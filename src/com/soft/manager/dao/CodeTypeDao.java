package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.CodeType;
/**
 * 
 *  
 * @类名称：CodeTypeDao 
 * @类描述：(这里用一句话描述这个类的作用) 
 * @创建人：黄梦姣 
 * @创建时间：2015年5月14日 上午9:40:34 
 * @修改人：黄梦姣 
 * @修改时间：2015年5月14日 上午9:40:34 
 * @修改备注： 
 * @version 1.0.0 
 *
 */
public interface CodeTypeDao {
	/**
	 * 
	 * insertCodeType(新增系统类别)
	 * 
	 * @param codeType
	 *            系统类别对象
	 * @return int 返回新增数据笔数
	 * @exception
	 */
	public int insertCodeType(CodeType codeType);
	/**
	 * 
	 * updateCodeType(编辑系统类别)
	 * 
	 * @param codeType
	 *            系统类别对象
	 * @return int 返回新增数据笔数
	 * @exception
	 */
	public int updateCodeType(CodeType codeType);
	/**
	 * 
	 * selectCodeTypeByType(根据编码查询)  
	 * @param codeType
	 * @return  
	 * int 
	 * @exception
	 */
	public int selectCodeTypeByType(CodeType codeType);
	/**
	 * 
	 * getAllCodeType(查询所有系统类别)
	 * 
	 * @return List<CodeType> 返回系统类别列表
	 * @exception
	 */
	public List<CodeType> getAllCodeType();
	/**
	 * 根据类别类型删除类别信息
	 * 
	 * @param params
	 * @return
	 */
	public int deleteCodeTypeById(Map<String, Object> params);
	/**
	 * 根据类别类型查询类别信息
	 * 
	 * @param type
	 * @return
	 */
	public CodeType findCodeTypeById(String type);
	
}