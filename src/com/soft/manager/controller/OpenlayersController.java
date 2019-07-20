package com.soft.manager.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.common.domain.Position;
import com.soft.manager.service.PositionService;

@Controller
@RequestMapping("openlayers")
public class OpenlayersController extends BaseController {
	@Resource
	private PositionService positionService;

	/**
	 * 
	 * mapInit(初始化地图)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("mapInit.do")
	public String mapInit() {
		return "openlayers/map";
	}

	/**
	 * 
	 * queryPosition(根据名称查询地理坐标)
	 * 
	 * @param position
	 * @return Map <String,Object> @exception
	 */
	@RequestMapping("queryPosition.do")
	public @ResponseBody Map<String, Object> queryPosition(Position position, int pageIndex, int pageSize) {
		Map<String, Object> resultMap = positionService.getPositionList(position, pageIndex, pageSize);
		return resultMap;
	}
}
