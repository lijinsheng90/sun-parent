package com.sysuser.service.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysuser.dao.SysUserRoleDao;
import com.sysuser.model.sys.SysUserRole;


@Service(value = "sysUserRoleServiceImpl")
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private SysUserRoleDao relationDao;

	@Override
	public List<SysUserRole> getRelationByUserId(int userId) {
		return relationDao.getSysUserRolesById(userId);
	}

	@Transactional
	@Override
	public void insertRelations(List<SysUserRole> relationList) {
		List<String> userIds=new ArrayList<String>();
		relationDao.delById(userIds);
		if (relationList.get(0).getRoleId() != null) {
			relationDao.insertSysUserRoles(relationList);
		}
	}

}
