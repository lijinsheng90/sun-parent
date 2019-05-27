package com.sysuser.service.manage;

import java.util.List;

import com.sysuser.model.sys.SysUserRole;


public interface RelationService {

	/**
	 * 通过userId得到关系List
	 * @param userId
	 * @return
	 */
	public List<SysUserRole> getRelationByUserId(int userId);

	/**
	 * 批量插入关系数据
	 * @param relationList
	 */
	public void insertRelations(List<SysUserRole> relationList);

	
}
