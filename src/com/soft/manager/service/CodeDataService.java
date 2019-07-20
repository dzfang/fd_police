package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.CodeData;
import com.soft.manager.Constants;
import com.soft.manager.dao.CodeDataDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

/**
 * 
 * 
 * @类名称：CodeDataService
 * @类描述：(系统代码业务处理类) @创建人：张晓雅
 * @创建时间：2015年7月17日 上午11:12:14
 * @修改人：张晓雅
 * @修改时间：2015年7月17日 上午11:12:14 @修改备注：
 * @version 1.0.0
 *
 */

@Service
public class CodeDataService {

	/**
	 * 系统代码数据持久化对象
	 */
	@Resource
	private CodeDataDao codeDataDao;

	/**
	 * 所有系统编码
	 */
	private static List<CodeData> codeDataListAll;

	/**
	 * 
	 * getCodeDataList(根据代码类型、上级编码、SQL检索条件进行编码检索)
	 * 
	 * @param type
	 *            代码类型 @param code2 上级编码 @param sqlWhere SQL检索条件 @return List
	 *            <CodeData> 代码列表 @exception
	 */
	public List<CodeData> getCodeDataList(String type, String code2, String sqlWhere) {
		// codeDataListAll为空时，才查询数据库，否则直接从codeDataListAll列表中取得数据
		if (codeDataListAll == null || (type == null && code2 == null && sqlWhere == null)) {
			// 查询所有编码
			codeDataListAll = codeDataDao.selectListByType(null, null, null);
		}
		List<CodeData> result = new ArrayList<CodeData>();
		// SQL检索条件不为空，根据检索条件查询
		if (sqlWhere != null && !"".equals(sqlWhere)) {
			result = codeDataDao.selectListByType(type, code2, sqlWhere);
		} else {

			for (CodeData codeData : codeDataListAll) {
				// 根据代码类型进行检索
				if (type != null && !"".equals(type)) {
					if (codeData.getType().equals(type)) {
						// 根据上级编码进行检索
						if (code2 != null && !"".equals(code2)) {
							if (code2.equals(codeData.getCode2())) {
								result.add(codeData);
							}
						} else {
							result.add(codeData);
						}
					}
				} else {
					result.add(codeData);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * refreshCodeDataList(重新加载代码表数据) void
	 * 
	 * @exception
	 */
	public void refreshCodeDataList() {
		// 查询所有编码
		codeDataListAll = codeDataDao.selectListByType(null, null, null);
	}

	/**
	 * 
	 * getCodeDataValue(获取代码对应的文本)
	 * 
	 * @param type
	 *            代码类型 @param code1 编码1 @return String 返回代码对应的文本 @exception
	 */
	public String getCodeDataValue(String type, String code1) {
		String result = "";
		// 如果代码列表为null,从数据库中查询代码表数据
		if (codeDataListAll == null) {
			// 查询所有编码
			refreshCodeDataList();
		}
		// 根据代码类型、编码1对代码进行检索
		for (CodeData codeData : codeDataListAll) {
			// 返回代码类型、编码1与参数一致的代码对应的文本
			if (codeData.getType().equals(type) && codeData.getCode1().equals(code1)) {
				// 取得代码对应的文本
				result = codeData.getValue();
			}
		}
		// 返回文本
		return result;
	}

	/**
	 * 
	 * getCodeDataValue(获取代码对应的文本)
	 * 
	 * @param type
	 *            代码类型 @param code1 编码1 @param code2 编码2 @return String
	 *            返回代码对应的文本 @exception
	 */
	public String getCodeDataValue(String type, String code1, String code2) {
		String result = "";
		if (codeDataListAll == null) {
			// 查询所有编码
			refreshCodeDataList();
		}
		// 根据代码类型、编码1、编码2对代码进行检索
		for (CodeData codeData : codeDataListAll) {
			// 返回代码类型、编码1、编码2与参数一致的代码对应的文本
			if (codeData.getType().equals(type) && codeData.getCode1().equals(code1) && codeData.getCode2().equals(code2)) {
				// 取得代码对应的文本
				result = codeData.getValue();
			}
		}
		// 返回文本
		return result;
	}

	/**
	 * 
	 * getCodeDataBean(获取代码信息对象)
	 * 
	 * @param type
	 *            代码类型 @param code1 编码1 @return CodeData 代码信息对象 @exception
	 */
	public CodeData getCodeDataBean(String type, String code1) {
		CodeData result = new CodeData();
		if (codeDataListAll == null) {
			// 查询所有编码
			refreshCodeDataList();
		}
		// 根据代码类型、编码1对代码进行检索
		for (CodeData codeData : codeDataListAll) {
			// 返回代码类型、编码1与参数一致的代码信息对象
			if (codeData.getType().equals(type) && codeData.getCode1().equals(code1)) {
				// 取得代码信息对象
				result = codeData;
			}
		}
		// 返回对象
		return result;
	}

	/**
	 * 
	 * getCodeDataBean(获取代码信息对象)
	 * 
	 * @param type
	 *            代码类型 @param code1 编码1 @return CodeData 代码信息对象 @exception
	 */
	public String getCodeByValue(String type, String value) {
		if (Utils.isEmptyString(value)) {
			return null;
		}
		String code = null;
		if (codeDataListAll == null) {
			// 查询所有编码
			refreshCodeDataList();
		}
		// 根据代码类型、编码1对代码进行检索
		for (CodeData codeData : codeDataListAll) {
			// 返回代码类型、编码1与参数一致的代码信息对象
			if (codeData.getType().equals(type) && codeData.getValue().equals(value.trim())) {
				// 取得代码信息对象
				code = codeData.getCode1();
				break;
			}
		}
		// 返回对象
		return code;
	}

	/**
	 * 
	 * getCodeList(查询系统编码信息)
	 * 
	 * @param codeData
	 *            系统编码对象 @param pageIndex 当前页码 @param pageSize 每页数据笔数 @return
	 *            Map<String,Object> 包含数据总笔数，当前页系统编码信息列表 @exception
	 */
	public Map<String, Object> getCodeList(CodeData codeData, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = codeDataDao.getCodeListCount(codeData);
		// 当前页码开始下标大于记录总数时，重置当前页码，主要用于删除时，数据笔数不足时的页码重置
		if ((pageIndex - 1) * pageSize + 1 > count) {
			pageIndex = (int) Math.ceil(count / pageSize);
			pageIndex = pageIndex == 0 ? 1 : pageIndex;
		}
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		// 参数列表
		Map<String, Object> params = new HashMap<String, Object>();
		// 活动信息
		params.put("codeData", codeData);
		// 当前页起始下标
		params.put("startIndex", startIndex);
		// 每页数据笔数
		params.put("pageSize", pageSize);
		// 查询系统编码信息列表
		List<CodeData> codeDataList = codeDataDao.getCodeList(params);
		// 结果集
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 数据笔数
		resultMap.put("total", count);
		// 系统编码信息
		resultMap.put("rows", codeDataList);
		return resultMap;
	}

	/**
	 * 
	 * insertCodeData(添加系统数据)
	 * 
	 * @param codeData
	 *            系统数据对象 @return String @exception
	 */
	public String saveCodeData(CodeData codeData) {
		int result = 0;
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		if (Constants.INSERT.equals(codeData.getOperate())) {
			// 判断类别代码与代 码1是否存在
			CodeData oneCodeData = codeDataDao.selectCodeData(codeData);
			if (oneCodeData != null) {
				return Constants.CODEDATA_EXISTS;
			}

			Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
			// 设定系统数据创建者
			codeData.setCreateUser(userId);
			// 设定系统数据日期
			codeData.setCreateDate(createDate);
			// 设定系统数据修改人
			codeData.setUpdateUser(userId);
			// 设定系统数据修改日期
			codeData.setUpdateDate(createDate);
			// 新增系统数据
			result = codeDataDao.insertCodeData(codeData);

		} else {
			// 获取当前登录用户的ID
			Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
			// 设定活动修改人
			codeData.setUpdateUser(userId);
			// 设定活动修改日期
			codeData.setUpdateDate(updateDate);
			result = codeDataDao.updateCodeDataByType(codeData);

		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * deleteCodeDataByType(根据代码类型和代码1删除系统数据)
	 * 
	 * @param codeDataList
	 *            代码类型和代码1集合 @return String
	 *            返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED) @exception
	 */
	public String deleteCodeDataByType(List<CodeData> codeDataList) {
		int result = 0;
		for (CodeData codeData : codeDataList) {
			// 删除系统信息
			result = codeDataDao.deleteCodeDataByType(codeData);
		}
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 
	 * selectCodeData(根据代码类型和代码1查询数据)
	 * 
	 * @param type
	 *            代码类型 @param code1 代码1 @return CodeData 对象 @exception
	 */
	public CodeData selectCodeData(String type, String code1) {
		CodeData codeData = new CodeData();
		codeData.setType(type);
		codeData.setCode1(code1);
		// 返回系统数据对象
		return codeDataDao.selectCodeData(codeData);
	}

}
