package com.team1.acdeal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.team1.acdeal.service.AcDealService;
import com.team1.acdeal.validator.AcDealValidator;
import com.team1.acdeal.vo.AcCategoryDTO;
import com.team1.acdeal.vo.AcDTO;
import com.team1.acdeal.vo.AcDealDTO;
import com.team1.acdeal.vo.AcDealVOList;
import com.team1.acdeal.vo.AcTypeDTO;

@Controller
public class AcDealController {

	@Autowired
	AcDealService adService;

	@RequestMapping("/img.upload.sw")
	public String imgUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {

		File file2 = new File("C:\\Users\\Yeonju\\Desktop\\" + file.getOriginalFilename());
		file.transferTo(file2);
		return "index2.jsp";
	}

	// 거래내역 폼 화면 이동
	@RequestMapping("/acDeal.regForm.sw")
	public String acDealIndex(Model model) {
		System.out.println("acDealIndex 호출");
		List<AcTypeDTO> typeDTO = adService.getType();
		List<AcCategoryDTO> categoryDTO = adService.getCategory();

		model.addAttribute("typeDTO", typeDTO);
		model.addAttribute("categoryDTO", categoryDTO);
		model.addAttribute("includeName", "/hanbitsw/acDeal/view/acDealRegForm.jsp");
		return "/hanbitsw/acDeal/view/acDealIndex.jsp";
	}

	// 조회 화면 이동
	@RequestMapping("/acDeal.ListView.sw")
	public String acDealType(Model model) throws UnsupportedEncodingException {
		System.out.println("acDeal.ListView.sw");
		// 구분, 카테고리 데이터 읽어오기
		List<AcTypeDTO> typeDTO = adService.getType();
		List<AcCategoryDTO> categoryDTO = adService.getCategory();
		AcDTO acDTO = adService.acSelect();
		// VALUE값 셋팅하기
		StringBuilder typeValue = new StringBuilder();
		StringBuilder categoryValue = new StringBuilder();
		// TYPE값
		for (int i = 0; i < typeDTO.size(); i++) {
			typeValue.append(typeDTO.get(i).getTypeName());
			typeValue.append(":");
			typeValue.append(typeDTO.get(i).getTypeName());
			if (i < (typeDTO.size() - 1))
				typeValue.append(";");
		} // end for
			// CATEGORY 값
		for (int i = 0; i < categoryDTO.size(); i++) {
			categoryValue.append(categoryDTO.get(i).getCategoryName());
			categoryValue.append(":");
			categoryValue.append(categoryDTO.get(i).getCategoryName());
			if (i < (categoryDTO.size() - 1))
				categoryValue.append(";");
		} // end for

		model.addAttribute("bankname", acDTO.getAcBankname());
		model.addAttribute("balance", acDTO.getAcBalance());
		model.addAttribute("categoryValue", categoryValue);
		model.addAttribute("typeValue", typeValue);
		model.addAttribute("includeName", "/hanbitsw/acDeal/view/acDealList.jsp");
		return "/hanbitsw/acDeal/view/acDealIndex.jsp";
	}// acDealType

	// 등록
	@ResponseBody
	@RequestMapping(value = "/acDeal.Reg.sw", method = RequestMethod.POST)
	public String acDealReg(@Valid @ModelAttribute("dealList") AcDealVOList dealList, Errors errors, Model model)
			throws UnsupportedEncodingException {
		System.out.println("POST 호출 " + dealList.getDealvoList().size());

		// 검증에러가 있다면
		if (errors.hasErrors()) {
			String strError = errors.getFieldError("dealvoList").getCode();
			return URLEncoder.encode(strError, "utf-8");
		} // end if

		int res = adService.acDealReg(dealList);
		if (res != 1) {
		} // end if
			// 화면 이동
		return "acDeal.regForm.sw";
	}// acDealReg

	// Grid요청
	@RequestMapping("/acDealGrid.EditAction.sw")
	public String acDealDel(HttpServletRequest request) throws ParseException {
		int res = -1;
		String oper = request.getParameter("oper");
		System.out.println("acDealGrid.EditAction.sw(" + oper + ") 요청");

		// 삭제요청
		if (oper.equals("del")) {
			int dealId = Integer.parseInt(request.getParameter("id"));
			res = adService.acDealDel(dealId);
			// 수정요청
		} else if (oper.equals("edit")) {
			int dealId = Integer.parseInt(request.getParameter("dealId"));
			AcDealDTO dealDTO = new AcDealDTO();
			String date = request.getParameter("dealDate");
			dealDTO.setDealId(dealId);
			dealDTO.setTypeName(request.getParameter("typeName"));
			dealDTO.setCategoryName(request.getParameter("categoryName"));
			dealDTO.setDealNote(request.getParameter("dealNote"));
			dealDTO.setDealSum(Integer.parseInt(request.getParameter("dealSum")));
			dealDTO.setDealDate(new SimpleDateFormat("yyyy/mm/dd").parse(date));

			res = adService.acDealUpdate(dealDTO);
		} // end else if
			// acDeal.Del.sw
		if (res == -1) {//
			System.out.println("acDealGrid.EditAction.sw 실패");
		} // end if
		return "acDeal.ListView.sw";
	}// acDealDel

