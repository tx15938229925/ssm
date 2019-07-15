package com.mypack.service;
import java.util.List;


import com.github.pagehelper.PageInfo;
import com.mypack.entity.Menu;
import com.mypack.entity.User;

public interface UserServiceIF {
	public User queryOneUser(String username);
	public User queryOneUserById(int id);
	public List<Menu> queryAllMenusByRoleId(int roleId);
	public List<User> queryAllUser();
	//鍒嗛〉鏌ヨ鎵�鏈夌敤鎴�
	public PageInfo<User> queryAllUserByPage(int currentPage,int pageSize);
	
	public boolean updateRole(int roleId,int userId);
	
	public boolean updateUser(User user);
	
	public boolean addUser(User user);
	
	public boolean addRole(int roleId,int userId);
	
	//鍒犻櫎鐢ㄦ埛
	public boolean deleteUserById(int id);
	//鍒犻櫎鐢ㄦ埛鏉冮檺
	public boolean deleteUserRole(int userId);
	public int queryMaxUserId();
}
