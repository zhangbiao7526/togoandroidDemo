package com.example.togodemo.udao.biz;

import com.example.togodemo.mode.User;
import com.example.togodemo.udao.inter.IUserService;
import com.example.togodemo.udao.mpl.UserService;


public class UserManagers {
	/**
	 * 使用接口
	 */
	private IUserService dao;
	public UserManagers(){
		dao=new UserService();
	}
	/**
	 * 登录，成功返回登录 成功的用户的实体,失败返回null
	 * 
	 * @param user_name
	 * @param user_password
	 * @return
	 */
	public User Login_biz(String user_name,String user_password){
		User user=dao.getUserByName(user_name);
		if(user!=null){
			return user.getUserpassword().equals(user_password) ? user :null;
		}
		return null;
		
	}
	/**
	 * 注册用户,成功返回注册的用户实体，失败返回null;
	 * @param user
	 * @return
	 */
	public User Register(User user){
		try{
			this.dao.insert(user);
			return user;
		}catch(Exception e){
			return null;
		}
		
	}
}
