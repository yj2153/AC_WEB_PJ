package com.team1.acdeal.vo;

import java.util.Date;

/* ȭ�� ��� */
public class AcDealDTO {
	int dealId; //Deal ID �ŷ� ���̵�
	String dealNote;	//�ŷ�����
	int dealSum;	//�ݾ�
	Date dealDate;	//�ŷ���¥	
	//int acId; //AC ID ������̵�
	String categoryName;	//(������, ��ȭ, ����)
	String typeName;	//���� (�Ա�,���)
	
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
