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

	@Autowired // 자동 주입
	SqlSessionTemplate sessionTemplate;
	
	//통잔잔액 호출
	public AcDTO acSelect(){
		AcDTO dto = sessionTemplate.selectOne("ac.ns.ac_select");
		return dto;
	}//acSelect
	
	//통장 잔액 수정
	public int acUpdate(int acBalance){
		int res = 0;
		res = sessionTemplate.update("ac.ns.ac_update", acBalance);
		return res;
	}//acUpdate

	//거래 내역 추가
	public int acDealReg(AcDealVO dealVO){
		int res = 0;
		res = sessionTemplate.insert("ac.ns.acDeal_insert", dealVO);

		return res;
	}//acDealReg
	
	//거래 내역 수정
	public int acDealUpdate(AcDealDTO dealDTO){
		int res = 0;
		res = sessionTemplate.update("ac.ns.acDeal_update", dealDTO);
		return res;
	}//acDelUpdate
	
	//거래내역 삭제
	@Transactional
	public int acDealDel(int dealId){
		int res = 0;
		res = sessionTemplate.delete("ac.ns.acDeal_delete", dealId);
		return res;
	}//acDealDel
	
	//거래내역 총 갯수
	@Transactional
	public int getDealCount(){
		int res = 0;
		res = sessionTemplate.selectOne("ac.ns.acDeal_count");
		return res;
	}
	
	//거래 내역 1개
	public AcDealDTO getDeal(int dealId){
		AcDealDTO dto = sessionTemplate.selectOne("ac.ns.acDeal_select_one",dealId);
		return dto;
	}	//getDeal
	
	//거래 내역 리스트 (10개)
	public List<AcDealDTO> getDealList10(int s){
		List<AcDealDTO> list = sessionTemplate.selectList("ac.ns.acDeal_select10",s);
		return list;
	}
	//거래내역 리스트 전부
	public List<AcDealDTO> getDealList(int s){
		List<AcDealDTO> list = sessionTemplate.selectList("ac.ns.acDeal_select",s);
		return list;
	}
	
	//타입정보를 리턴.
	@Transactional
	public List<AcTypeDTO> getType(){
		List<AcTypeDTO> typeDTO = sessionTemplate.selectList("ac.ns.acType_select");
		return typeDTO;
	}//getType
	
	//카테고리정보를 리턴.
	@Transactional
	public List<AcCategoryDTO> getCategory(){
		List<AcCategoryDTO> typeDTO = sessionTemplate.selectList("ac.ns.acCategory_select");
		return typeDTO;
	}//getCategory
	
	//카테고리 수정
	@Transactional
	public int acCategoryUpdate(AcCategoryDTO categoryDTO){
		int res = 0;
		res = sessionTemplate.update("ac.ns.acCategory_update",categoryDTO);
		return res;
	}//acCategoryUpdate
	
	//카테고리 삭제
	@Transactional
	public  int acCategoryDelete(int categoryId){
		int res = 0;
		res = sessionTemplate.delete("ac.ns.acCategory_delete",categoryId);
		return res;
	}//acCategoryDelete
	
	//카테고리 추가
	@Transactional
	public int acCategoryInsert(String categoryName){
		int res = 0;
		res = sessionTemplate.insert("ac.ns.acCategory_Insert",categoryName);
		return res;
	}//acCategoryInsert
}
