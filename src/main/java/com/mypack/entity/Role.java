package com.mypack.entity;

import java.util.List;

public class Role {
	private int id;
	private String rolename;
	List<Menu> menulst;
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int id, String rolename, List<Menu> menulst) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.menulst = menulst;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public List<Menu> getMenulst() {
		return menulst;
	}
	public void setMenulst(List<Menu> menulst) {
		this.menulst = menulst;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", rolename=" + rolename + ", menulst=" + menulst + "]";
	}
	
	
	
}
