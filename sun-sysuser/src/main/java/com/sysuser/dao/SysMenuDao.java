package com.sysuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sysuser.model.sys.SysMenu;

@Mapper
public interface SysMenuDao {


	public List<SysMenu> getMenuListById(@Param("ids") String[] ids);

	/**
	 * 获取menus列表
	 * 
	 * @param pageSize
	 * @param start
	 * @param menuId
	 * @return
	 */
	public List<SysMenu> menusList(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("menuId") String menuId);

	/**
	 * 获取menus列表的总量
	 * 
	 * @param pageSize
	 * @param start
	 * @param menuId
	 * @return
	 */
	public Integer menusSize(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("menuId") String menuId);

	/**
	 * 新建菜单信息
	 * 
	 * @param menuEntity
	 */
	public void insertMenu(@Param("menuEntity") SysMenu menuEntity);

	/**
	 * 修改菜单信息
	 * 
	 * @param menuEntity
	 */
	public void updateMenu(@Param("menuEntity") SysMenu menuEntity);

	/**
	 * 删除菜单信息
	 * 
	 * @param groupId
	 */
	public void deleteMenus(@Param("groupId") List<String> groupId);

	
	/**
	 * 根据权限获取菜单
	 * @return
	 */
	public List<SysMenu> getResourcesListByAuth(@Param("auth") String auth);

	/**
	 * 获取所有菜单资源
	 * @return
	 */
	public List<SysMenu> getResourcesList();

	/**
	 * 根据登录角色获取有权限的菜单
	 */
	public List<SysMenu> getSysMenuList(@Param("roleId") int roleId);

	/**
	 * 根据菜单ID获取菜单对象
	 */
	public SysMenu getSysMenuById(@Param("id") int id);

}
