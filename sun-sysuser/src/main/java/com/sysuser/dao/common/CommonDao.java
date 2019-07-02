package com.sysuser.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sysuser.model.common.SearchSet;
import com.sysuser.model.common.Select;
import com.sysuser.model.common.Tree;


@Mapper
public interface CommonDao {

	List<SearchSet> getSearchListByfunctionKey(@Param("key") String key);

	List<Tree> getTree(@Param("dictkey") String dictkey);

	List<Select> getSelectList(String dictkey);
	
}
