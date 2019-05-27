package com.sysuser.model.sys;

import java.util.List;

public class SysMenu {
	/**
	 * id
	 */
	private int id;
	/**
	 * 菜单名
	 */
	private String name;
	/**
	 * url
	 */
	private String url;
	/**
	 * 父类id
	 */
	private int parentId;
	/**
	 * 排序
	 */
	private int sort;
	/**
	 * 描述
	 */
	private String remark;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 二级菜单
	 */
	List<SysMenu> children;
	
	/**
	 * 一个资源对应多个权限
	 */
	private List<SysAuthoritie> sysAuthorities;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	public List<SysAuthoritie> getSysAuthorities() {
		return sysAuthorities;
	}

	public void setSysAuthorities(List<SysAuthoritie> sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	@Override
	public String toString() {
		return "MenuEntity [id=" + id + ", name=" + name + ", url=" + url + ", parentId=" + parentId + ", sort=" + sort
				+ ", remark=" + remark + ", icon=" + icon + ", children=" + children + "]";
	}

}
