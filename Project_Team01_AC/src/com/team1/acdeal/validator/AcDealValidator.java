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
		AcDealVOList list = (AcDealVOList) arg0;	//리스트 값 읽어오기
		String strError = "";	//에러 메세지 출력 (에러 properties 로 수정하기)
		int iSuccess = -1;	//성공값

		// 날짜가 설정되어 있지 않다면
		if (list.getDealDate() == null || list.getDealDate().length() <= 0) {
			strError = "날짜를 선택해 주세요.";
			iSuccess = 1;	//에러가 있음을 알린다.
		} else {
			for (int i = 0; i < list.getDealvoList().size(); i++) {
				// 하나라도 제대로 되어 있다면
				if (list.getDealvoList().get(i).getTypeId() != 0 && list.getDealvoList().get(i).getCategoryId() != 0
						&& list.getDealvoList().get(i).getDealNote() != null
						&& list.getDealvoList().get(i).getDealNote().length() > 0
						&& list.getDealvoList().get(i).getDealSum() != 0) {
					iSuccess = 100; //에러가 없음을 알린다.
				}//end if
				// 구분 O 또는 카테고리 O 또는 상세내용 O 또는 금액 O
				else if (list.getDealvoList().get(i).getTypeId() != 0
						|| list.getDealvoList().get(i).getCategoryId() != 0
						|| list.getDealvoList().get(i).getDealNote() != null
								&& list.getDealvoList().get(i).getDealNote().length() > 0
						|| list.getDealvoList().get(i).getDealSum() != 0) {

					System.out.println(i + "번째 ======================= ");
					System.out.println(list.getDealvoList().get(i).getTypeId() + " : typeId");
					System.out.println(list.getDealvoList().get(i).getCategoryId() + " : categoryId");
					System.out.println(list.getDealvoList().get(i).getDealNote() + " : DealNote");
					System.out.println(list.getDealvoList().get(i).getDealSum() + " : DealSum");

					// 구분이 선택 안되었다면
					if (list.getDealvoList().get(i).getTypeId() == 0) {
						strError = "구분 선택 바람";
						iSuccess = 2; 	//에러가 있음을 알린다.
						// 카테고리가 선택이 안되었다면
					} else if (list.getDealvoList().get(i).getCategoryId() == 0) {
						strError = "카테고리 선택 바람";
						iSuccess = 2;	//에러가 있음을 알린다.
						// 상세정보가 null 이라면
					} else if (list.getDealvoList().get(i).getDealNote() == null || list.getDealvoList().get(i).getDealNote().length() <= 0) {
						strError = "상세내역 기입 바람";
						iSuccess = 2;	//에러가 있음을 알린다.
						// 금액이 설정 안되었다면
					} else if (list.getDealvoList().get(i).getDealSum() == 0) {
						strError = "금액 입력 바람";
						iSuccess = 2;	//에러가 있음을 알린다.
					}//end else
				}//end else if

				// 에러도 없고 성공도 없으면
				if (i == (list.getDealvoList().size() - 1) && iSuccess == (-1)) {
					strError = "내용을 입력해주세요.";
					iSuccess = 3;
				}//end if
			}//end for
		}//end else

		// 에러가 있고, 성공이 없다면
		if (iSuccess >= 0 && iSuccess < 100) {
			System.out.println(strError);
			arg1.rejectValue("dealvoList", strError);
		}//end if
	}//validate

}
