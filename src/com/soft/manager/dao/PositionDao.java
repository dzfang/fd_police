package com.soft.manager.dao;

import java.util.List;
import java.util.Map;

import com.soft.common.domain.Position;

public interface PositionDao {
	
	/**
	 * 
	 * getEmployeeCount(根据名称查询地理信息笔数)
	 * 
	 * @param record
	 *            地理信息
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getPositionCount(Position record);
	
	/**
	 * 
	 * getPositionList(根据名称查询地理信息列表)
	 * 
	 * @param params
	 * @return List<Position> @exception
	 */
	List<Position> getPositionList(Map<String, Object> params);
}
