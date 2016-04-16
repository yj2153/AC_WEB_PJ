package com.team1.acdeal.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team1.acdeal.service.AcDealService;
import com.team1.acdeal.vo.AcCategoryDTO;

@Controller
public class AcCategoryController {
	@Autowired
	AcDealService adService;

	// 조회 화면 이동
	@RequestMapping("acCategory.ListView.sw")
	public String acDealType(Model model) throws UnsupportedEncodingException {
		System.out.println("acCategory.ListView.sw 호출");
		model.addAttribute("includeName", "/hanbitsw/acDeal/view/acCategoryList.jsp");
		return "/hanbitsw/acDeal/view/acDealIndex.jsp";
	}// acDealType
	
	// 조회 데이터 JSON
	@ResponseBody
	@RequestMapping(value = "acCategory.List.sw", produces = "application/json;charset=UTF-8")
	public String acDealShowJson(HttpServletRequest request) {
		System.out.println("acCategory.List.sw 호출");
		String current_page = request.getParameter("page"); // 현재페이지
		int MAXSIZE = 10; // 10개씩 화면에 노출
		int listCnt = 0; // 총 리스트 갯수 구하기

		// 카테고리 데이터 읽어오기
		List<AcCategoryDTO> categoryDTO = adService.getCategory();
		listCnt = categoryDTO.size();
		// VALUE값 셋팅하기
		JSONArray jsonList = new JSONArray();

		// CATEGORY 값
		for (int i = 0; i < categoryDTO.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("categoryId", categoryDTO.get(i).getCategoryId());
			jsonObj.put("categoryName", categoryDTO.get(i).getCategoryName());
			jsonList.add(jsonObj);
		} // end for

		int maxPage = (int) (listCnt / MAXSIZE + 0.95); // 페이지로 구하면 몇 페이지?
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", jsonList); // 현재페이지의 목록데이터
		jsonObj.put("page", current_page); // 현재페이지
		jsonObj.put("total", maxPage); // 총페이지수
		jsonObj.put("records", MAXSIZE); // 총글목록수

		return jsonObj.toJSONString();
	}// acDealShowJson

	// Grid요청
	@RequestMapping("/acCategoryGrid.EditAction.sw")
	public String acDealDel(HttpServletRequest request) throws ParseException {
		int res = -1;
		String oper = request.getParameter("oper");
		System.out.println("acCategoryGrid.EditAction.sw(" + oper + ") 요청");

		// 삭제요청
		if (oper.equals("del")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			res = adService.acCategoryDelete(categoryId);
			// 수정요청
		} else if (oper.equals("edit")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			AcCategoryDTO cDTO = new AcCategoryDTO();
			cDTO.setCategoryId(categoryId);
			cDTO.setCategoryName(request.getParameter("categoryName"));
			res = adService.acCategoryUpdate(cDTO);
			//내용 추가
		}else if(oper.equals("add")){
			res = adService.acCategoryInsert(request.getParameter("categoryName"));
		}
		// acDeal.Del.sw
		if (res == -1) {//
			System.out.println("acCategory.EditAction.sw 실패");
		} // end if
		return "acCategory.ListView.sw";
	}// acDealDel
}
