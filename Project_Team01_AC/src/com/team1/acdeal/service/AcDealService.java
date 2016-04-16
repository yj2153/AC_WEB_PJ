package com.team1.acdeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team1.acdeal.dao.AcDealDAO;
import com.team1.acdeal.vo.AcCategoryDTO;
import com.team1.acdeal.vo.AcDTO;
import com.team1.acdeal.vo.AcDealDTO;
import com.team1.acdeal.vo.AcDealVOList;
import com.team1.acdeal.vo.AcTypeDTO;

@Service
public class AcDealService {

	@Autowired
	AcDealDAO adDAO;

	// 통잔잔액 호출
	@Transactional
	public AcDTO acSelect() {
		AcDTO dto = adDAO.acSelect();
		return dto;
	}//acSelect

	// 통장 잔액 수정
	public int acUpdate(int acBalance) {
		int res = 0;
		AcDTO dto = adDAO.acSelect();
		int sum = dto.getAcBalance() + (acBalance);
		res = adDAO.acUpdate(sum);
		return res;
	}//acUpdate

	// 타입정보를 리턴.
	public List<AcTypeDTO> getType() {
		List<AcTypeDTO> typeDTO = adDAO.getType();
		return typeDTO;
	}// getType

	// 카테고리정보를 리턴.
	public List<AcCategoryDTO> getCategory() {
		List<AcCategoryDTO> typeDTO = adDAO.getCategory();
		return typeDTO;
	}// getCategory

	// 카테고리 수정
	public int acCategoryUpdate(AcCategoryDTO categoryDTO) {
		int res = 0;
		res = adDAO.acCategoryUpdate(categoryDTO);
		return res;
	}// acCategoryUpdate

	// 카테고리 삭제
	public int acCategoryDelete(int categoryId) {
		int res = 0;
		res = adDAO.acCategoryDelete(categoryId);
		return res;
	}// acCategoryDelete

	// 카테고리 추가
	public int acCategoryInsert(String categoryName) {
		int res = 0;
		res = adDAO.acCategoryInsert(categoryName);
		return res;
	}// acCategoryInsert

	// 거래 내역 추가
	@Transactional
	public int acDealReg(AcDealVOList dealVOList) {
		int res = 0;
		int depositSum = 0;
		int withdrawSum =0;
		//내역추가
		for (int i = 0; i < dealVOList.getDealvoList().size(); i++) {
			if (dealVOList.getDealvoList().get(i).getTypeId() != 0
					&& dealVOList.getDealvoList().get(i).getCategoryId() != 0
					&& dealVOList.getDealvoList().get(i).getDealNote() != null
					&& dealVOList.getDealvoList().get(i).getDealNote().length() > 0
					&& dealVOList.getDealvoList().get(i).getDealSum() != 0) {

				//입금인지 출금인지에 따라 
				if(dealVOList.getDealvoList().get(i).getTypeId() == 1)
					depositSum += dealVOList.getDealvoList().get(i).getDealSum();
				else if(dealVOList.getDealvoList().get(i).getTypeId() == 2)
					withdrawSum += dealVOList.getDealvoList().get(i).getDealSum();
				
				// 모든 정보가 들어있는 객체만 사용하기
				res = adDAO.acDealReg(dealVOList.getDealvoList().get(i));
			} // end if
		} // end for
		
		//통장 갱신
		int balance = depositSum -  withdrawSum;
		acUpdate(balance);
		
		return res;
	}// acDealReg

	// 거래내역 삭제
	public int acDealDel(int dealId) {
		int res = 0;
		res = adDAO.acDealDel(dealId);
		return res;
	}// acDealDel

	// 거래내역 수정
	@Transactional
	public int acDealUpdate(AcDealDTO dealDTO) {
		int res = 0;
		//수정전의 데이터 추출
		AcDealDTO beforDAO = adDAO.getDeal(dealDTO.getDealId());
		//데이터 갱신
		res = adDAO.acDealUpdate(dealDTO);
		//통장 갱신
		int balance =  dealDTO.getDealSum() - beforDAO.getDealSum();
		acUpdate(balance);
		
		return res;
	}// acDealUpdate

	// 총 갯수 구하기
	public int getDealCount() {
		int res = 0;
		res = adDAO.getDealCount();
		return res;
	}// getDealCount

	// 거래내역 리스트 출력 (10개)
	@Transactional
	public List<AcDealDTO> getDealList(int s) {
		List<AcDealDTO> list = adDAO.getDealList(s);
		return list;
	}// getDealList
	
	//10개만 읽어오기
	@Transactional
	public List<AcDealDTO> getDealList10(int s){
		List<AcDealDTO> list = adDAO.getDealList10(s);
		return list;
	}
}
