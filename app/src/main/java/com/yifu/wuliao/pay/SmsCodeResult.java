package com.yifu.wuliao.pay;

public class SmsCodeResult extends BaseBean{
	
	private String mchStreamNo;
	private String moSmsCommand;
	private String moSmsPort;
	private String paychannel;

	public SmsCodeResult() {

	}

	public SmsCodeResult(int errorcode, String errormsg, String mchStreamNo,
			String moSmsCommand, String moSmsPort, String paychannel) {
		this.errorcode = errorcode;
		this.errormsg = errormsg;
		this.mchStreamNo = mchStreamNo;
		this.moSmsCommand = moSmsCommand;
		this.moSmsPort = moSmsPort;
		this.paychannel = paychannel;
	}

	public int geterrorcode() {
		return errorcode;
	}

	public void seterrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public String geterrormsg() {
		return errormsg;
	}

	public void seterrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getMchStreamNo() {
		return mchStreamNo;
	}

	public void setMchStreamNo(String mchStreamNo) {
		this.mchStreamNo = mchStreamNo;
	}

	public String getMoSmsCommand() {
		return moSmsCommand;
	}

	public void setMoSmsCommand(String moSmsCommand) {
		this.moSmsCommand = moSmsCommand;
	}

	public String getMoSmsPort() {
		return moSmsPort;
	}

	public void setMoSmsPort(String moSmsPort) {
		this.moSmsPort = moSmsPort;
	}

	public String getPaychannel() {
		return paychannel;
	}

	public void setPaychannel(String paychannel) {
		this.paychannel = paychannel;
	}

	@Override
	public String toString() {
		return "SmsCodeResult [errorcode=" + errorcode + ", errormsg=" + errormsg
				+ ", mchStreamNo=" + mchStreamNo + ", moSmsCommand="
				+ moSmsCommand + ", moSmsPort=" + moSmsPort + ", paychannel="
				+ paychannel + "]";
	}

}
