package com.team1.acdeal.vo;

import java.util.Date;

/* ������ ���� */
public class AcDealVO {
	//int dealId; //Deal ID �ŷ� ���̵�
	String dealNote;	//�ŷ�����
	int dealSum;	//�ݾ�
	Date dealDate;	//�ŷ���¥	
	//int acId; //AC ID ������̵�
	int categoryId;	//(������, ��ȭ, ����)
	int typeId;	//���� (�Ա�,���)
	
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
