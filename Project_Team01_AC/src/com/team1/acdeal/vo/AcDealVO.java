package com.team1.acdeal.vo;

import java.util.Date;

/* 서버로 전송 */
public class AcDealVO {
	//int dealId; //Deal ID 거래 아이디
	String dealNote;	//거래내용
	int dealSum;	//금액
	Date dealDate;	//거래날짜	
	//int acId; //AC ID 통장아이디
	int categoryId;	//(기자재, 잡화, 도서)
	int typeId;	//구분 (입금,출금)
	
	public String getDealNote() {
		return dealNote;
	}
	public void setDealNote(String dealNote) {
		this.dealNote = dealNote;
	}
	public int getDealSum() {
		return dealSum;
	}
	public void setDealSum(int dealSum) {
		this.dealSum = dealSum;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}
