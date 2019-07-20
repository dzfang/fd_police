
package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.LoginRecord;

 
public interface LoginRecordDao {

	/**
	 * 
	 * getLoginRecordCount(根据条件查询后台用户登录记录数据笔数)
	 * 
	 * @param loginRecordInfo
	 *            登录记录对象
	 * @return int 返回数据笔数
	 * @exception
	 */
	public int getLoginRecordCount(LoginRecord loginRecordInfo);

	/**
	 * 
	 * getLoginRecordList(根据条件查询后台用户登录记录列表)
	 * 
	 * @param params
	 *            Map对象，包含LoginRecord对象,当前页码以及每页数据笔数
	 * @return List<LoginRecord> 返回数据集合
	 * @exception
	 */
	public List<LoginRecord> getLoginRecordList(
			Map<String, Object> params);

	/**
	 * 
	 * inserLoginRecord(新增登录记录信息)
	 * 
	 * @param record
	 *            登录记录对象
	 * @return int 返回新增数据笔数
	 * @exception
	 */
	public int inserLoginRecord(LoginRecord record);
}
