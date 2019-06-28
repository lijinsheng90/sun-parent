package com.sysuser.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysuser.dao.SysRoleDao;
import com.sysuser.model.sys.SysRole;


@Service(value = "sysRoleServiceImpl")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao roleDao;

	@Override
	public List<SysRole> rolesList(int pageSize, int start) {
		return roleDao.rolesList(pageSize, start);
	}

	@Override
	public Integer rolesSize(int pageSize, int start) {
		return roleDao.rolesSize(pageSize, start);
	}

	@Override
	public void insertRole(SysRole roleEntity) {
		roleDao.insertRole(roleEntity);
	}

	@Override
	public void updateRole(SysRole roleEntity) {
		roleDao.updateRole(roleEntity);
	}

	@Override
	public void deleteRoles(List<String> groupId) {
		roleDao.deleteRoles(groupId);
	}

	@Override
	public List<SysRole> allRoles() {
		return roleDao.allRoles();
	}

}
