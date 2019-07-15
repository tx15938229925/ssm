package com.mypack.entity;

public class Menu {
	private int id;
	private String menuname;
	private String url;
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(int id, String menuname, String url) {
		super();
		this.id = id;
		this.menuname = menuname;
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuname=" + menuname + ", url=" + url + "]";
	}
	
	
}
