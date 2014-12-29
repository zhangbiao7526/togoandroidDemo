package com.example.togodemo.mode;

import java.io.Serializable;
import java.sql.Timestamp;

public class Indent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ShopInfo shopInfo;
	
	int f_i_Iid;
	int f_i_userid;
	String f_c_username;
	String f_c_Iconsignee;
	String f_c_Idelivery;
	String f_c_Iaddress;
	String f_c_Iphone;
	int f_i_Ishopid;
	String f_c_Ishopname;
	int f_i_Inum;
	Double f_d_Ssprice;
//	String f_c_Stype;
//	String f_c_Simagpath;
	Timestamp f_d_Idate; 
	double f_c_IshouldPay;
	int f_c_Istatus;
	

	public ShopInfo getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(ShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
	public int getF_i_Iid() {
		return f_i_Iid;
	}
	public void setF_i_Iid(int f_i_Iid) {
		this.f_i_Iid = f_i_Iid;
	}
	
	public String getF_c_Iconsignee() {
		return f_c_Iconsignee;
	}
	public void setF_c_Iconsignee(String f_c_Iconsignee) {
		this.f_c_Iconsignee = f_c_Iconsignee;
	}
	
	public String getF_c_Idelivery() {
		return f_c_Idelivery;
	}
	public void setF_c_Idelivery(String f_c_Idelivery) {
		this.f_c_Idelivery = f_c_Idelivery;
	}
	public String getF_c_Iaddress() {
		return f_c_Iaddress;
	}
	public void setF_c_Iaddress(String f_c_Iaddress) {
		this.f_c_Iaddress = f_c_Iaddress;
	}
	public String getF_c_Iphone() {
		return f_c_Iphone;
	}
	public void setF_c_Iphone(String f_c_Iphone) {
		this.f_c_Iphone = f_c_Iphone;
	}
	

	public int getF_i_userid() {
		return f_i_userid;
	}
	public void setF_i_userid(int f_i_userid) {
		this.f_i_userid = f_i_userid;
	}
	public Timestamp getF_d_Idate() {
		return f_d_Idate;
	}
	public void setF_d_Idate(Timestamp f_d_Idate) {
		this.f_d_Idate = f_d_Idate;
	}
	public int getF_i_Ishopid() {
		return f_i_Ishopid;
	}
	public void setF_i_Ishopid(int f_i_Ishopid) {
		this.f_i_Ishopid = f_i_Ishopid;
	}
	
		
	public Double getF_d_Ssprice() {
		return f_d_Ssprice;
	}
	public void setF_d_Ssprice(Double f_d_Ssprice) {
		this.f_d_Ssprice = f_d_Ssprice;
	}
	public int getF_i_Inum() {
		return f_i_Inum;
	}
	public void setF_i_Inum(int f_i_Inum) {
		this.f_i_Inum = f_i_Inum;
	}
	
	
	public String getF_c_Ishopname() {
		return f_c_Ishopname;
	}
	public void setF_c_Ishopname(String f_c_Ishopname) {
		this.f_c_Ishopname = f_c_Ishopname;
	}
	public Indent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getF_c_IshouldPay() {
		return f_c_IshouldPay;
	}
	public void setF_c_IshouldPay(double f_c_IshouldPay) {
		this.f_c_IshouldPay = f_c_IshouldPay;
	}
	public int getF_c_Istatus() {
		return f_c_Istatus;
	}
	public void setF_c_Istatus(int f_c_Istatus) {
		this.f_c_Istatus = f_c_Istatus;
	}
	
	
	public String getF_c_username() {
		return f_c_username;
	}
	public void setF_c_username(String f_c_username) {
		this.f_c_username = f_c_username;
	}
	public Indent(ShopInfo shopInfo, int f_i_Iid, int f_i_userid,
			String f_c_username, String f_c_Iconsignee, String f_c_Idelivery,
			String f_c_Iaddress, String f_c_Iphone, int f_i_Ishopid,
			String f_c_Ishopname, int f_i_Inum, Timestamp f_d_Idate,
			double f_c_IshouldPay, int f_c_Istatus) {
		super();
		this.shopInfo = shopInfo;
		this.f_i_Iid = f_i_Iid;
		this.f_i_userid = f_i_userid;
		this.f_c_username = f_c_username;
		this.f_c_Iconsignee = f_c_Iconsignee;
		this.f_c_Idelivery = f_c_Idelivery;
		this.f_c_Iaddress = f_c_Iaddress;
		this.f_c_Iphone = f_c_Iphone;
		this.f_i_Ishopid = f_i_Ishopid;
		this.f_c_Ishopname = f_c_Ishopname;
		this.f_i_Inum = f_i_Inum;
		this.f_d_Idate = f_d_Idate;
		this.f_c_IshouldPay = f_c_IshouldPay;
		this.f_c_Istatus = f_c_Istatus;
	}
	@Override
	public String toString() {
		return "Indent [shopInfo=" + shopInfo + ", f_i_Iid=" + f_i_Iid
				+ ", f_i_userid=" + f_i_userid + ", f_c_username="
				+ f_c_username + ", f_c_Iconsignee=" + f_c_Iconsignee
				+ ", f_c_Idelivery=" + f_c_Idelivery + ", f_c_Iaddress="
				+ f_c_Iaddress + ", f_c_Iphone=" + f_c_Iphone
				+ ", f_i_Ishopid=" + f_i_Ishopid + ", f_c_Ishopname="
				+ f_c_Ishopname + ", f_i_Inum=" + f_i_Inum + ", f_d_Idate="
				+ f_d_Idate + ", f_c_IshouldPay=" + f_c_IshouldPay
				+ ", f_c_Istatus=" + f_c_Istatus 
				+ ", f_d_Ssprice="+shopInfo.getF_d_Ssprice()+", f__c_Stype="+shopInfo.getF_c_Stype()+"]";
	}

	
//	@Override
//	public int describeContents() {
//		return 0;
//	}
//	@Override
//	public void writeToParcel(Parcel dest, int flags) {
//		dest.writeInt(f_i_Iid);
//		dest.writeInt(f_i_userid);
//		dest.writeString(f_c_username);
//		dest.writeString(f_c_Iconsignee);
//		dest.writeString(f_c_Idelivery);
//		dest.writeString(f_c_Iaddress);
//		dest.writeString(f_c_Iphone);
//		dest.writeInt(f_i_Ishopid);
////		dest.writeValue(shopInfo);
//		dest.writeDouble(f_c_IshouldPay);
//		dest.writeInt(f_c_Istatus);
//	}

//	public Indent(Parcel source) {
//		f_i_Iid = source.readInt();
//		f_i_userid = source.readInt();
//		f_c_username = source.readString();
//		f_c_Iconsignee = source.readString();
//		f_c_Idelivery = source.readString();
//		f_c_Iaddress = source.readString();
//		f_c_Iphone = source.readString();
//		f_i_Ishopid = source.readInt();
//	}

//	public static final Parcelable.Creator<Indent> CREATOR = new Creator<Indent>() {
//
//		@Override
//		public Indent[] newArray(int size) {
//
//			return new Indent[size];
//		}
//
//		@Override
//		public Indent createFromParcel(Parcel source) {
//
//			return new Indent(source);
//		}
//	};

	
}

