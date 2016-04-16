package com.team1.acdeal.vo;

import java.util.Date;

/* 화면 출력 */
public class AcDealDTO {
	int dealId; //Deal ID 거래 아이디
	String dealNote;	//거래내용
	int dealSum;	//금액
	Date dealDate;	//거래날짜	
	//int acId; //AC ID 통장아이디
	String categoryName;	//(기자재, 잡화, 도서)
	String typeName;	//구분 (입금,출금)
	
	public int getDealId() {
		return dealId;
	}
	public void setDealId(int dealId) {
		this.dealId = dealId;
	}
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
