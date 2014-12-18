package com.example.togodemo.udao.inter;

import com.example.togodemo.mode.User;


public interface IUserService {
	/**
	 * 添加用户实体
	 * @param user
	 */
	public void insert(User user);
	/**
	 * 查找用户实体
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName);
//	public User getUser(String userName,String userPassword);
}
