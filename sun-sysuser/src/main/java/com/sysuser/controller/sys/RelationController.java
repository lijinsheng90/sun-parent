package com.sysuser.controller.sys;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sysuser.model.sys.SysUserRole;
import com.sysuser.service.manage.SysUserRoleService;


@RestController
public class RelationController {

	private Logger log = LoggerFactory.getLogger(RelationController.class);

	@Resource(name = "sysUserRoleServiceImpl")
	private SysUserRoleService relationService;

	/**
	 * 通过userId得到关系List
	 * @param userId
	 * @return
	 */
	@GetMapping("/relations/{userId}")
	public List<SysUserRole> getRelationByUserId(@PathVariable int userId){
		log.debug("The method is ending");
		return relationService.getRelationByUserId(userId);
	}
	
	/**
	 * 批量插入关系数据
	 * @param relationList
	 * @return
	 */
	@PostMapping("/relations")
	public List<SysUserRole> insertRelations(@RequestBody() List<SysUserRole> relationList){
		relationService.insertRelations(relationList);
		log.debug("The method is ending");
		return relationList;
	}
}
