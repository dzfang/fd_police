package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.CodeData;
import com.soft.common.domain.CodeType;
import com.soft.manager.Constants;
import com.soft.manager.dao.CodeDataDao;
import com.soft.manager.dao.CodeTypeDao;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

/**
 * 
 * 
 * @类名称：CodeTypeService
 * @类描述：(系统类别业务处理类)
 * @创建人：黄梦姣
 * @创建时间：2015年5月14日 上午11:09:56
 * @修改人：黄梦姣
 * @修改时间：2015年5月14日 上午11:09:56
 * @修改备注：
 * @version 1.0.0
 *
 */
@Service
public class CodeTypeService {
	/**
	 * 系统类别持久化对象
	 */
	@Resource
	private CodeTypeDao codeTypeDao;

	@Resource
	private CodeDataDao codeDataDao;

	/**
	 * 
	 * insertCodeType(新增系统编码)
	 * 
	 * @param codeType
	 *            系统编码
	 * @return String 返回执行结果(成功：SAVE_SUCCESS;失败：SAVE_FAILED;)
	 * @exception
	 */
	public String saveCodeType(CodeType codeType) {
		int result = 0;
		// 判断类别代码是否已经存在于类型表中

		if (Constants.INSERT.equals(codeType.getOperate())) {
			int count = codeTypeDao.selectCodeTypeByType(codeType);
			if (count != 0) {
				return Constants.CODETYPE_EXISTS;
			}
			// 获取当前登录用户的ID
			long userId = LoginInfoUtil.getCurrentUser().getUserId();
			Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
			codeType.setType(codeType.getType());
			// 设定活动创建者
			codeType.setCreateUser(userId);
			// 设定活动日期
			codeType.setCreateDate(createDate);
			// 设定活动修改人
			codeType.setUpdateUser(userId);
			// 设定活动修改日期
			codeType.setUpdateDate(createDate);
			// 新增系统类别
			result = codeTypeDao.insertCodeType(codeType);
			// 新增提示

		} else {
			Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
			codeType.setUpdateDate(updateDate);
			// 获取当前登录用户的ID
			long userId = LoginInfoUtil.getCurrentUser().getUserId();
			codeType.setUpdateUser(userId);
			// 更新日报信息
			result = codeTypeDao.updateCodeType(codeType);

		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getAllCodeTypes(查询所有系统类别)
	 * 
	 * @return List<CodeType> 系统类别列表
	 * @exception
	 */
	public List<CodeType> getAllCodeTypes() {
		// 查询系统类别列表
		return codeTypeDao.getAllCodeType();
	}

	/**
	 * 根据日报ID删除日报信息
	 * 
	 * @param params
	 * @return
	 */
	public String deleteCodeTypeById(String[] idArray) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateDate", new Date());
		CodeData codeData = new CodeData();
		codeData.setType(idArray[0]);
		int result = codeTypeDao.deleteCodeTypeById(params);
		int re = codeDataDao.deleteCodeDataByType(codeData);
		return result > 0 || re > 0 ? Constants.DELETE_SUCCESS
				: Constants.DELETE_FAILED;
	}

	/**
	 * 根据类别类型查询类别信息
	 * 
	 * @param noticeId
	 * @return
	 */
	public CodeType findCodeTypeById(String type) {
		// 返回用户信息
		return codeTypeDao.findCodeTypeById(type);
	}
}