	@RequestMapping("/acDealGrid.Excel.sw")
	public String acDealGridExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String path = request.getRealPath("\\hanbitsw\\acDeal\\xml") + "\\DealList.xls";

		if (oper.equals("excel")) {
			String[] titleName = { "구분", "카테고리", "상세내용", "금액", "거래일" };
			String pageNum = request.getParameter("page");
			System.out.println(path);
			// 1차로 workbook을 생성
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 2차는 sheet생성
			HSSFSheet sheet = workbook.createSheet("page_" + pageNum);
			// 엑셀의 행
			HSSFRow row = null;
			// Cell 색깔, 무늬 채우기
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
			// 출력 row 생성
			row = sheet.createRow(0);

			// 출력 cell 생성
			for (int i = 0; i < titleName.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titleName[i]); // 타이틀
				cell.setCellStyle(cellStyle);
			}

			List<AcDealDTO> dtoList = adService.getDealList(Integer.valueOf(pageNum));
			for (int i = 0; i < dtoList.size(); i++) {
				int j = 0;
				row = sheet.createRow(i + 1);
				row.createCell(j++).setCellValue(dtoList.get(i).getTypeName());
				row.createCell(j++).setCellValue(dtoList.get(i).getCategoryName());
				row.createCell(j++).setCellValue(dtoList.get(i).getDealNote());
				row.createCell(j++).setCellValue(dtoList.get(i).getDealSum());
				row.createCell(j++).setCellValue(dtoList.get(i).getDealDate());
			}

			// 출력 파일 위치및 파일명 설정
			FileOutputStream outFile = null;
			try {
				outFile = new FileOutputStream(path);
				workbook.write(outFile);
				outFile.close();

				System.out.println("파일생성 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "/hanbitsw/acDeal/view/adDealFiledown.jsp?path=" + path;//"acDeal.ListView.sw"
	}

	// 조회 데이터 JSON
	@ResponseBody
	@RequestMapping(value = "/acDeal.List.sw", produces = "application/json;charset=UTF-8")
	public String acDealShowJson(HttpServletRequest request) {
		System.out.println("acDeal.List.sw 호출");
		String current_page = request.getParameter("page"); // 현재페이지
		int MAXSIZE = 10; // 10개씩 화면에 노출
		int listCnt = adService.getDealCount(); // 총 리스트 갯수 구하기
		int maxPage = (int) (listCnt / MAXSIZE + 0.95); // 페이지로 구하면 몇 페이지?
		int startPage = (((int) ((double) Integer.valueOf(current_page) / MAXSIZE + 0.9)) - 1) * 10 + 1;

		JSONObject jsonObj = new JSONObject();
		List<AcDealDTO> list = adService.getDealList(startPage);

		// DB로 읽은 데이터를 json화 하기
		JSONArray jsonArray = new JSONArray();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObj2 = new JSONObject();
			jsonObj2.put("dealId", list.get(i).getDealId());
			jsonObj2.put("typeName", list.get(i).getTypeName());
			jsonObj2.put("categoryName", list.get(i).getCategoryName());
			jsonObj2.put("dealNote", list.get(i).getDealNote());
			jsonObj2.put("dealSum", list.get(i).getDealSum());
			jsonObj2.put("dealDate", sdf.format(list.get(i).getDealDate()));
			jsonArray.add(jsonObj2);
		}

		jsonObj.put("rows", jsonArray); // 현재페이지의 목록데이터
		jsonObj.put("page", current_page); // 현재페이지
		jsonObj.put("total", maxPage); // 총페이지수
		jsonObj.put("records", MAXSIZE); // 총글목록수

		return jsonObj.toJSONString();
	}// acDealShowJson

	// webDataBinder에 검증객체 등록
	@InitBinder
	public void bind(WebDataBinder binder) {
		binder.setValidator(new AcDealValidator());
	}
}
