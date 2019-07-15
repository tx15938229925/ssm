package com.mypack.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mypack.entity.Menu;
import com.mypack.entity.User;

public interface UserDao {
	public User queryOneUser(String username);
	public User queryOneUserById(int id);
	public List<Menu> queryAllMenusByRoleId(int roleId);
	
	//查询所有用户
	public List<User> queryAllUser();
	//修改用户信息
	public boolean updateUser(User user);
	//修改权限
	public boolean updateRole(@Param("roleId")int roleId,@Param("userId")int userId);
	//新增用户
	public boolean addUser(User user);
	//新增用户权限 修改user_role表信息
	public boolean addRole(@Param("roleId")int roleId,@Param("userId")int userId);
	//删除用户
	public boolean deleteUserById(int id);
	//删除用户权限
	public boolean deleteUserRole(int userId);
	//查询id最大的用户id
	public int queryMaxUserId();
}
