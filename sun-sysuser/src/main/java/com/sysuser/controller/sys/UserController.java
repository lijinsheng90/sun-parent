package com.sysuser.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sysuser.model.sys.PageResult;
import com.sysuser.model.sys.SysUser;
import com.sysuser.service.manage.UserService;
import com.sysuser.utils.SecurityAuthenUtil;


@Controller
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
		userService.insertUser(userEntity,0);
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
    public String userManage(HashMap<String, Object> map, Model model) {
    	map.put("userEntity",new SysUser());
        return "/user/userManage";
    }
    
    /**
     * 用户管理--列表
     * @return
     */
    @RequestMapping(value="/user/getUserAll")
    @ResponseBody
    public Map<String, Object> getUserAll(){
    	List<SysUser> userlist=userService.usersList(null,50,0);
    	Map<String, Object> dataList=new HashMap<String, Object>();
    	dataList.put("total", userlist.size());
    	dataList.put("rows",userlist);
    	return dataList;
    }
    
	/**
	 * 新建或者编辑用户
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/users/addEditUser")
	@ResponseBody
	public Map<String, Object> addEditUser(@ModelAttribute SysUser userEntity,@RequestParam(name="roleid") String roleid,Model model) {
		if(userEntity.getId()==0) {
			userService.insertUser(userEntity,Integer.parseInt(roleid));
		}else {
			userService.updateUser(userEntity);
		}
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("resule","success");
		return data;
	}
	
	/**
	 * 删除用户
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/users/delUser")
	@ResponseBody
	public Map<String, Object> delUser(@RequestParam(name="UserId") String UserId) {
		List<String> userIds=new ArrayList<String>();
		userIds.add(UserId);
		userService.deleteUsers(userIds);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("resule","success");
		return data;
	}
	
	/**
	 * 根据userId获取用户信息
	 * 
	 * @param userEntity
	 * @return
	 */
	@GetMapping("/users/getUser")
	@ResponseBody
	public SysUser getUser(@RequestParam(name="userId") String userId) {
		SysUser sysUser=userService.getUserById(Integer.parseInt(userId));
		
		return sysUser;
	}
	
	@RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map, Model model) {
        model.addAttribute("say","欢迎欢迎,热烈欢迎");
        map.put("hello", "欢迎进入HTML页面");
        return "index";
    }
    
    /**
     * 角色管理
     * @return
     */
    @RequestMapping(value="/user/roleManage")
    public ModelAndView roleManage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/user/roleManage");
        return view;
    }
    
    /**
     * 个人信息
     * @return
     */
    @RequestMapping(value="/user/userInfo")
    public ModelAndView userInfo(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/user/userInfo");
        return view;
    }
}
