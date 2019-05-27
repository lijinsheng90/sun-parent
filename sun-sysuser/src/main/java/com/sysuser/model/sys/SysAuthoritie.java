package com.sysuser.model.sys;

/**
 * @author wanghuan
 *
 */
public class SysAuthoritie {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 权限名称
	 */
	private String name;
	
	/**
	 * 描述
	 */
	private String displayName;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	

}
