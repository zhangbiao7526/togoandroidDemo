package com.example.togodemo.mode;


public class User {
int f_c_userid;
String f_c_username;
String f_c_userpassword;
String f_c_userquestion;
String f_c_userresult;
String f_c_useraddress;
String f_c_userphone;

public boolean Login(String f_c_username,String f_c_userpassword){
	
	return false;
}

public int getF_c_userid() {
	return f_c_userid;
}

public void setF_c_userid(int f_c_userid) {
	this.f_c_userid = f_c_userid;
}



public String getF_c_username() {
	return f_c_username;
}
public void setF_c_username(String f_c_username) {
	this.f_c_username = f_c_username;
}
public String getF_c_userpassword() {
	return f_c_userpassword;
}
public void setF_c_userpassword(String f_c_userpassword) {
	this.f_c_userpassword = f_c_userpassword;
}


public String getF_c_userquestion() {
	return f_c_userquestion;
}
public void setF_c_userquestion(String f_c_userquestion) {
	this.f_c_userquestion = f_c_userquestion;
}
public String getF_c_userresult() {
	return f_c_userresult;
}
public void setF_c_userresult(String f_c_userresult) {
	this.f_c_userresult = f_c_userresult;
}

public String getF_c_useraddress() {
	return f_c_useraddress;
}

public void setF_c_useraddress(String f_c_useraddress) {
	this.f_c_useraddress = f_c_useraddress;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}

public User( String f_c_username, String f_c_userpassword) {
	super();
	this.f_c_username = f_c_username;
	this.f_c_userpassword = f_c_userpassword;
}

public User(int f_c_userid, String f_c_username, String f_c_userpassword,
		String f_c_userquestion, String f_c_userresult) {
	super();
	this.f_c_userid = f_c_userid;
	this.f_c_username = f_c_username;
	this.f_c_userpassword = f_c_userpassword;
	this.f_c_userquestion = f_c_userquestion;
	this.f_c_userresult = f_c_userresult;
}

//ʹ�ù��췽��4����
public void addUser(String username, String userpassword) {
	// TODO Auto-generated method stub
	
	this.f_c_username = username;
	this.f_c_userpassword = userpassword;
}

public String getF_c_userphone() {
	return f_c_userphone;
}

public void setF_c_userphone(String f_c_userphone) {
	this.f_c_userphone = f_c_userphone;
}

}
