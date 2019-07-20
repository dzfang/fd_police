package com.soft.manager.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

import com.soft.common.domain.FdInspection;
import com.soft.manager.Constants;
import com.soft.manager.dao.FdInspectionDao;
import com.soft.util.CodeDataUtil;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class FdInspectionService {
	@Resource
	private FdInspectionDao inspectionDao;

	/**
	 * 
	 * insertInspection(新增食品药品稽查行政处罚案件)
	 * 
	 * @param record
	 *            FdInspection对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	public String insertInspection(FdInspection record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		if (null != record.getFilingTime()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(record.getFilingTime());
			record.setFilingYear(String.valueOf(cal.get(Calendar.YEAR)));
			record.setFilingMonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
		}
		record.setDeleteSign(false);
		record.setCreateUser(userId);
		record.setCreateDate(createDate);
		record.setUpdateUser(userId);
		record.setUpdateDate(createDate);
		int result = inspectionDao.insertInspection(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteInspectionsById(根据食品药品稽查行政处罚案件ID批量删除食品药品稽查行政处罚案件)
	 * 
	 * @param params
	 *            Map对象，包含一个食品药品稽查行政处罚案件ID数组 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	public String deleteInspectionsById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = inspectionDao.deleteInspectionsById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findInspectionById(根据食品药品稽查行政处罚案件ID查询食品药品稽查行政处罚案件)
	 * 
	 * @param id
	 *            食品药品稽查行政处罚案件ID @return FdInspection 食品药品稽查行政处罚案件对象 @exception
	 */
	public FdInspection findInspectionById(Long id) {
		return inspectionDao.findInspectionById(id);
	}

	/**
	 * 
	 * updateInspectionById(根据食品药品稽查行政处罚案件ID更新食品药品稽查行政处罚案件)
	 * 
	 * @param record
	 *            食品药品稽查行政处罚案件对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	public String updateInspectionById(FdInspection record) {
		FdInspection entity = inspectionDao.findInspectionById(record.getId());
		String message = Utils.versionCheck(record, entity);
		if (Utils.isNotEmptyString(message)) {
			return message;
		}
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		if (null != record.getFilingTime()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(record.getFilingTime());
			record.setFilingYear(String.valueOf(cal.get(Calendar.YEAR)));
			record.setFilingMonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
		}
		record.setUpdateUser(userId);
		record.setUpdateDate(updateDate);
		int result = inspectionDao.updateInspectionById(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getInspectionList(根据查询条件查询食品药品稽查行政处罚案件列表)
	 * 
	 * @param record
	 *            FdInspection对象 @param pageIndex 当前页码 @param pageSize
	 *            每页数据笔数 @return List
	 *            <FdInspection> 返回一个Map对象，包含数据笔数及食品药品稽查行政处罚案件列表 @exception
	 */
	public Map<String, Object> getInspectionList(FdInspection record, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = inspectionDao.getInspectionCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<FdInspection> rows = inspectionDao.getInspectionList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

	/**
	 * 
	 * insertInspection(将excel中的数据插入到数据库中) @param in 数据流 @return @throws
	 * IOException String @exception
	 */
	public String insertInspection(InputStream in) throws IOException {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		List<FdInspection> list = importExcel(in);
		int result = 0;
		for (FdInspection record : list) {
			if (null != record.getFilingTime()) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(record.getFilingTime());
				record.setFilingYear(String.valueOf(cal.get(Calendar.YEAR)));
				record.setFilingMonth(String.valueOf(cal.get(Calendar.MONTH) + 1));
			}
			record.setDeleteSign(false);
			record.setCreateUser(userId);
			record.setCreateDate(createDate);
			record.setUpdateUser(userId);
			record.setUpdateDate(createDate);
			result = inspectionDao.insertInspection(record);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * importExcel(将Excel中的数据从数据流中读出来，转变为对象列表) @param in @return @throws
	 * IOException List<FdInspection> @exception
	 */
	private List<FdInspection> importExcel(InputStream in) throws IOException {
		HSSFWorkbook hssWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssWorkbook.getSheetAt(0);
		if (null == hssfSheet) {
			if (null != hssWorkbook) {
			}
			return null;
		}
		List<FdInspection> list = new ArrayList<FdInspection>();
		FdInspection item = null;
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (null != hssfRow) {
				System.out.println("------" + rowNum);
				// 案卷编号
				HSSFCell cell = hssfRow.getCell(0);
				String caseCode = getCellValue(cell);
				if (Utils.isEmptyString(caseCode)) {
					continue;
				}
				item = new FdInspection();
				item.setCaseCode(caseCode);
				// 案卷类别
				cell = hssfRow.getCell(1);
				String caseType = getCellValue(cell);
				item.setCaseType(CodeDataUtil.getCodeByValue(Constants.CASE_TYPE, caseType));
				// 案件名称
				cell = hssfRow.getCell(2);
				String caseName = getCellValue(cell);
				item.setCaseName(caseName);
				// 案件类型（涉案物品）
				cell = hssfRow.getCell(3);
				String relatedThings = getCellValue(cell);
				item.setRelatedThings(CodeDataUtil.getCodeByValue(Constants.RELATED_THINGS, relatedThings));
				// 案件属性
				cell = hssfRow.getCell(4);
				String caseAttr = getCellValue(cell);
				item.setCaseAttr(CodeDataUtil.getCodeByValue(Constants.CASE_ATTR, caseAttr));
				// 案件环节
				cell = hssfRow.getCell(5);
				String caseLink = getCellValue(cell);
				item.setCaseLink(CodeDataUtil.getCodeByValue(Constants.CASE_LINK, caseLink));
				// 立案时间
				cell = hssfRow.getCell(6);
				String filingTime = getCellValue(cell);
				item.setFilingTime(Utils.stringToDate(Utils.DATE_FORMAT_4, filingTime));
				// 行政处罚决定文书号
				cell = hssfRow.getCell(7);
				String punishDoc = getCellValue(cell);
				item.setPunishDoc(punishDoc);
				// 处罚单位
				cell = hssfRow.getCell(8);
				String execOrg = getCellValue(cell);
				item.setExecOrg(CodeDataUtil.getCodeByValue(Constants.EXEC_ORG, execOrg));
				// 违法企业名称或违法自然人姓名
				cell = hssfRow.getCell(9);
				String orgName = getCellValue(cell);
				item.setOrgName(orgName);
				// 违法企业组织机构代码
				cell = hssfRow.getCell(10);
				String orgCode = getCellValue(cell);
				item.setOrgCode(orgCode);
				// 违法企业地址
				cell = hssfRow.getCell(11);
				String orgAddress = getCellValue(cell);
				item.setOrgCode(orgAddress);
				// 法定代表人姓名
				cell = hssfRow.getCell(12);
				String corporationName = getCellValue(cell);
				item.setCorporationName(corporationName);
				// 主要违法事实
				cell = hssfRow.getCell(13);
				String illegalFacts = getCellValue(cell);
				item.setIllegalFacts(illegalFacts);
				// 行政处罚的种类和依据
				cell = hssfRow.getCell(14);
				String punishKind = getCellValue(cell);
				item.setPunishKind(punishKind);
				// 行政处罚的履行方式和期限
				cell = hssfRow.getCell(15);
				String punishExec = getCellValue(cell);
				item.setPunishExec(punishExec);
				// 结案时间
				cell = hssfRow.getCell(16);
				String closeCaseTime = getCellValue(cell);
				item.setCloseCaseTime(Utils.stringToDate(Utils.DATE_FORMAT_4, closeCaseTime));
				// 作出处罚的日期
				cell = hssfRow.getCell(17);
				String punishDate = getCellValue(cell);
				item.setPunishDate(Utils.stringToDate(Utils.DATE_FORMAT_4, punishDate));
				// 没收物价值
				cell = hssfRow.getCell(18);
				String confiscateValue = getCellValue(cell);
				item.setConfiscateValue(Utils.stringToDecimal(confiscateValue));
				// 案件总值(元)
				cell = hssfRow.getCell(19);
				String caseAmount = getCellValue(cell);
				item.setCaseAmount(Utils.stringToDecimal(caseAmount));
				// 销毁物价值
				cell = hssfRow.getCell(20);
				String destroyValue = getCellValue(cell);
				item.setDestroyValue(Utils.stringToDecimal(destroyValue));
				// 罚款金额(元)
				cell = hssfRow.getCell(21);
				String fine = getCellValue(cell);
				item.setFine(Utils.stringToDecimal(fine));
				// 票号
				cell = hssfRow.getCell(22);
				String billNo = getCellValue(cell);
				item.setBillNo(billNo);
				// 缴款日期
				cell = hssfRow.getCell(23);
				String paymentTime = getCellValue(cell);
				item.setPaymentTime(Utils.stringToDate(Utils.DATE_FORMAT_4, paymentTime));
				// 案件来源
				cell = hssfRow.getCell(24);
				String caseSource = getCellValue(cell);
				item.setCaseSource(caseSource);
				// item.setCaseSource(CodeDataUtil.getCodeByValue(Constants.CASE_SOURCE,
				// caseSource));
				// 案件来源时间
				cell = hssfRow.getCell(25);
				String caseSourceTime = getCellValue(cell);
				item.setCaseSourceTime(Utils.stringToDate(Utils.DATE_FORMAT_4, caseSourceTime));
				// 组号
				cell = hssfRow.getCell(26);
				String groupNo = getCellValue(cell);
				item.setGroupNo(groupNo);
				list.add(item);
			}
		}
		return list;
	}

	/**
	 * 
	 * getCellValue(获取单元格的值) @param cell @return String @exception
	 */
	private String getCellValue(HSSFCell cell) {
		if (null == cell) {
			return null;
		}
		if (HSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
			return cell.getStringCellValue();
		} else if (HSSFCell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
			String result = null;
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				CellStyle style = cell.getCellStyle();
				DecimalFormat format = new DecimalFormat();
				String temp = style.getDataFormatString();
				// 单元格设置成常规
				if (temp.equals("General")) {
					format.applyPattern("#");
				}
				result = format.format(value);
			}
			return result;
		} else {
			return cell.getStringCellValue();
		}
	}

	/**
	 * 
	 * getInspectionCountByYear(根据立案年份统计案件数量) @param record @return Map<String,
	 * Object> @exception
	 */
	public Map<String, Object> getInspectionCountByYear(String year) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<FdInspection> rows = inspectionDao.getInspectionCountByYear(year);
		resultMap.put("rows", rows);
		return resultMap;
	}
}
