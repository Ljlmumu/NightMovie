package com.yifu.wuliao.pay;

public class BeanGetIndentify extends BaseBean{
	String orderid;
	String paychannel;
	String regular;
	
	public BeanGetIndentify(){
		
	}
	
	public BeanGetIndentify(String orderid, String paychannel) {
		super();
		this.orderid = orderid;
		this.paychannel = paychannel;
		
	}

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getPaychannel() {
		return paychannel;
	}
	public void setPaychannel(String paychannel) {
		this.paychannel = paychannel;
	}
	public String getRegular() {
		return regular;
	}
	public void setRegular(String regular) {
		this.regular = regular;
	}
	
	

}
