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

	// ��ȸ ȭ�� �̵�
	@RequestMapping("acCategory.ListView.sw")
	public String acDealType(Model model) throws UnsupportedEncodingException {
		System.out.println("acCategory.ListView.sw ȣ��");
		model.addAttribute("includeName", "/hanbitsw/acDeal/view/acCategoryList.jsp");
		return "/hanbitsw/acDeal/view/acDealIndex.jsp";
	}// acDealType
	
	// ��ȸ ������ JSON
	@ResponseBody
	@RequestMapping(value = "acCategory.List.sw", produces = "application/json;charset=UTF-8")
	public String acDealShowJson(HttpServletRequest request) {
		System.out.println("acCategory.List.sw ȣ��");
		String current_page = request.getParameter("page"); // ����������
		int MAXSIZE = 10; // 10���� ȭ�鿡 ����
		int listCnt = 0; // �� ����Ʈ ���� ���ϱ�

		// ī�װ� ������ �о����
		List<AcCategoryDTO> categoryDTO = adService.getCategory();
		listCnt = categoryDTO.size();
		// VALUE�� �����ϱ�
		JSONArray jsonList = new JSONArray();

		// CATEGORY ��
		for (int i = 0; i < categoryDTO.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("categoryId", categoryDTO.get(i).getCategoryId());
			jsonObj.put("categoryName", categoryDTO.get(i).getCategoryName());
			jsonList.add(jsonObj);
		} // end for

		int maxPage = (int) (listCnt / MAXSIZE + 0.95); // �������� ���ϸ� �� ������?
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", jsonList); // ������������ ��ϵ�����
		jsonObj.put("page", current_page); // ����������
		jsonObj.put("total", maxPage); // ����������
		jsonObj.put("records", MAXSIZE); // �ѱ۸�ϼ�

		return jsonObj.toJSONString();
	}// acDealShowJson

	// Grid��û
	@RequestMapping("/acCategoryGrid.EditAction.sw")
	public String acDealDel(HttpServletRequest request) throws ParseException {
		int res = -1;
		String oper = request.getParameter("oper");
		System.out.println("acCategoryGrid.EditAction.sw(" + oper + ") ��û");

		// ������û
		if (oper.equals("del")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			res = adService.acCategoryDelete(categoryId);
			// ������û
		} else if (oper.equals("edit")) {
			int categoryId = Integer.parseInt(request.getParameter("id"));
			AcCategoryDTO cDTO = new AcCategoryDTO();
			cDTO.setCategoryId(categoryId);
			cDTO.setCategoryName(request.getParameter("categoryName"));
			res = adService.acCategoryUpdate(cDTO);
			//���� �߰�
		}else if(oper.equals("add")){
			res = adService.acCategoryInsert(request.getParameter("categoryName"));
		}
		// acDeal.Del.sw
		if (res == -1) {//
			System.out.println("acCategory.EditAction.sw ����");
		} // end if
		return "acCategory.ListView.sw";
	}// acDealDel
}
