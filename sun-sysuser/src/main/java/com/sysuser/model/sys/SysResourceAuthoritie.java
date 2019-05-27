package com.sysuser.model.sys;

import java.util.List;

/**
 * @author wanghuan
 *
 */
public class SysResourceAuthoritie {
	/**
	 * 资源ID
	 */
	private Integer ResourceId;
	/**
	 * 权限ID
	 */
	private Integer authoritieId;
	
	public Integer getResourceId() {
		return ResourceId;
	}
	public void setResourceId(Integer resourceId) {
		ResourceId = resourceId;
	}
	public Integer getAuthoritieId() {
		return authoritieId;
	}
	public void setAuthoritieId(Integer authoritieId) {
		this.authoritieId = authoritieId;
	}


	
}
