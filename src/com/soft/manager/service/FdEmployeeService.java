package com.soft.manager.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.soft.common.domain.FdEmployee;
import com.soft.manager.Constants;
import com.soft.manager.dao.FdEmployeeDao;
import com.soft.util.CodeDataUtil;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class FdEmployeeService {
	@Resource
	private FdEmployeeDao employeeDao;

	/**
	 * 
	 * insertEmployee(新增从业人员信息)
	 * 
	 * @param record
	 *            FdEmployee对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String insertEmployee(FdEmployee record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setDeleteSign(false);
		record.setCreateUser(userId);
		record.setCreateDate(createDate);
		record.setUpdateUser(userId);
		record.setUpdateDate(createDate);
		int result = employeeDao.insertEmployee(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteEmployeesById(根据从业人员信息ID批量删除从业人员信息)
	 * 
	 * @param params
	 *            Map对象，包含一个从业人员信息ID数组
	 * @return String 成功：DELETE_SUCCESS；失败：DELETE_FAILED
	 * @exception
	 */
	public String deleteEmployeesById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = employeeDao.deleteEmployeesById(params);
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * findEmployeeById(根据从业人员信息ID查询从业人员信息)
	 * 
	 * @param id
	 *            从业人员信息ID
	 * @return FdEmployee 从业人员信息对象
	 * @exception
	 */
	public FdEmployee findEmployeeById(Long id) {
		return employeeDao.findEmployeeById(id);
	}

	/**
	 * 
	 * updateEmployeeById(根据从业人员信息ID更新从业人员信息)
	 * 
	 * @param record
	 *            从业人员信息对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String updateEmployeeById(FdEmployee record) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		record.setUpdateUser(userId);
		record.setUpdateDate(updateDate);
		int result = employeeDao.updateEmployeeById(record);

		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getEmployeeList(根据查询条件查询从业人员信息列表)
	 * 
	 * @param record
	 *            FdEmployee对象
	 * @param pageIndex
	 *            当前页码
	 * @param pageSize
	 *            每页数据笔数
	 * @return List<FdEmployee> 返回一个Map对象，包含数据笔数及从业人员信息列表
	 * @exception
	 */
	public Map<String, Object> getEmployeeList(FdEmployee record,
			int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = employeeDao.getEmployeeCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询当前页数据
		List<FdEmployee> rows = employeeDao.getEmployeeList(params);
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
	public String insertEmployee(InputStream in) throws IOException {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		List<FdEmployee> list = importExcel(in);
		int result = 0;
		for (FdEmployee record : list) {
			record.setDeleteSign(false);
			record.setCreateUser(userId);
			record.setCreateDate(createDate);
			record.setUpdateUser(userId);
			record.setUpdateDate(createDate);
			result = employeeDao.insertEmployee(record);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * importExcel(将Excel中的数据从数据流中读出来，转变为对象列表) @param in @return @throws
	 * IOException List<FdEmployee> @exception
	 */
	private List<FdEmployee> importExcel(InputStream in) throws IOException {
		HSSFWorkbook hssWorkbook = new HSSFWorkbook(in);
		HSSFSheet hssfSheet = hssWorkbook.getSheetAt(0);
		if (null == hssfSheet) {
			if (null != hssWorkbook) {
			}
			return null;
		}
		List<FdEmployee> list = new ArrayList<FdEmployee>();
		FdEmployee item = null;
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			if (null != hssfRow) {
				// 姓名
				HSSFCell cell = hssfRow.getCell(0);
				String name = getCellValue(cell);
				if (Utils.isEmptyString(name)) {
					continue;
				}
				item = new FdEmployee();
				item.setName(name);
				// 身份证号
				cell = hssfRow.getCell(1);
				String idCard = getCellValue(cell);
				item.setIdCard(idCard);
				// 性别
				cell = hssfRow.getCell(2);
				String gender = getCellValue(cell);
				item.setGender(CodeDataUtil.getCodeByValue(Constants.GENDER, gender));
				// 出生日期
				cell = hssfRow.getCell(3);
				String birthday = getCellValue(cell);
				item.setBirthday(Utils.stringToDate(Utils.DATE_FORMAT_4, birthday));
				// 籍贯
				cell = hssfRow.getCell(4);
				String birthPlace = getCellValue(cell);
				item.setBirthPlace(birthPlace);
				// 民族
				cell = hssfRow.getCell(5);
				String nation = getCellValue(cell);
				item.setNation(CodeDataUtil.getCodeByValue(Constants.NATION, nation));
				// 毕业院校
				cell = hssfRow.getCell(6);
				String school = getCellValue(cell);
				item.setSchool(school);
				// 专业
				cell = hssfRow.getCell(7);
				String major = getCellValue(cell);
				item.setMajor(major);
				// 学历
				cell = hssfRow.getCell(8);
				String degree = getCellValue(cell);
				item.setDegree(CodeDataUtil.getCodeByValue(Constants.DEGREE, degree));
				// 职称
				cell = hssfRow.getCell(9);
				String position = getCellValue(cell);
				item.setPosition(position);
				// 住址
				cell = hssfRow.getCell(10);
				String address = getCellValue(cell);
				item.setAddress(address);
				// 联系电话
				cell = hssfRow.getCell(11);
				String telephone = getCellValue(cell);
				item.setTelephone(telephone);
				// 机构代码
				cell = hssfRow.getCell(12);
				String orgCode = getCellValue(cell);
				item.setOrgCode(orgCode);
				// 企业名称
				cell = hssfRow.getCell(13);
				String orgName = getCellValue(cell);
				item.setOrgName(orgName);
				
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
}
