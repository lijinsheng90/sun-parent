package com.sysuser.model.common;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchSet {
	
	private String functionKey;
	private String fieldName;
	private String fieldCaption;
	private String tagType;
	private String dictKey;
	private int seqNo;
	private int width;

	private List<Map<String,Object>> subList;//通过ds_script 查询获取到的结果

}
