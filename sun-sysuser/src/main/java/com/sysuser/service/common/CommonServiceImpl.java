package com.sysuser.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysuser.dao.common.CommonDao;
import com.sysuser.model.common.SearchSet;
import com.sysuser.model.common.Select;
import com.sysuser.model.common.Tree;

@Service("commonService")
public class CommonServiceImpl implements CommonService{

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public List<SearchSet> getSearchListByfunctionKey(String key) {
		List<SearchSet> list=commonDao.getSearchListByfunctionKey(key);
		/*for (SearchSet searchSet : list) {
			if(StringUtils.isNotBlank(searchSet.getDsScript()) && searchSet.getTagType().equals("select")){
				List<Map<String, Object>> subList=commonDao.searchSubDsScript(searchSet.getDsScript());
				searchSet.setSubList(subList);
			}else if(StringUtils.isNotBlank(searchSet.getDsScript()) && searchSet.getTagType().equals("select")){
				List<Map<String, Object>> subList=commonDao.searchSubDsScript(searchSet.getDsScript());
				searchSet.setSubList(subList);
			}
		}*/
		return list;
	}

	@Override
	public List<Tree> getTreeList(String dictkey) {
		List<Tree> list=commonDao.getTree(dictkey);
		return list;
	}

	@Override
	public List<Select> getSelectList(String dictkey) {
		List<Select> list=commonDao.getSelectList(dictkey);
		return list;
	}

}
