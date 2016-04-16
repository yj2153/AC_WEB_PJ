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

	// �ŷ����� �� ȭ�� �̵�
	@RequestMapping("/acDeal.regForm.sw")
	public String acDealIndex(Model model) {
		System.out.println("acDealIndex ȣ��");
		List<AcTypeDTO> typeDTO = adService.getType();
		List<AcCategoryDTO> categoryDTO = adService.getCategory();

		model.addAttribute("typeDTO", typeDTO);
		model.addAttribute("categoryDTO", categoryDTO);
		model.addAttribute("includeName", "/hanbitsw/acDeal/view/acDealRegForm.jsp");
		return "/hanbitsw/acDeal/view/acDealIndex.jsp";
	}

	// ��ȸ ȭ�� �̵�
	@RequestMapping("/acDeal.ListView.sw")
	public String acDealType(Model model) throws UnsupportedEncodingException {
		System.out.println("acDeal.ListView.sw");
		// ����, ī�װ� ������ �о����
		List<AcTypeDTO> typeDTO = adService.getType();
		List<AcCategoryDTO> categoryDTO = adService.getCategory();
		AcDTO acDTO = adService.acSelect();
		// VALUE�� �����ϱ�
		StringBuilder typeValue = new StringBuilder();
		StringBuilder categoryValue = new StringBuilder();
		// TYPE��
		for (int i = 0; i < typeDTO.size(); i++) {
			typeValue.append(typeDTO.get(i).getTypeName());
			typeValue.append(":");
			typeValue.append(typeDTO.get(i).getTypeName());
			if (i < (typeDTO.size() - 1))
				typeValue.append(";");
		} // end for
			// CATEGORY ��
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

	// ���
	@ResponseBody
	@RequestMapping(value = "/acDeal.Reg.sw", method = RequestMethod.POST)
	public String acDealReg(@Valid @ModelAttribute("dealList") AcDealVOList dealList, Errors errors, Model model)
			throws UnsupportedEncodingException {
		System.out.println("POST ȣ�� " + dealList.getDealvoList().size());

		// ���������� �ִٸ�
		if (errors.hasErrors()) {
			String strError = errors.getFieldError("dealvoList").getCode();
			return URLEncoder.encode(strError, "utf-8");
		} // end if

		int res = adService.acDealReg(dealList);
		if (res != 1) {
		} // end if
			// ȭ�� �̵�
		return "acDeal.regForm.sw";
	}// acDealReg

	// Grid��û
	@RequestMapping("/acDealGrid.EditAction.sw")
	public String acDealDel(HttpServletRequest request) throws ParseException {
		int res = -1;
		String oper = request.getParameter("oper");
		System.out.println("acDealGrid.EditAction.sw(" + oper + ") ��û");

		// ������û
		if (oper.equals("del")) {
			int dealId = Integer.parseInt(request.getParameter("id"));
			res = adService.acDealDel(dealId);
			// ������û
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
			System.out.println("acDealGrid.EditAction.sw ����");
		} // end if
		return "acDeal.ListView.sw";
	}// acDealDel

	@RequestMapping("/acDealGrid.Excel.sw")
	public String acDealGridExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oper = request.getParameter("oper");
		String path = request.getRealPath("\\hanbitsw\\acDeal\\xml") + "\\DealList.xls";

		if (oper.equals("excel")) {
			String[] titleName = { "����", "ī�װ�", "�󼼳���", "�ݾ�", "�ŷ���" };
			String pageNum = request.getParameter("page");
			System.out.println(path);
			// 1���� workbook�� ����
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 2���� sheet����
			HSSFSheet sheet = workbook.createSheet("page_" + pageNum);
			// ������ ��
			HSSFRow row = null;
			// Cell ����, ���� ä���
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
			cellStyle.setFillPattern(CellStyle.BIG_SPOTS);
			// ��� row ����
			row = sheet.createRow(0);

			// ��� cell ����
			for (int i = 0; i < titleName.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titleName[i]); // Ÿ��Ʋ
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

			// ��� ���� ��ġ�� ���ϸ� ����
			FileOutputStream outFile = null;
			try {
				outFile = new FileOutputStream(path);
				workbook.write(outFile);
				outFile.close();

				System.out.println("���ϻ��� �Ϸ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "/hanbitsw/acDeal/view/adDealFiledown.jsp?path=" + path;//"acDeal.ListView.sw"
	}

	// ��ȸ ������ JSON
	@ResponseBody
	@RequestMapping(value = "/acDeal.List.sw", produces = "application/json;charset=UTF-8")
	public String acDealShowJson(HttpServletRequest request) {
		System.out.println("acDeal.List.sw ȣ��");
		String current_page = request.getParameter("page"); // ����������
		int MAXSIZE = 10; // 10���� ȭ�鿡 ����
		int listCnt = adService.getDealCount(); // �� ����Ʈ ���� ���ϱ�
		int maxPage = (int) (listCnt / MAXSIZE + 0.95); // �������� ���ϸ� �� ������?
		int startPage = (((int) ((double) Integer.valueOf(current_page) / MAXSIZE + 0.9)) - 1) * 10 + 1;

		JSONObject jsonObj = new JSONObject();
		List<AcDealDTO> list = adService.getDealList(startPage);

		// DB�� ���� �����͸� jsonȭ �ϱ�
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

		jsonObj.put("rows", jsonArray); // ������������ ��ϵ�����
		jsonObj.put("page", current_page); // ����������
		jsonObj.put("total", maxPage); // ����������
		jsonObj.put("records", MAXSIZE); // �ѱ۸�ϼ�

		return jsonObj.toJSONString();
	}// acDealShowJson

	// webDataBinder�� ������ü ���
	@InitBinder
	public void bind(WebDataBinder binder) {
		binder.setValidator(new AcDealValidator());
	}
}
