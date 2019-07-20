package com.soft.manager.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.CodeData;
import com.soft.manager.Constants;
import com.soft.manager.service.CodeDataService;
import com.soft.util.Utils;

/**
 * 
 * 
 * @类名称：CodeDataController
 * @类描述：(代码表Controller，提供查询代码表数据的方法) @创建人：张晓雅
 * @创建时间：2015年7月17日 上午11:17:00
 * @修改人：张晓雅
 * @修改时间：2015年7月17日 上午11:17:00 @修改备注：
 * @version 1.0.0
 *
 */

@Controller
public class CodeDataController extends BaseController {
	/**
	 * 代码表Service对象
	 */
	@Resource
	private CodeDataService codeDataService;

	/**
	 * 
	 * listInit(初始化系统编码列表页面)
	 * 
	 * @return String 返回codeList.jsp页面 @exception
	 */
	@RequestMapping(value = "/codeData/listInit.do")
	public String listInit() {
		return "code/codeList";
	}

	/**
	 * 
	 * getCodeDataList(根据参数查询代码表数据)
	 * 
	 * @param type
	 *            代码类型 @param code2 代码2 @param sqlWhere 查询条件 @return List
	 *            <CodeData> 返回代码表数据列表 @exception
	 */
	@RequestMapping(value = "/codeData/getCodeDataList.do")
	public @ResponseBody List<CodeData> getCodeDataList(String type, String code2, String sqlWhere) {
		try {
			// 查询数据
			List<CodeData> result = codeDataService.getCodeDataList(type, code2, sqlWhere);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * getCodeDataListHadNull(根据参数查询代码表数据，并且在返回结果中添加空白项)
	 * 
	 * @param type
	 *            代码类型 @param code2 代码2 @param sqlWhere 查询条件 @return List
	 *            <CodeData> 返回代码表数据列表 @exception
	 */
	@RequestMapping(value = "/codeData/getCodeDataListHadNull.do")
	public @ResponseBody List<CodeData> getCodeDataListHadNull(String type, String code2, String sqlWhere) {
		try {
			// 新建一个代码表数据列表对象
			List<CodeData> results = new ArrayList<CodeData>();
			// 添加空白项
			CodeData bm = new CodeData();
			bm.setCode1("");
			bm.setValue("");
			// 将空白项添加到数据列表中
			results.add(bm);
			// 查询数据库中的数据
			List<CodeData> result = codeDataService.getCodeDataList(type, code2, sqlWhere);
			// 如果存在相应的数据，将其添加到数据列表中
			if (result != null) {
				for (CodeData item : result) {
					results.add(item);
				}
			}
			// 返回查询结果
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * addInit(初始化系统编新增画面)
	 * 
	 * @return ModelAndView 返回codeSave.jsp页面 @exception
	 */
	@RequestMapping(value = "/codeData/addInit.do")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("code/codeSave");
		// 操作标志，表示当前画面要执行的保存操作为新增
		modelAndView.addObject("operate", Constants.INSERT);

		return modelAndView;
	}

	/**
	 * 
	 * getCodeDataList(查询系统编码信息)
	 * 
	 * @param codeData
	 *            对象 @param pageIndex 当前页码 @param pageSize 每页数据笔数 @return Map
	 *            <String,Object> @exception
	 */
	@RequestMapping(value = "/codeData/getCodeList.do")
	public @ResponseBody Map<String, Object> getCodeList(CodeData codeData, int pageIndex, int pageSize) {
		return codeDataService.getCodeList(codeData, pageIndex, pageSize);
	}

	/**
	 * 
	 * saveCodeData(添加系统数据)
	 * 
	 * @param codeData
	 *            系统数据对象 @return String @exception
	 */
	@RequestMapping(value = "/codeData/saveCodeData.do")
	public @ResponseBody String saveCodeData(CodeData codeData) {
		String result = "";
		// 新增系统数据
		result = codeDataService.saveCodeData(codeData);
		return result;
	}

	/**
	 * 
	 * deleteCodeDataByType(根据代码类型和代码1删除系统数据)
	 * 
	 * @param codeData
	 *            系统数据对象 @return String
	 *            返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED) @exception
	 */
	@RequestMapping(value = "/codeData/deleteCodeDataByType.do")
	public @ResponseBody String deleteCodeDataByType(CodeData codeData) {
		List<CodeData> codeDataList = new ArrayList<CodeData>();
		codeDataList.add(codeData);
		return codeDataService.deleteCodeDataByType(codeDataList);
	}

	/**
	 * 
	 * deleteCodeDataByTypes(根据代码类型和代码1批量删除系统数据)
	 * 
	 * @param typeArray
	 *            代码类型数组 @param codeArray 代码1数组 @return String
	 *            返回执行结果(成功：DELETE_SUCCESS;失败：DELETE_FAILED) @exception
	 */
	@RequestMapping(value = "/codeData/deleteCodeDataByTypes.do")
	public @ResponseBody String deleteCodeDataByTypes(@RequestParam(value = "typeArray[]") String[] typeArray, @RequestParam(value = "codeArray[]") String[] codeArray) {
		List<CodeData> codeDataList = new ArrayList<CodeData>();
		for (int i = 0; i < typeArray.length; i++) {
			CodeData codeData = new CodeData();
			codeData.setType(typeArray[i]);
			codeData.setCode1(codeArray[i]);
			codeDataList.add(codeData);
		}
		return codeDataService.deleteCodeDataByType(codeDataList);

	}

	/**
	 * 
	 * selectCodeData(初始化系统对象画面)
	 * 
	 * @param type
	 *            代码类型 @param code1 代码1 @return ModelAndView
	 *            返回codeSave.jsp页面 @exception
	 */
	@RequestMapping(value = "/codeData/updateInit.do")
	public ModelAndView selectCodeData(String type, String code1) {
		// 根据代码类型和代码1查询系统数据
		CodeData codeData = codeDataService.selectCodeData(type, code1);
		ModelAndView modelAndView = new ModelAndView("code/codeSave");
		modelAndView.addObject("codeDataModel", codeData);
		// 操作标志，表示当前画面要执行的保存操作为新增
		modelAndView.addObject("operate", Constants.UPDATE);
		return modelAndView;
	}

	@RequestMapping(value = "/codeData/getConstantDataByType.do")
	public @ResponseBody List<CodeData> getConstantDataByType(String type) {
		List<CodeData> codeDataList = new ArrayList<CodeData>();
		if (Utils.isNotEmptyString(type)) {
			if ("A001".equals(type)) {
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				for (int i = 0; i < 10; i++) {
					CodeData e = new CodeData();
					e.setType(type);
					e.setCode1(String.valueOf(year - i));
					e.setValue(e.getCode1());
					if(i==0)
					{
						e.setSelected(true);
					}
					codeDataList.add(e);
				}
			}
		}
		return codeDataList;
	}
}