package com.sysuser.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sysuser.model.sys.SysRole;


@Mapper
public interface SysRoleDao {
	/**
	 * 获取role列表
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<SysRole> rolesList(@Param("pageSize") int pageSize,@Param("start") int start);

	/**
	 * 获取role列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer rolesSize(@Param("pageSize") int pageSize,@Param("start") int start);

	/**
	 * 新建角色信息
	 * @param roleEntity
	 */
	public void insertRole(@Param("roleEntity") SysRole roleEntity);

	/**
	 * 更新角色信息
	 * @param roleEntity
	 */
	public void updateRole(@Param("roleEntity") SysRole roleEntity);

	/**
	 * 删除角色信息
	 * @param groupId
	 */
	public void deleteRoles(@Param("groupId") List<String> groupId);
	

	/**
	 * 得到角色全部数据
	 * @return
	 */
	public List<SysRole> allRoles();

	/**
	 * 通过UserId得到对应的role
	 * @param id
	 * @return
	 */
	public List<String> getRolesByUserId(int id);
}
