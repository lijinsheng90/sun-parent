package com.sysuser.controller.sys;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sysuser.model.sys.SysMenu;
import com.sysuser.model.sys.PageResult;
import com.sysuser.model.sys.SysUser;
import com.sysuser.service.manage.SysMenuService;
import com.sysuser.service.manage.UserService;


@RestController
public class MenuController {

	private Logger log = LoggerFactory.getLogger(MenuController.class);

	@Resource(name = "sysMenuService")
	private SysMenuService menuService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * 获取该用户的菜单权限
	 * 
	 * @param loginName
	 * @return
	 */
	@GetMapping("/menu/{loginName}")
	public List<SysMenu> menuList(@PathVariable String loginName) {
		SysUser userEntity = userService.getUserEntityByLoginName(loginName);
		List<SysMenu> menuList = null;
		return menuList;
	}

	/**
	 * 获取menus表数据
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/menus")
	public PageResult menusList(int pageSize, int page, String menuId) {
		PageResult pageResult = new PageResult();
		pageResult.setData(menuService.menusList(pageSize, page * pageSize, menuId));
		pageResult.setTotalCount(menuService.menusSize(pageSize, page * pageSize, menuId));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 通过parentId得到menus列表
	 * 
	 * @param parentId
	 * @return
	 */
	@GetMapping("/menus/parentId")
	public List<SysMenu> menusByParentId(int parentId) {
		return null;
	}

	/**
	 * 新建菜单信息
	 * 
	 * @param menuEntity
	 * @return
	 */
	@PostMapping("/menus/menu")
	public SysMenu insertMenu(@RequestBody SysMenu menuEntity) {
		menuService.insertMenu(menuEntity);
		log.debug("The method is ending");
		return menuEntity;
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param menuEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/menus/{id}")
	public SysMenu updateMenu(@RequestBody SysMenu menuEntity, @PathVariable int id) {
		if (menuEntity.getId() == id) {
			menuService.updateMenu(menuEntity);
		}
		log.debug("The method is ending");
		return menuEntity;
	}

	/**
	 * 删除菜单信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/menus")
	public List<String> deleteMenus(@RequestBody List<String> groupId) {
		menuService.deleteMenus(groupId);
		return groupId;
	}

	/**
	 * 获取二级菜单
	 * @return
	 */
	@GetMapping("/menus/submenus")
	public List<SysMenu> getSubmenus() {
		return null;
	}
}
