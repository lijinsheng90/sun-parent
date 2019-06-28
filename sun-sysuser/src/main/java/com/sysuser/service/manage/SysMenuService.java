package com.sysuser.service.manage;

import java.util.List;
import java.util.Map;

import com.sysuser.model.sys.SysMenu;


public interface SysMenuService {


	/**
	 * 获取menus列表
	 * 
	 * @param pageSize
	 * @param menuId
	 * @param i
	 * @return
	 */
	List<SysMenu> menusList(int pageSize, int start, String menuId);

	/**
	 * 获取menus列表的总量
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param menuId
	 * @param i
	 * @return
	 */
	Integer menusSize(int pageSize, int start, String menuId);

	/**
	 * 新建菜单信息
	 * 
	 * @param menuEntity
	 */
	void insertMenu(SysMenu menuEntity);

	/**
	 * 修改菜单信息
	 * 
	 * @param menuEntity
	 */
	void updateMenu(SysMenu menuEntity);

	/**
	 * 删除菜单信息
	 * 
	 * @param groupId
	 */
	void deleteMenus(List<String> groupId);


	/**
	 * 根据登录角色获取有权限的菜单
	 * 
	 */
	List<SysMenu> getSysMenuList(int roleId);
	
	/**
	 *根据id获取菜单 
	 */
	void getParentSysMenuById(int id,List<SysMenu> list,Map<Integer, Integer> added);

}
