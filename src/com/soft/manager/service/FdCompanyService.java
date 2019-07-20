package com.soft.manager.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.soft.common.domain.FdCompany;
import com.soft.manager.Constants;
import com.soft.manager.dao.FdCompanyDao;
import com.soft.util.CodeDataUtil;
import com.soft.util.ExcelUtil;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class FdCompanyService {
	@Resource
	private FdCompanyDao companyDao;

	/**
	 * 
	 * insertCompany(新增从业企业信息)
	 * 
	 * @param record FdCompany对象 @return String
	 * 成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	public String insertCompany(FdCompany record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		if (null != record.getIssueDate()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(record.getIssueDate());
			record.setHandleYear(String.valueOf(cal.get(Calendar.YEAR)));
		}
		record.setDeleteSign(false);
		record.setCreateUser(userId);
		record.setCreateDate(createDate);
		record.setUpdateUser(userId);
		record.setUpdateDate(createDate);
		int result = companyDao.insertCompany(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteCompanysById(根据从业企业信息ID批量删除从业企业信息)
	 * 
	 * @param params Map对象，包含一个从业企业信息ID数组 @return String
	 * 成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	public String deleteCompanysById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = companyDao.deleteCompanysById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findCompanyById(根据从业企业信息ID查询从业企业信息)
	 * 
	 * @param id 从业企业信息ID @return FdCompany 从业企业信息对象 @exception
	 */
	public FdCompany findCompanyById(Long id) {
		return companyDao.findCompanyById(id);
	}

	/**
	 * 
	 * updateCompanyById(根据从业企业信息ID更新从业企业信息)
	 * 
	 * @param record 从业企业信息对象 @return String
	 * 成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	public String updateCompanyById(FdCompany record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		if (null != record.getIssueDate()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(record.getIssueDate());
			record.setHandleYear(String.valueOf(cal.get(Calendar.YEAR)));
		}
		record.setUpdateUser(userId);
		record.setUpdateDate(updateDate);
		int result = companyDao.updateCompanyById(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getCompanyList(根据查询条件查询从业企业信息列表)
	 * 
	 * @param record FdCompany对象 @param pageIndex 当前页码 @param pageSize
	 * 每页数据笔数 @return List<FdCompany> 返回一个Map对象，包含数据笔数及从业企业信息列表 @exception
	 */
	public Map<String, Object> getCompanyList(FdCompany record, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = companyDao.getCompanyCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<FdCompany> rows = companyDao.getCompanyList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

	/**
	 * 
	 * insertCompany(将excel中的数据插入到数据库中) @param in 数据流 @return @throws
	 * IOException String @exception
	 */
	public String insertCompany(InputStream in) throws IOException {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		List<FdCompany> list = importExcel(in);
		int result = 0;
		for (FdCompany record : list) {
			if (null != record.getIssueDate()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(record.getIssueDate());
				record.setHandleYear(String.valueOf(cal.get(Calendar.YEAR)));
			}
			record.setDeleteSign(false);
			record.setCreateUser(userId);
			record.setCreateDate(createDate);
			record.setUpdateUser(userId);
			record.setUpdateDate(createDate);
			result = companyDao.insertCompany(record);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * importExcel(将Excel中的数据从数据流中读出来，转变为对象列表) @param in @return @throws
	 * IOException List<FdCompany> @exception
	 */
	private List<FdCompany> importExcel(InputStream in) throws IOException {
		HSSFWorkbook hssWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssWorkbook.getSheetAt(0);
		if (null == hssfSheet) {
			if (null != hssWorkbook) {
			}
			return null;
		}
		List<FdCompany> list = new ArrayList<FdCompany>();
		FdCompany item = null;
		for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (null != hssfRow) {
				System.out.println("------" + rowNum);
				// 案卷编号
				HSSFCell cell = hssfRow.getCell(0);
				String license = ExcelUtil.getCellValue(cell);
				if (Utils.isEmptyString(license)) {
					continue;
				}
				item = new FdCompany();
				item.setLicense(license);
				// 单位名称
				cell = hssfRow.getCell(1);
				String companyName = ExcelUtil.getCellValue(cell);
				item.setCompanyName(companyName);
				// 法人
				cell = hssfRow.getCell(2);
				String legalPerson = ExcelUtil.getCellValue(cell);
				item.setLegalPerson(legalPerson);
				// 负责人
				cell = hssfRow.getCell(3);
				String director = ExcelUtil.getCellValue(cell);
				item.setDirector(director);
				// 业主
				cell = hssfRow.getCell(4);
				String owner = ExcelUtil.getCellValue(cell);
				item.setOwner(owner);
				// 联系电话
				cell = hssfRow.getCell(5);
				String phone = ExcelUtil.getCellValue(cell);
				item.setPhone(phone);
				// 地址
				cell = hssfRow.getCell(6);
				String address = ExcelUtil.getCellValue(cell);
				item.setAddress(address);
				// 类型
				cell = hssfRow.getCell(7);
				String companyType = ExcelUtil.getCellValue(cell);
				item.setCompanyType(CodeDataUtil.getCodeByValue(Constants.COMPANY_TYPE, companyType));
				// 备注
				cell = hssfRow.getCell(8);
				String remark = ExcelUtil.getCellValue(cell);
				item.setRemark(remark);
				// 发证时间
				cell = hssfRow.getCell(9);
				String issueDate = ExcelUtil.getCellValue(cell);
				item.setIssueDate(Utils.stringToDate(Utils.DATE_FORMAT_4, issueDate));
				// 委托代理人
				cell = hssfRow.getCell(10);
				String agent = ExcelUtil.getCellValue(cell);
				item.setAgent(agent);
				// 联系电话
				cell = hssfRow.getCell(11);
				String agentPhone = ExcelUtil.getCellValue(cell);
				item.setAgentPhone(agentPhone);
				// 处理类型
				cell = hssfRow.getCell(12);
				String handleType = ExcelUtil.getCellValue(cell);
				item.setHandleType(CodeDataUtil.getCodeByValue(Constants.HANDLE_TYPE, handleType));

				list.add(item);
			}
		}
		return list;
	}

}
