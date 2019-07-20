package com.soft.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.CodeType;
import com.soft.common.domain.TreeNode;
import com.soft.manager.Constants;
import com.soft.manager.service.CodeTypeService;

/**
 * 
 * 
 * @类名称：CodeTypeController
 * @类描述：(系统类别) @创建人：黄梦姣
 * @创建时间：2015年5月14日 上午9:38:06
 * @修改人：黄梦姣
 * @修改时间：2015年5月14日 上午9:38:06 @修改备注：
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("codeType")
public class CodeTypeController extends BaseController {
	/**
	 * 系统类别处理对象
	 */
	@Resource
	private CodeTypeService codeTypeService;

	/**
	 * 
	 * codeTypeInit(添加类别页面)
	 * 
	 * @return ModelAndView @exception
	 */
	@RequestMapping(value = "codeTypeInit.do")
	public ModelAndView codeTypeInit() {
		// 展现codeTypeSave页面
		ModelAndView modelAndView = new ModelAndView("code/codeTypeSave");
		modelAndView.addObject("operate", Constants.INSERT);
		return modelAndView;
	}

	/**
	 * 
	 * insertCodeType(新增类别名称)
	 * 
	 * @param codeType
	 *            类别对象
	 * @return String
	 * @exception 返回执行结果
	 *                ：成功：SAVE_SUCCESS;失败：SAVE_FAILED
	 */
	@RequestMapping("saveCodeType.do")
	public @ResponseBody String saveCodeType(CodeType codeType) {

		// 新增系统类别
		String result = "";
		result = codeTypeService.saveCodeType(codeType);
		return result;
	}

	/**
	 * 更新画面初始化
	 * 
	 * @param roleId
	 * @param page
	 * @return
	 */
	@RequestMapping("updateInit.do")
	public ModelAndView getCodeTypeById(String type) {
		// 根据类别类型查询类别信息
		CodeType codeType = codeTypeService.findCodeTypeById(type);
		ModelAndView modelAndView = new ModelAndView("code/codeTypeSave");
		modelAndView.addObject("codeTypeModel", codeType);
		modelAndView.addObject("operate", Constants.UPDATE);
		return modelAndView;
	}

	/**
	 * 
	 * getAllCodeTypes(查询所有系统类别)
	 * 
	 * @return List<CodeType> 返回系统类别 @exception
	 */
	@RequestMapping("getAllCodeTypes.do")
	public @ResponseBody List<TreeNode> getAllCodeTypes() {
		List<CodeType> typeList = codeTypeService.getAllCodeTypes();
		TreeNode root = new TreeNode();
		root.setId("");
		root.setText("全部");
		for (CodeType item : typeList) {
			TreeNode children = new TreeNode();
			children.setId(item.getType());
			children.setText(item.getText() + " [" + item.getType() + "]");
			root.getChildren().add(children);
		}
		// 树型菜单结构列表
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		nodeList.add(root);
		return nodeList;
	}

	/**
	 * 
	 * getAllCodeType(获得类型下拉)
	 * 
	 * @return List<CodeType> 返回类型信息列表 @exception
	 */
	@RequestMapping("getAllCodeType.do")
	public @ResponseBody List<CodeType> getAllCodeType() {
		return codeTypeService.getAllCodeTypes();
	}

	/**
	 * 删除类别
	 * 
	 * @param workReport
	 * @return
	 */
	@RequestMapping("deleteCodeTypeById.do")
	public @ResponseBody String deleteCodeTypeById(CodeType codeType) {
		String[] idArray = new String[] { codeType.getType() };
		return codeTypeService.deleteCodeTypeById(idArray);
	}
}
