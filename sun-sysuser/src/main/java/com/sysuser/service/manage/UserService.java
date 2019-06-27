package com.sysuser.service.manage;

import java.util.List;

import com.sysuser.model.sys.SysUser;


public interface UserService {
	public void insert(SysUser userEntity);

	public void del(SysUser userEntity);

	/**
	 * 通过登录名得到用户信息
	 * @param loginName
	 * @return
	 */
	public SysUser getUserEntityByLoginName(String loginName);

	/**
	 * 获取user列表
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<SysUser> usersList(String loginName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer usersSize(String loginName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param userEntity
	 */
	public void insertUser(SysUser userEntity,int roleid);

	/**
	 * 更新用户信息
	 * @param userEntity
	 */
	public void updateUser(SysUser userEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteUsers(List<String> userIds);

	/**
	 * 根据id获取用户对象
	 * @param 
	 */
	public SysUser getUserById(int userId);
}
