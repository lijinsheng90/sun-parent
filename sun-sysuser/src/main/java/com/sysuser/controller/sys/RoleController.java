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

import com.sysuser.model.sys.PageResult;
import com.sysuser.model.sys.SysRole;
import com.sysuser.service.manage.SysRoleService;

@RestController
public class RoleController {

	private Logger log = LoggerFactory.getLogger(RoleController.class);

	@Resource(name = "sysRoleServiceImpl")
	private SysRoleService roleService;

	/**
	 * 获取role表数据
	 * 
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/roles")
	public PageResult rolesList(String loginName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(roleService.rolesList(pageSize, page * pageSize));
		pageResult.setTotalCount(roleService.rolesSize(pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建角色信息
	 * 
	 * @param roleEntity
	 * @return
	 */
	@PostMapping("/roles/role")
	public SysRole insertRole(@RequestBody SysRole roleEntity) {
		roleService.insertRole(roleEntity);
		log.debug("The method is ending");
		return roleEntity;
	}

	/**
	 * 更新角色信息
	 * 
	 * @param roleEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/roles/{id}")
	public SysRole updateRole(@RequestBody SysRole roleEntity, @PathVariable int id) {
		if (roleEntity.getId() == id) {
			roleService.updateRole(roleEntity);
		}
		log.debug("The method is ending");
		return roleEntity;
	}

	/**
	 * 删除角色信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/roles")
	public List<String> deleteRoles(@RequestBody List<String> groupId) {
		roleService.deleteRoles(groupId);
		return groupId;
	}
	
	/**
	 * 得到角色全部数据
	 * @return
	 */
	@GetMapping("/roles/all")
	public List<SysRole> allRoles(){
		return roleService.allRoles();
	}
}
