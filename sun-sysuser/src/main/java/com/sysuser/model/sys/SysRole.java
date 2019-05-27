package com.sysuser.model.sys;

/**
 * @author wanghuan
 *
 */
public class SysRole {
	/**
	 * id
	 */
	private int id;
	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String describe;
	
	/**
	 * 状态
	 */
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
