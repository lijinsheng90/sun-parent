package com.sysuser.service.manage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysuser.dao.SysMenuDao;
import com.sysuser.dao.SysRoleDao;
import com.sysuser.model.sys.SysMenu;


@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private SysMenuDao menuDao;
	
	@Autowired
	private SysRoleDao roleDao;

	@Override
	public List<SysMenu> menuList(int id) {
		List<String> idList = roleDao.getModulesById(id);
		
		String idstemp = "";
		for (String idtemp : idList) {
			idstemp = idstemp + idtemp;
		}
		String[] ids = idstemp.split(";");
		List<SysMenu> parentMenuList = menuDao.getParentMenuListById(ids);
		List<SysMenu> childrenMenuList = menuDao.getMenuListById(ids);
		List<SysMenu> menuList = new ArrayList<SysMenu>();

		for (SysMenu parentMenu : parentMenuList) {
			List<SysMenu> menuListTemp = new ArrayList<SysMenu>();
			for (SysMenu childrenMenu : childrenMenuList) {
				if (parentMenu.getId() == childrenMenu.getParentId()) {
					menuListTemp.add(childrenMenu);
				}
			}
			parentMenu.setChildren(menuListTemp);
			menuList.add(parentMenu);
		}

		return menuList;
	}

	@Override
	public List<SysMenu> menusList(int pageSize, int start, String menuId) {
		return menuDao.menusList(pageSize, start, menuId);
	}

	@Override
	public Integer menusSize(int pageSize, int start, String menuId) {
		return menuDao.menusSize(pageSize, start, menuId);
	}

	@Override
	public void insertMenu(SysMenu menuEntity) {
		menuDao.insertMenu(menuEntity);
	}

	@Override
	public void updateMenu(SysMenu menuEntity) {
		menuDao.updateMenu(menuEntity);
	}

	@Override
	public void deleteMenus(List<String> groupId) {
		menuDao.deleteMenus(groupId);
	}

	@Override
	public List<SysMenu> menusByParentId(int parentId) {
		return menuDao.menusByParentId(parentId);
	}

	@Override
	public List<SysMenu> getSubmenus() {
		return menuDao.getSubmenus();
	}

}
