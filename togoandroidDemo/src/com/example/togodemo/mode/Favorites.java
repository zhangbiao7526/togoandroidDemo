package com.example.togodemo.mode;

public class Favorites {
	
	Integer f_i_Fid;
	Integer f_i_Sid;
	String f_c_username;
	
	
	public Integer getF_i_Fid() {
		return f_i_Fid;
	}
	public void setF_i_Fid(Integer f_i_Fid) {
		this.f_i_Fid = f_i_Fid;
	}
	public Integer getF_i_Sid() {
		return f_i_Sid;
	}
	public void setF_i_Sid(Integer f_i_Sid) {
		this.f_i_Sid = f_i_Sid;
	}
	public String getF_c_username() {
		return f_c_username;
	}
	public void setF_c_username(String f_c_username) {
		this.f_c_username = f_c_username;
	}
	
	public Favorites(Integer f_i_Fid, Integer f_i_Sid, String f_c_username) {
		super();
		this.f_i_Fid = f_i_Fid;
		this.f_i_Sid = f_i_Sid;
		this.f_c_username = f_c_username;
	}
	
	public Favorites() {
		super();
		
	}
	
    

}
