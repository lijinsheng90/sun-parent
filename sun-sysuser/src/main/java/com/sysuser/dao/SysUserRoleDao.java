package com.sysuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sysuser.model.sys.SysUserRole;


@Mapper
public interface SysUserRoleDao {

	/**
	 * 通过userId得到关系List
	 * 
	 * @param userId
	 * @return
	 */
	public List<SysUserRole> getSysUserRolesById(@Param("userId") int userId);

	/**
	 * 通过userId删除关系
	 * 
	 * @param userId
	 */
	public void delById(@Param("userIds") List<String> userIds);

	/**
	 * 批量插入关系数据
	 * 
	 * @param relationList
	 */
	public void insertSysUserRoles(List<SysUserRole> relationList);
}
