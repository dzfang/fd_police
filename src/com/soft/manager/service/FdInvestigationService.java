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

import com.soft.common.domain.FdInvestigation;
import com.soft.common.domain.FdRelatedPerson;
import com.soft.manager.Constants;
import com.soft.manager.dao.FdInvestigationDao;
import com.soft.manager.dao.FdRelatedPersonDao;
import com.soft.util.CodeDataUtil;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

@Service
public class FdInvestigationService {
	@Resource
	private FdInvestigationDao investigationDao;
	@Resource
	private FdRelatedPersonDao relatedPersonDao;

	/**
	 * 
	 * insertInvestigation(新增食品药品犯罪侦查案件)
	 * 
	 * @param record
	 *            FdInvestigation对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	@SuppressWarnings("unchecked")
	public String insertInvestigation(FdInvestigation record) {
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
		int result = investigationDao.insertInvestigation(record);
		if (result > 0) {
			if (Utils.isNotEmptyString(record.getPersonJson())) {
				List<FdRelatedPerson> list = (List<FdRelatedPerson>) JSONArray.toCollection(JSONArray.fromObject(record.getPersonJson()), FdRelatedPerson.class);
				if (null != list && list.size() > 0) {
					for (FdRelatedPerson e : list) {
						e.setCaseId(record.getId());
						e.setCreateUser(userId);
						e.setCreateDate(createDate);
						e.setUpdateUser(userId);
						e.setUpdateDate(createDate);
						relatedPersonDao.insertRelatedPerson(e);
					}
				}
			}
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteInvestigationsById(根据食品药品犯罪侦查案件ID批量删除食品药品犯罪侦查案件)
	 * 
	 * @param params
	 *            Map对象，包含一个食品药品犯罪侦查案件ID数组 @return String
	 *            成功：DELETE_SUCCESS；失败：DELETE_FAILED @exception
	 */
	public String deleteInvestigationsById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = investigationDao.deleteInvestigationsById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findInvestigationById(根据食品药品犯罪侦查案件ID查询食品药品犯罪侦查案件)
	 * 
	 * @param id
	 *            食品药品犯罪侦查案件ID @return FdInvestigation 食品药品犯罪侦查案件对象 @exception
	 */
	public FdInvestigation findInvestigationById(Long id) {
		FdInvestigation entity = investigationDao.findInvestigationById(id);
		List<FdRelatedPerson> personList = relatedPersonDao.getRelatedPersonByCaseId(entity.getId());
		entity.setPersonList(personList);
		return entity;
	}

