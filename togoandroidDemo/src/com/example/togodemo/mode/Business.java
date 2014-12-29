package com.example.togodemo.mode;

public class Business {
	Integer f_i_Bid;
	Integer f_i_Sid;
	double f_m_Bminprice;
	double f_m_Bdiscprices;
	Integer f_i_Bexemption;
	String f_d_Bstart;
	String f_d_Bend;
	
	public Integer getF_i_Bid() {
		return f_i_Bid;
	}
	public void setF_i_Bid(Integer f_i_Bid) {
		this.f_i_Bid = f_i_Bid;
	}
	public Integer getF_i_Sid() {
		return f_i_Sid;
	}
	public void setF_i_Sid(Integer f_i_Sid) {
		this.f_i_Sid = f_i_Sid;
	}
	public double getF_m_Bminprice() {
		return f_m_Bminprice;
	}
	public void setF_m_Bminprice(double f_m_Bminprice) {
		this.f_m_Bminprice = f_m_Bminprice;
	}
	public double getF_m_Bdiscprices() {
		return f_m_Bdiscprices;
	}
	public void setF_m_Bdiscprices(double f_m_Bdiscprices) {
		this.f_m_Bdiscprices = f_m_Bdiscprices;
	}
	public Integer getF_i_Bexemption() {
		return f_i_Bexemption;
	}
	public void setF_i_Bexemption(Integer f_i_Bexemption) {
		this.f_i_Bexemption = f_i_Bexemption;
	}
	public String getF_d_Bstart() {
		return f_d_Bstart;
	}
	public void setF_d_Bstart(String f_d_Bstart) {
		this.f_d_Bstart = f_d_Bstart;
	}
	public String getF_d_Bend() {
		return f_d_Bend;
	}
	public void setF_d_Bend(String f_d_Bend) {
		this.f_d_Bend = f_d_Bend;
	}
	
	public Business() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Business( Integer f_i_Sid, double f_m_Bminprice,
			double f_m_Bdiscprices, Integer f_i_Bexemption, String f_d_Bstart,
			String f_d_Bend) {
		super();
		
		this.f_i_Sid = f_i_Sid;
		this.f_m_Bminprice = f_m_Bminprice;
		this.f_m_Bdiscprices = f_m_Bdiscprices;
		this.f_i_Bexemption = f_i_Bexemption;
		this.f_d_Bstart = f_d_Bstart;
		this.f_d_Bend = f_d_Bend;
	}
	
	
	

}
