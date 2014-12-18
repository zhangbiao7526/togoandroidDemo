package com.example.togodemo.udao.mpl;

import java.util.HashMap;
import java.util.Map;

import com.example.togodemo.mode.User;
import com.example.togodemo.udao.inter.IUserService;

public class UserService implements IUserService{
	private Map<String, User> user_map=new HashMap<String, User>();
//	public UserService(String user_name,String user_password){
//		user_map=new HashMap<String, User>();
//		User user=new User(user_name,user_password);
//		this.insert(user);//将User对象添加进接口中，通过接口来判定用户是否登录
//	}
	@Override
	public void insert(User user) {
		user_map.put(user.getUserName(), user);
	}
	@Override
	public User getUserByName(String userName) {
		
		return user_map.get(userName);
	}
}
