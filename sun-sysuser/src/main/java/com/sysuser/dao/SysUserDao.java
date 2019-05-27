package com.sysuser.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.GrantedAuthority;

import com.sysuser.model.sys.SysAuthoritie;
import com.sysuser.model.sys.SysUser;

@Mapper
public interface SysUserDao {

	public ArrayList<SysUser> select(@Param("userEntity") SysUser userEntity);
	
	public void del(@Param("userEntity") SysUser userEntity);
	
	public void update(@Param("userEntity") SysUser userEntity);
	
	public void insert(@Param("userEntity") SysUser userEntity);
	
	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public SysUser getUserEntityByLoginName(@Param("loginName") String loginName);

	/**
	 * 获取user列表
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<SysUser> usersList(@Param("loginName")String loginName,@Param("pageSize") int pageSize,@Param("start") int start);

	/**
	 * 获取user列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer usersSize(@Param("loginName")String loginName,@Param("pageSize") int pageSize,@Param("start") int start);

	/**
	 * 新建用户信息
	 * @param userEntity
	 */
	public void insertUser(@Param("userEntity") SysUser userEntity);

	/**
	 * 更新用户信息
	 * @param userEntity
	 */
	public void updateUser(@Param("userEntity") SysUser userEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteUsers(@Param("groupId") List<String> groupId);
	
	
	//=========登录权限控制=======stat===========
	/**
	 * 获取用户所有权限
	 * @param userId
	 * @return
	 */
	public ArrayList<SysAuthoritie> getAuthoritieByUserId(@Param("userId") int userId,@Param("roleId") int roleId);
	
	/**
	 * 获取所有权限
	 * @param userId
	 * @return
	 */
	public ArrayList<SysAuthoritie> getAuthoritieAll();
	
	
	//=========登录权限控制=======end===========
}
