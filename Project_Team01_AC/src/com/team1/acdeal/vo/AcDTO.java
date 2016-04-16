package com.team1.acdeal.vo;

public class AcDTO {
	int acId;	//계좌번호
	String acBankname; //은행명
	int acBalance;	//금액
	public int getAcId() {
		return acId;
	}
	public void setAcId(int acId) {
		this.acId = acId;
	}
	public String getAcBankname() {
		return acBankname;
	}
	public void setAcBankname(String acBankname) {
		this.acBankname = acBankname;
	}
	public int getAcBalance() {
		return acBalance;
	}
	public void setAcBalance(int acBalance) {
		this.acBalance = acBalance;
	}
}