	/**
	 * 
	 * updateInvestigationById(根据食品药品犯罪侦查案件ID更新食品药品犯罪侦查案件)
	 * 
	 * @param record
	 *            食品药品犯罪侦查案件对象 @return String
	 *            成功：SAVE_SUCCESS；失败：SAVE_FAILED @exception
	 */
	@SuppressWarnings("unchecked")
	public String updateInvestigationById(FdInvestigation record) {
		FdInvestigation entity = investigationDao.findInvestigationById(record.getId());
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
		int result = investigationDao.updateInvestigationById(record);
		if (result > 0) {
			// 删除已有数据
			relatedPersonDao.deleteRelatedPersonByCaseId(record.getId());
			if (Utils.isNotEmptyString(record.getPersonJson())) {
				String[] dateFormats = new String[] {"yyyy-MM-dd"};
		        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
				// 新增新的数据
				List<FdRelatedPerson> list = (List<FdRelatedPerson>) JSONArray.toCollection(JSONArray.fromObject(record.getPersonJson()), FdRelatedPerson.class);
				if (null != list && list.size() > 0) {
					for (FdRelatedPerson e : list) {
						e.setCaseId(record.getId());
						e.setUpdateUser(userId);
						e.setUpdateDate(updateDate);
						relatedPersonDao.insertRelatedPerson(e);
					}
				}
			}
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getInvestigationList(根据查询条件查询食品药品犯罪侦查案件列表)
	 * 
	 * @param record
	 *            FdInvestigation对象 @param pageIndex 当前页码 @param pageSize
	 *            每页数据笔数 @return List
	 *            <FdInvestigation> 返回一个Map对象，包含数据笔数及食品药品犯罪侦查案件列表 @exception
	 */
	public Map<String, Object> getInvestigationList(FdInvestigation record, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = investigationDao.getInvestigationCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<FdInvestigation> rows = investigationDao.getInvestigationList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", rows);
		return resultMap;
	}

	/**
	 * 
	 * insertInvestigation(将excel中的数据插入到数据库中) @param in 数据流 @return @throws
	 * IOException String @exception
	 */
	public String insertInvestigation(InputStream in) throws IOException {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		List<FdInvestigation> list = importExcel(in);
		int result = 0;
		for (FdInvestigation record : list) {
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
			result = investigationDao.insertInvestigation(record);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * importExcel(将Excel中的数据从数据流中读出来，转变为对象列表) @param in @return @throws
	 * IOException List<FdInvestigation> @exception
	 */
	private List<FdInvestigation> importExcel(InputStream in) throws IOException {
		HSSFWorkbook hssWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssWorkbook.getSheetAt(0);
		if (null == hssfSheet) {
			if (null != hssWorkbook) {
			}
			return null;
		}
		List<FdInvestigation> list = new ArrayList<FdInvestigation>();
		FdInvestigation item = null;
		for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (null != hssfRow) {
				// 案卷编号
				HSSFCell cell = hssfRow.getCell(0);
				String caseCode = getCellValue(cell);
				if (Utils.isEmptyString(caseCode)) {
					continue;
				}
				item = new FdInvestigation();
				item.setCaseCode(caseCode);
				// 案卷类别
				cell = hssfRow.getCell(1);
				String caseType = getCellValue(cell);
				item.setCaseType(CodeDataUtil.getCodeByValue(Constants.CASE_TYPE, caseType));
				// 案件名称
				cell = hssfRow.getCell(2);
				String caseName = getCellValue(cell);
				item.setCaseName(caseName);
				// 承办人
				cell = hssfRow.getCell(3);
				String undertakePerson = getCellValue(cell);
				item.setUndertakePerson(undertakePerson);
				// 承办单位
				cell = hssfRow.getCell(4);
				String undertakeOrg = getCellValue(cell);
				item.setUndertakeOrg(undertakeOrg);
				// 违法事实
				cell = hssfRow.getCell(5);
				String illegalFacts = getCellValue(cell);
				item.setIllegalFacts(illegalFacts);
				// 涉案物品
				cell = hssfRow.getCell(6);
				String relatedThings = getCellValue(cell);
				item.setRelatedThings(CodeDataUtil.getCodeByValue(Constants.RELATED_THINGS, relatedThings));
				// 涉案价值
				cell = hssfRow.getCell(7);
				String relatedValue = getCellValue(cell);
				item.setRelatedValue(Utils.stringToDecimal(relatedValue));
				// 案件来源
				cell = hssfRow.getCell(8);
				String caseSource = getCellValue(cell);
				item.setCaseSource(CodeDataUtil.getCodeByValue(Constants.CASE_SOURCE, caseSource));
				// 受案时间
				cell = hssfRow.getCell(9);
				String caseTime = getCellValue(cell);
				item.setCaseTime(Utils.stringToDate(Utils.DATE_FORMAT_4, caseTime));
				// 立案时间
				cell = hssfRow.getCell(10);
				String filingTime = getCellValue(cell);
				item.setFilingTime(Utils.stringToDate(Utils.DATE_FORMAT_4, filingTime));
				// 发案地域
				cell = hssfRow.getCell(11);
				String caseArea = getCellValue(cell);
				item.setCaseArea(CodeDataUtil.getCodeByValue(Constants.CASE_AREA, caseArea));
				// 发案地址
				cell = hssfRow.getCell(12);
				String caseAddress = getCellValue(cell);
				item.setCaseAddress(caseAddress);
				// 发案处所
				cell = hssfRow.getCell(13);
				String casePlace = getCellValue(cell);
				item.setCaseArea(CodeDataUtil.getCodeByValue(Constants.CASE_PLACE, casePlace));
				// 嫌疑人单位
				cell = hssfRow.getCell(14);
				String relatedOrg = getCellValue(cell);
				item.setRelatedOrg(relatedOrg);
				// 刑事拘留开始日期
				cell = hssfRow.getCell(15);
				String crimnalStart = getCellValue(cell);
				item.setCrimnalStart(Utils.stringToDate(Utils.DATE_FORMAT_4, crimnalStart));
				// 刑事拘留结束日期
				cell = hssfRow.getCell(16);
				String crimnalEnd = getCellValue(cell);
				item.setCrimnalEnd(Utils.stringToDate(Utils.DATE_FORMAT_4, crimnalEnd));
				// 监视居住处所
				cell = hssfRow.getCell(17);
				String monitorPlace = getCellValue(cell);
				item.setMonitorPlace(monitorPlace);
				// 监视居住开始日期
				cell = hssfRow.getCell(18);
				String monitorStart = getCellValue(cell);
				item.setMonitorStart(Utils.stringToDate(Utils.DATE_FORMAT_4, monitorStart));
				// 监视居住结束日期
				cell = hssfRow.getCell(19);
				String monitorEnd = getCellValue(cell);
				item.setMonitorEnd(Utils.stringToDate(Utils.DATE_FORMAT_4, monitorEnd));
				// 取保候审日期
				cell = hssfRow.getCell(20);
				String bailTime = getCellValue(cell);
				item.setBailTime(Utils.stringToDate(Utils.DATE_FORMAT_4, bailTime));
				// 取保候审保证人
				cell = hssfRow.getCell(21);
				String bailPerson = getCellValue(cell);
				item.setBailPerson(bailPerson);
				// 刑期
				cell = hssfRow.getCell(22);
				String term = getCellValue(cell);
				item.setTerm(term);
				// 逮捕日期
				cell = hssfRow.getCell(23);
				String arrestDate = getCellValue(cell);
				item.setArrestDate(Utils.stringToDate(Utils.DATE_FORMAT_4, arrestDate));
				// 延长羁押
				cell = hssfRow.getCell(24);
				String extendDetention = getCellValue(cell);
				item.setExtendDetention(null != extendDetention && Constants.CN_YES.equals(extendDetention));
				// 罚金
				cell = hssfRow.getCell(25);
				String fine = getCellValue(cell);
				item.setFine(Utils.stringToDecimal(fine));
				// 是否免于起诉
				cell = hssfRow.getCell(26);
				String prosecution = getCellValue(cell);
				item.setProsecution(null != prosecution && Constants.CN_YES.equals(prosecution));
				// 撤销日期
				cell = hssfRow.getCell(27);
				String pevocationDate = getCellValue(cell);
				item.setPevocationDate(Utils.stringToDate(Utils.DATE_FORMAT_4, pevocationDate));

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
	 * queryRelatedPerson(查询案件相关人员) @param caseId 案件ID @return Map<String,
	 * Object> @exception
	 */
	public Map<String, Object> queryRelatedPerson(Long caseId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<FdRelatedPerson> list = relatedPersonDao.getRelatedPersonByCaseId(caseId);
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 
	 * getInvestigationCountByYear(根据立案年份统计案件数量) @param record @return Map<String,
	 * Object> @exception
	 */
	public Map<String, Object> getInvestigationCountByYear(String year) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<FdInvestigation> rows = investigationDao.getInvestigationCountByYear(year);
		resultMap.put("rows", rows);
		return resultMap;
	}
}
