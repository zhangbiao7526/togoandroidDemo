package com.example.togodemo.mode;

public class User {

	private String userName;
	private String userpassword;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public User(String userName, String userpassword) {
		super();
		this.userName = userName;
		this.userpassword = userpassword;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
