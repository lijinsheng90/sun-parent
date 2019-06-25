package com.sysuser.controller.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.util.NullableWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sysuser.model.sys.PageResult;
import com.sysuser.model.sys.SysUser;
import com.sysuser.service.manage.UserService;
import com.sysuser.utils.SecurityAuthenUtil;


@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userServiceImpl")
	private UserService userService;

	//根据用户名获取对象
	@GetMapping("/user/{loginName}")
	public SysUser userGet(@PathVariable String loginName) {
		SysUser userEntity = userService.getUserEntityByLoginName(loginName);
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 获取user表数据
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/users")
	public PageResult usersList(String loginName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.usersList(loginName, pageSize, page * pageSize));
		pageResult.setTotalCount(userService.usersSize(loginName, pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/users/user")
	public SysUser insertUser(@RequestBody SysUser userEntity) {
		userService.insertUser(userEntity);
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userEntity
	 * @param id
	 * @return
	 */
	@PutMapping("/users/{id}")
	public SysUser updateUser(@RequestBody SysUser userEntity, @PathVariable int id) {
		if (userEntity.getId() == id) {
			userService.updateUser(userEntity);
		}
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/users")
	public List<String> deleteUsers(@RequestBody List<String> groupId) {
		userService.deleteUsers(groupId);
		return groupId;
	}
	
    @GetMapping("/currentuser")
    public String currentuser() {
        return SecurityAuthenUtil.getUserId()+"================"+SecurityAuthenUtil.getLoginName();
    }
    
    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value="/user/userManage")
    public ModelAndView userManage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/user/userManage");
        return view;
    }
    
    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value="/user/getUserAll")
    @ResponseBody
    public String getUserAll(){
    	List<SysUser> list=new ArrayList<SysUser>();
    	for (int i = 0; i < 5; i++) {
    		SysUser user=new SysUser();
    		user.setId(i);
    		user.setLoginName("ljs"+i);
    		user.setName("name"+i);
    		user.setEmail("772995929@qq.com"+i);
    		list.add(user);
		}
    	//return list;
    	String rtn = "{\"total\":2,\"rows\":[{\"id\":0,\"loginName\":\"ljs0\",\"name\":\"name0\",\"password\":null,\"email\":\"772995929@qq.com0\",\"sysUserRole\":null},{\"id\":1,\"loginName\":\"ljs0\",\"name\":\"name0\",\"password\":null,\"email\":\"772995929@qq.com0\",\"sysUserRole\":null}]}";//定义变量并初始化JSON格式的结果集
    	return rtn;
      
    }
}
