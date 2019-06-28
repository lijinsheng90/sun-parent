package com.sysuser.service.manage;

import java.util.List;

import com.sysuser.model.sys.SysRole;


public interface SysRoleService {

	/**
	 * 获取role列表
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<SysRole> rolesList(int pageSize, int start);

	/**
	 * 获取role列表的总量
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer rolesSize(int pageSize, int start);

	/**
	 * 新建角色信息
	 * 
	 * @param roleEntity
	 */
	public void insertRole(SysRole roleEntity);

	/**
	 * 更新角色信息
	 * 
	 * @param roleEntity
	 */
	public void updateRole(SysRole roleEntity);

	/**
	 * 删除角色信息
	 * 
	 * @param groupId
	 */
	public void deleteRoles(List<String> groupId);

	/**
	 * 得到角色全部数据
	 * @return
	 */
	public List<SysRole> allRoles();
}
