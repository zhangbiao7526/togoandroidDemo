package com.example.togodemo.mode;

public class ShopInfo {
	
	Integer f_i_Sid;
	String f_c_Sname;
	double f_d_Ssprice;
	Integer f_i_Snum;
	Integer f_i_Stotal;
	String f_c_Simagpath;
	String f_c_Stype;
	String f_c_Saddress;
	String f_c_Sdescription;
	int f_c_Sactivity;
	
	public Integer getF_i_Sid() {
		return f_i_Sid;
	}
	public void setF_i_Sid(Integer f_i_Sid) {
		this.f_i_Sid = f_i_Sid;
	}
	public String getF_c_Sname() {
		return f_c_Sname;
	}
	public void setF_c_Sname(String f_c_Sname) {
		this.f_c_Sname = f_c_Sname;
	}
	public double getF_d_Ssprice() {
		return f_d_Ssprice;
	}
	public void setF_d_Ssprice(double f_d_Ssprice) {
		this.f_d_Ssprice = f_d_Ssprice;
	}
	public Integer getF_i_Snum() {
		return f_i_Snum;
	}
	public void setF_i_Snum(Integer f_i_Snum) {
		this.f_i_Snum = f_i_Snum;
	}
	public Integer getF_i_Stotal() {
		return f_i_Stotal;
	}
	public void setF_i_Stotal(Integer f_i_Stotal) {
		this.f_i_Stotal = f_i_Stotal;
	}
	public String getF_c_Simagpath() {
		return f_c_Simagpath;
	}
	public void setF_c_Simagpath(String f_c_Simagpath) {
		this.f_c_Simagpath = f_c_Simagpath;
	}
	public String getF_c_Stype() {
		return f_c_Stype;
	}
	public void setF_c_Stype(String f_c_Stype) {
		this.f_c_Stype = f_c_Stype;
	}
	public String getF_c_Saddress() {
		return f_c_Saddress;
	}
	public void setF_c_Saddress(String f_c_Saddress) {
		this.f_c_Saddress = f_c_Saddress;
	}
	public String getF_c_Sdescription() {
		return f_c_Sdescription;
	}
	public void setF_c_Sdescription(String f_c_Sdescription) {
		this.f_c_Sdescription = f_c_Sdescription;
	}
	public int getF_c_Sactivity() {
		return f_c_Sactivity;
	}
	public void setF_c_Sactivity(int f_c_Sactivity) {
		this.f_c_Sactivity = f_c_Sactivity;
	}
	
	public ShopInfo(Integer f_i_Sid, String f_c_Sname, double f_d_Ssprice,
			Integer f_i_Snum, Integer f_i_Stotal, String f_c_Simagpath,
			String f_c_Stype, String f_c_Saddress, String f_c_Sdescription,int f_c_Sactivity) {
		super();
		this.f_i_Sid = f_i_Sid;
		this.f_c_Sname = f_c_Sname;
		this.f_d_Ssprice = f_d_Ssprice;
		this.f_i_Snum = f_i_Snum;
		this.f_i_Stotal = f_i_Stotal;
		this.f_c_Simagpath = f_c_Simagpath;
		this.f_c_Stype = f_c_Stype;
		this.f_c_Saddress = f_c_Saddress;
		this.f_c_Sdescription = f_c_Sdescription;
		this.f_c_Sactivity = f_c_Sactivity;
	}
	public ShopInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ShopInfo [f_i_Sid=" + f_i_Sid + ", f_c_Sname=" + f_c_Sname
				+ ", f_d_Ssprice=" + f_d_Ssprice + ", f_i_Snum=" + f_i_Snum
				+ ", f_i_Stotal=" + f_i_Stotal + ", f_c_Simagpath="
				+ f_c_Simagpath + ", f_c_Stype=" + f_c_Stype
				+ ", f_c_Saddress=" + f_c_Saddress + ", f_c_Sdescription="
				+ f_c_Sdescription + "]";
	}
	

}
