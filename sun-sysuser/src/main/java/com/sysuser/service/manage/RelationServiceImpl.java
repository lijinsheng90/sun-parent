package com.sysuser.service.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysuser.dao.SysUserRoleDao;
import com.sysuser.model.sys.SysUserRole;


@Service(value = "relationServiceImpl")
public class RelationServiceImpl implements RelationService {

	@Autowired
	private SysUserRoleDao relationDao;

	@Override
	public List<SysUserRole> getRelationByUserId(int userId) {
		return relationDao.getRelationByUserId(userId);
	}

	@Transactional
	@Override
	public void insertRelations(List<SysUserRole> relationList) {
		relationDao.delById(relationList.get(0).getUserId());
		if (relationList.get(0).getRoleId() != null) {
			relationDao.insertRelations(relationList);
		}
	}

}
