package com.sysuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sysuser.model.sys.SysMenu;

@Mapper
public interface SysMenuDao {

	/**
	 * 通过用户Id得到一级菜单List
	 * 
	 * @param id
	 * @return
	 */
	public List<SysMenu> getParentMenuListById(@Param("ids") String[] ids);

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
	 * 通过parentId得到menus列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysMenu> menusByParentId(@Param("parentId") int parentId);

	/**
	 * 获取二级菜单
	 * @return
	 */
	public List<SysMenu> getSubmenus();
	
	
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

}
