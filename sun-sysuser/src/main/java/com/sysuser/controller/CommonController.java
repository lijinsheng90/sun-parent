package com.sysuser.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sysuser.model.common.SearchSet;
import com.sysuser.model.common.Select;
import com.sysuser.model.common.Tree;
import com.sysuser.service.common.CommonService;

@Controller
public class CommonController {
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	
	@RequestMapping(value = "common/searchModel", method = RequestMethod.GET)
	public String searchModel(Model model) {
		String key="keytest";
		List<SearchSet> searchList=commonService.getSearchListByfunctionKey(key);
		model.addAttribute("searchList",searchList);
		//model.addAttribute("guests", userService.getUserLists());
		return "/common/searchPage :: mySarch";
	}
	
	@RequestMapping(value = "common/tree", method = RequestMethod.GET)
	@ResponseBody
	public List<Tree> tree(Model model,@RequestParam(name="dict_key") String dict_key) {
		List<Tree> treeList=commonService.getTreeList(dict_key);
		return treeList;
	}

	@RequestMapping(value = "common/select", method = RequestMethod.GET)
	@ResponseBody
	public List<Select> select(Model model,@RequestParam(name="dict_key") String dict_key) {
		List<Select> selectList=commonService.getSelectList(dict_key);
		return selectList;
	}
	
}
