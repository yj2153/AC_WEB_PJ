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

	// �����ܾ� ȣ��
	@Transactional
	public AcDTO acSelect() {
		AcDTO dto = adDAO.acSelect();
		return dto;
	}//acSelect

	// ���� �ܾ� ����
	public int acUpdate(int acBalance) {
		int res = 0;
		AcDTO dto = adDAO.acSelect();
		int sum = dto.getAcBalance() + (acBalance);
		res = adDAO.acUpdate(sum);
		return res;
	}//acUpdate

	// Ÿ�������� ����.
	public List<AcTypeDTO> getType() {
		List<AcTypeDTO> typeDTO = adDAO.getType();
		return typeDTO;
	}// getType

	// ī�װ������� ����.
	public List<AcCategoryDTO> getCategory() {
		List<AcCategoryDTO> typeDTO = adDAO.getCategory();
		return typeDTO;
	}// getCategory

	// ī�װ� ����
	public int acCategoryUpdate(AcCategoryDTO categoryDTO) {
		int res = 0;
		res = adDAO.acCategoryUpdate(categoryDTO);
		return res;
	}// acCategoryUpdate

	// ī�װ� ����
	public int acCategoryDelete(int categoryId) {
		int res = 0;
		res = adDAO.acCategoryDelete(categoryId);
		return res;
	}// acCategoryDelete

	// ī�װ� �߰�
	public int acCategoryInsert(String categoryName) {
		int res = 0;
		res = adDAO.acCategoryInsert(categoryName);
		return res;
	}// acCategoryInsert

	// �ŷ� ���� �߰�
	@Transactional
	public int acDealReg(AcDealVOList dealVOList) {
		int res = 0;
		int depositSum = 0;
		int withdrawSum =0;
		//�����߰�
		for (int i = 0; i < dealVOList.getDealvoList().size(); i++) {
			if (dealVOList.getDealvoList().get(i).getTypeId() != 0
					&& dealVOList.getDealvoList().get(i).getCategoryId() != 0
					&& dealVOList.getDealvoList().get(i).getDealNote() != null
					&& dealVOList.getDealvoList().get(i).getDealNote().length() > 0
					&& dealVOList.getDealvoList().get(i).getDealSum() != 0) {

				//�Ա����� ��������� ���� 
				if(dealVOList.getDealvoList().get(i).getTypeId() == 1)
					depositSum += dealVOList.getDealvoList().get(i).getDealSum();
				else if(dealVOList.getDealvoList().get(i).getTypeId() == 2)
					withdrawSum += dealVOList.getDealvoList().get(i).getDealSum();
				
				// ��� ������ ����ִ� ��ü�� ����ϱ�
				res = adDAO.acDealReg(dealVOList.getDealvoList().get(i));
			} // end if
		} // end for
		
		//���� ����
		int balance = depositSum -  withdrawSum;
		acUpdate(balance);
		
		return res;
	}// acDealReg

	// �ŷ����� ����
	public int acDealDel(int dealId) {
		int res = 0;
		res = adDAO.acDealDel(dealId);
		return res;
	}// acDealDel

	// �ŷ����� ����
	@Transactional
	public int acDealUpdate(AcDealDTO dealDTO) {
		int res = 0;
		//�������� ������ ����
		AcDealDTO beforDAO = adDAO.getDeal(dealDTO.getDealId());
		//������ ����
		res = adDAO.acDealUpdate(dealDTO);
		//���� ����
		int balance =  dealDTO.getDealSum() - beforDAO.getDealSum();
		acUpdate(balance);
		
		return res;
	}// acDealUpdate

	// �� ���� ���ϱ�
	public int getDealCount() {
		int res = 0;
		res = adDAO.getDealCount();
		return res;
	}// getDealCount

	// �ŷ����� ����Ʈ ��� (10��)
	@Transactional
	public List<AcDealDTO> getDealList(int s) {
		List<AcDealDTO> list = adDAO.getDealList(s);
		return list;
	}// getDealList
	
	//10���� �о����
	@Transactional
	public List<AcDealDTO> getDealList10(int s){
		List<AcDealDTO> list = adDAO.getDealList10(s);
		return list;
	}
}
