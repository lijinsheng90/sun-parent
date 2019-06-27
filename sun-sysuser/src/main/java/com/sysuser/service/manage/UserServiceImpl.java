package com.sysuser.service.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sysuser.dao.SysUserDao;
import com.sysuser.dao.SysUserRoleDao;
import com.sysuser.model.sys.SysUser;
import com.sysuser.model.sys.SysUserRole;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public void insert(SysUser userEntity) {
		userDao.insert(userEntity);
	}

	@Override
	public void del(SysUser userEntity) {
		userDao.del(userEntity);
	}

	@Override
	public SysUser getUserEntityByLoginName(String loginName) {
		return userDao.getUserEntityByLoginName(loginName);
	}

	@Override
	public List<SysUser> usersList(String loginName, int pageSize, int start) {
		return userDao.usersList( loginName,  pageSize,  start);
	}

	@Override
	public Integer usersSize(String loginName, int pageSize, int start) {
		return userDao.usersSize(loginName, pageSize, start);
	}

	@Override
	public void insertUser(SysUser userEntity,int roleid) {
		/*String password = null;
		try {
			password = MD5Util.encrypt(userEntity.getPassword());
			userEntity.setPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}*/
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		userEntity.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(userEntity.getPassword()));
		userDao.insertUser(userEntity);
		int userid=userEntity.getId();
		SysUserRole sysUserRole=new SysUserRole();
		sysUserRole.setUserId(userid);
		sysUserRole.setRoleId(roleid);
		List<SysUserRole> relationList=new ArrayList<SysUserRole>();
		relationList.add(sysUserRole);
		sysUserRoleDao.insertSysUserRoles(relationList);
	}

	@Override
	public void updateUser(SysUser userEntity) {
		//userEntity.setPassword(new Md5PasswordEncoder().encodePassword(userEntity.getPassword(), null));
		userEntity.setPassword("{bcrypt}"+new BCryptPasswordEncoder().encode(userEntity.getPassword()));
		userDao.updateUser(userEntity);
	}

	@Override
	public void deleteUsers(List<String> userIds) {
		userDao.deleteUsers(userIds);
		sysUserRoleDao.delById(userIds);
	}

	@Override
	public SysUser getUserById(int userId) {
		return userDao.getUserById(userId);
	}

}
