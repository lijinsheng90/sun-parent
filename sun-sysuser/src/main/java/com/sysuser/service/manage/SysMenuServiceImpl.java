package com.sysuser.service.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysuser.dao.SysMenuDao;
import com.sysuser.dao.SysRoleDao;
import com.sysuser.model.sys.SysMenu;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuDao menuDao;
	
	@Autowired
	private SysRoleDao roleDao;


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
	public List<SysMenu> getSysMenuList(int roleId) {
		List<SysMenu> list=new ArrayList<SysMenu>();
		List<SysMenu> authMenuList=menuDao.getSysMenuList(roleId);//获取当前角色拥有的菜单权限
		list.addAll(authMenuList);
		Map<Integer, Integer> added=new HashMap<Integer, Integer>();//已加入父类菜单
		for (SysMenu sysMenu : authMenuList) {
			this.getParentSysMenuById(sysMenu.getParentId(),list,added);//递归算法获取所有父类菜单
		}
		return list;
	}

	@Override
	public void getParentSysMenuById(int id, List<SysMenu> list,Map<Integer, Integer> added) {
		
		SysMenu parentMenu=menuDao.getSysMenuById(id);
		if(!added.containsKey(parentMenu.getId())) {
			added.put(parentMenu.getId(),parentMenu.getId());
			list.add(parentMenu);
		}
		if(parentMenu.getParentId()!=0) {
			this.getParentSysMenuById(parentMenu.getParentId(),list,added);
		}
	}


}
