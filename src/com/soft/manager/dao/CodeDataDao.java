package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.soft.common.domain.CodeData;

/**
 * 
 * 
 * @类名称：CodeDataDao
 * @类描述：(代码表数据持久化接口)
 * @创建人：段志芳
 * @创建时间：2015年4月30日 下午2:59:50
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午2:59:50
 * @修改备注：
 * @version 1.0.0
 *
 */
public interface CodeDataDao {

	/**
	 * 
	 * selectListByType(根据编码类型、上级编码、SQL检索条件进行编码检索)
	 * 
	 * @param type
	 *            编码类型
	 * @param code2
	 *            上级编码
	 * @param sqlWhere
	 *            SQL检索条件
	 * @return List<CodeData> 代码表数据列表
	 * @exception
	 */
	public List<CodeData> selectListByType(@Param("type") String type,
			@Param("code2") String code2, @Param("sqlWhere") String sqlWhere);

	/**
	 * 
	 * getCodeListCount(根据条件查询系统编码数据笔数)
	 * 
	 * @param codeData
	 *            系统编码对象
	 * @return int 系统编码对象数据笔数
	 * @exception
	 */
	public int getCodeListCount(CodeData codeData);

	/**
	 * 
	 * getCodeList(根据查询条件查询系统编码列表)
	 * 
	 * @param params
	 *            Map对象，包含CodeData对象,当前页码以及每页数据笔数
	 * @return List<CodeData> 返回系统编码列表
	 * @exception
	 */
	public List<CodeData> getCodeList(Map<String, Object> params);

	/**
	 * 
	 * insertCodeData(新增系统数据)
	 * 
	 * @param codeData
	 *            系统数据对象
	 * @return int 返回新增数据笔数
	 * @exception
	 */
	public int insertCodeData(CodeData codeData);

	/**
	 * 
	 * selectCodeData(根据代码类型和代码1查询)
	 * 
	 * @param CodeData
	 *            系统类型对象
	 * @return int
	 * @exception
	 */
	public CodeData selectCodeData(CodeData codeData);

	/**
	 * 
	 * deleteCodeDataByType(删除系统数据)
	 * 
	 * @param params
	 *            codeData对象
	 * @return int 返回删除数据的笔数
	 * @exception
	 */
	public int deleteCodeDataByType(CodeData codeData);

	/**
	 * 
	 * updateCodeDataByType(根据代码类型和代码1更新数据)
	 * 
	 * @param codeData
	 *            系统数据对象
	 * @return int 返回更新数据笔数
	 * @exception
	 */
	public int updateCodeDataByType(CodeData codeData);

	/**
	 * 
	 * updateCodeDataByType(根据代码类型和代码1更新数据)
	 * 
	 * @param codeData
	 *            系统数据对象
	 * @return int 返回更新数据笔数
	 * @exception
	 */
	public int updateCodeDatasByType(CodeData codeData);

	/**
	 * 
	 * updateAppServiceStatus(根据代码类型和代码1更新APP服务器状态)
	 * 
	 * @param codeData
	 *            系统数据对象
	 * @return int 返回更新数据笔数
	 * @exception
	 */
	public int updateAppServiceStatus(CodeData codeData);
}
