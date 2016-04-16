package com.team1.acdeal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.team1.acdeal.vo.AcDealVOList;

public class AcDealValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return AcDealVOList.class.isAssignableFrom(arg0);
	}//supports

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		System.out.println("validator");
		AcDealVOList list = (AcDealVOList) arg0;	//����Ʈ �� �о����
		String strError = "";	//���� �޼��� ��� (���� properties �� �����ϱ�)
		int iSuccess = -1;	//������

		// ��¥�� �����Ǿ� ���� �ʴٸ�
		if (list.getDealDate() == null || list.getDealDate().length() <= 0) {
			strError = "��¥�� ������ �ּ���.";
			iSuccess = 1;	//������ ������ �˸���.
		} else {
			for (int i = 0; i < list.getDealvoList().size(); i++) {
				// �ϳ��� ����� �Ǿ� �ִٸ�
				if (list.getDealvoList().get(i).getTypeId() != 0 && list.getDealvoList().get(i).getCategoryId() != 0
						&& list.getDealvoList().get(i).getDealNote() != null
						&& list.getDealvoList().get(i).getDealNote().length() > 0
						&& list.getDealvoList().get(i).getDealSum() != 0) {
					iSuccess = 100; //������ ������ �˸���.
				}//end if
				// ���� O �Ǵ� ī�װ� O �Ǵ� �󼼳��� O �Ǵ� �ݾ� O
				else if (list.getDealvoList().get(i).getTypeId() != 0
						|| list.getDealvoList().get(i).getCategoryId() != 0
						|| list.getDealvoList().get(i).getDealNote() != null
								&& list.getDealvoList().get(i).getDealNote().length() > 0
						|| list.getDealvoList().get(i).getDealSum() != 0) {

					System.out.println(i + "��° ======================= ");
					System.out.println(list.getDealvoList().get(i).getTypeId() + " : typeId");
					System.out.println(list.getDealvoList().get(i).getCategoryId() + " : categoryId");
					System.out.println(list.getDealvoList().get(i).getDealNote() + " : DealNote");
					System.out.println(list.getDealvoList().get(i).getDealSum() + " : DealSum");

					// ������ ���� �ȵǾ��ٸ�
					if (list.getDealvoList().get(i).getTypeId() == 0) {
						strError = "���� ���� �ٶ�";
						iSuccess = 2; 	//������ ������ �˸���.
						// ī�װ��� ������ �ȵǾ��ٸ�
					} else if (list.getDealvoList().get(i).getCategoryId() == 0) {
						strError = "ī�װ� ���� �ٶ�";
						iSuccess = 2;	//������ ������ �˸���.
						// �������� null �̶��
					} else if (list.getDealvoList().get(i).getDealNote() == null || list.getDealvoList().get(i).getDealNote().length() <= 0) {
						strError = "�󼼳��� ���� �ٶ�";
						iSuccess = 2;	//������ ������ �˸���.
						// �ݾ��� ���� �ȵǾ��ٸ�
					} else if (list.getDealvoList().get(i).getDealSum() == 0) {
						strError = "�ݾ� �Է� �ٶ�";
						iSuccess = 2;	//������ ������ �˸���.
					}//end else
				}//end else if

				// ������ ���� ������ ������
				if (i == (list.getDealvoList().size() - 1) && iSuccess == (-1)) {
					strError = "������ �Է����ּ���.";
					iSuccess = 3;
				}//end if
			}//end for
		}//end else

		// ������ �ְ�, ������ ���ٸ�
		if (iSuccess >= 0 && iSuccess < 100) {
			System.out.println(strError);
			arg1.rejectValue("dealvoList", strError);
		}//end if
	}//validate

}
