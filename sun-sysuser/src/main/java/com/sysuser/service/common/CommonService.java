package com.sysuser.service.common;

import java.util.List;

import com.sysuser.model.common.SearchSet;
import com.sysuser.model.common.Select;
import com.sysuser.model.common.Tree;


public interface CommonService {

  public List<SearchSet> getSearchListByfunctionKey(String key);

public List<Tree> getTreeList(String dictkey);

public List<Select> getSelectList(String dict_key);
  
}
