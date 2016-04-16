package com.team1.acdeal.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team1.acdeal.vo.AcCategoryDTO;
import com.team1.acdeal.vo.AcDTO;
import com.team1.acdeal.vo.AcDealDTO;
import com.team1.acdeal.vo.AcDealVO;
import com.team1.acdeal.vo.AcTypeDTO;

@Repository
public class AcDealDAO {

	@Autowired // �ڵ� ����
	SqlSessionTemplate sessionTemplate;
	
	//�����ܾ� ȣ��
	public AcDTO acSelect(){
		AcDTO dto = sessionTemplate.selectOne("ac.ns.ac_select");
		return dto;
	}//acSelect
	
	//���� �ܾ� ����
	public int acUpdate(int acBalance){
		int res = 0;
		res = sessionTemplate.update("ac.ns.ac_update", acBalance);
		return res;
	}//acUpdate

	//�ŷ� ���� �߰�
	public int acDealReg(AcDealVO dealVO){
		int res = 0;
		res = sessionTemplate.insert("ac.ns.acDeal_insert", dealVO);

		return res;
	}//acDealReg
	
	//�ŷ� ���� ����
	public int acDealUpdate(AcDealDTO dealDTO){
		int res = 0;
		res = sessionTemplate.update("ac.ns.acDeal_update", dealDTO);
		return res;
	}//acDelUpdate
	
	//�ŷ����� ����
	@Transactional
	public int acDealDel(int dealId){
		int res = 0;
		res = sessionTemplate.delete("ac.ns.acDeal_delete", dealId);
		return res;
	}//acDealDel
	
	//�ŷ����� �� ����
	@Transactional
	public int getDealCount(){
		int res = 0;
		res = sessionTemplate.selectOne("ac.ns.acDeal_count");
		return res;
	}
	
	//�ŷ� ���� 1��
	public AcDealDTO getDeal(int dealId){
		AcDealDTO dto = sessionTemplate.selectOne("ac.ns.acDeal_select_one",dealId);
		return dto;
	}	//getDeal
	
	//�ŷ� ���� ����Ʈ (10��)
	public List<AcDealDTO> getDealList10(int s){
		List<AcDealDTO> list = sessionTemplate.selectList("ac.ns.acDeal_select10",s);
		return list;
	}
	//�ŷ����� ����Ʈ ����
	public List<AcDealDTO> getDealList(int s){
		List<AcDealDTO> list = sessionTemplate.selectList("ac.ns.acDeal_select",s);
		return list;
	}
	
	//Ÿ�������� ����.
	@Transactional
	public List<AcTypeDTO> getType(){
		List<AcTypeDTO> typeDTO = sessionTemplate.selectList("ac.ns.acType_select");
		return typeDTO;
	}//getType
	
	//ī�װ������� ����.
	@Transactional
	public List<AcCategoryDTO> getCategory(){
		List<AcCategoryDTO> typeDTO = sessionTemplate.selectList("ac.ns.acCategory_select");
		return typeDTO;
	}//getCategory
	
	//ī�װ� ����
	@Transactional
	public int acCategoryUpdate(AcCategoryDTO categoryDTO){
		int res = 0;
		res = sessionTemplate.update("ac.ns.acCategory_update",categoryDTO);
		return res;
	}//acCategoryUpdate
	
	//ī�װ� ����
	@Transactional
	public  int acCategoryDelete(int categoryId){
		int res = 0;
		res = sessionTemplate.delete("ac.ns.acCategory_delete",categoryId);
		return res;
	}//acCategoryDelete
	
	//ī�װ� �߰�
	@Transactional
	public int acCategoryInsert(String categoryName){
		int res = 0;
		res = sessionTemplate.insert("ac.ns.acCategory_Insert",categoryName);
		return res;
	}//acCategoryInsert
}
