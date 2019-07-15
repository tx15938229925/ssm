package com.mypack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mypack.dao.UserDao;
import com.mypack.entity.Menu;
import com.mypack.entity.User;


@Service
@Transactional
public class UserServiceImpl implements UserServiceIF{
	
	@Autowired
	UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	

	@Override
	public User queryOneUser(String username) {
		return userDao.queryOneUser(username);
	}


	@Override
	public List<Menu> queryAllMenusByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return userDao.queryAllMenusByRoleId(roleId);
	}


	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllUser();
	}
	
	

	//鍒嗛〉鐗堟湰鏌ヨ鎵�鏈�  浼犲叆鐨勬槸褰撳墠椤�  鍜屾瘡椤靛嚑鏉℃暟鎹�
	@Override
	public PageInfo<User> queryAllUserByPage(int currentPage,int pageSize){
		PageHelper.startPage(currentPage, pageSize);
		List<User> userlst= userDao.queryAllUser();
		PageInfo<User> pf=new PageInfo<User>(userlst);
		return pf;
	}


	@Override
	public User queryOneUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.queryOneUserById(id);
	}


	@Override
	public boolean updateRole(int roleId,int userId) {
		return userDao.updateRole(roleId,userId);
	}


	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}


	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}


	@Override
	public boolean addRole(int roleId, int userId) {
		// TODO Auto-generated method stub
		return userDao.addRole(roleId, userId);
	}


	@Override
	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(id);
	}


	@Override
	public boolean deleteUserRole(int userId) {
		// TODO Auto-generated method stub
		return userDao.deleteUserRole(userId);
	}


	@Override
	public int queryMaxUserId() {
		// TODO Auto-generated method stub
		return userDao.queryMaxUserId();
	}
}
