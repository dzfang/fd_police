package com.soft.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.Position;
import com.soft.manager.dao.PositionDao;
import com.soft.util.DataSourceSwitch;
import com.soft.util.Utils;

@Service
@DataSourceSwitch(dbType = "GIS")
public class PositionService {
	@Resource
	private PositionDao positionDao;

	/**
	 * 
	 * getPositionList(根据名称查询地理信息列表)
	 * 
	 * @param name
	 * @return List<Position> @exception
	 */
	@DataSourceSwitch(dbType = "GIS")
	public Map<String, Object> getPositionList(Position record, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = positionDao.getPositionCount(record);
		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("record", record);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		List<Position> positionList = positionDao.getPositionList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", count);
		resultMap.put("rows", positionList);
		return resultMap;
	}
}
