package com.soft.manager;

/**
 * 
 * @类名称：Constants
 * @类描述：(系统常量类) @创建人：段志芳
 * @创建时间：2015年4月30日 下午1:16:38
 * @修改人：段志芳
 * @修改时间：2015年4月30日 下午1:16:38 @修改备注：
 * @version 1.0.0
 *
 */
public class Constants {
	/** 新增 */
	public static final String INSERT = "INSERT";
	/** 密码 */
	public static final String PASSWORD = "111111";
	/** 更新 */
	public static final String UPDATE = "UPDATE";
	/** 版本号已改变 */
	public static final String VERSION_CHANGED = "VERSION_CHANGED";
	/** 无法获取数据版本信息 */
	public static final String NO_VERSION = "NO_VERSION";
	/** 保存成功 */
	public static final String SAVE_SUCCESS = "SAVE_SUCCESS";
	/** 保存失败 */
	public static final String SAVE_FAILED = "SAVE_FAILED";
	/** 保存成功 */
	public static final String DELETE_SUCCESS = "DELETE_SUCCESS";
	/** 保存失败 */
	public static final String DELETE_FAILED = "DELETE_FAILED";
	/** 提交成功 */
	public static final String COMMIT_SUCCESS = "COMMIT_SUCCESS";
	/** 提交失败 */
	public static final String COMMIT_FAILED = "COMMIT_FAILED";
	/** 用户名已存在 */
	public static final String LOGIN_ID_EXISTS = "LOGIN_ID_EXISTS";
	/** 未授权 */
	public static final String UNAUTHORIZED = "UNAUTHORIZED";
	/** 登录成功 */
	public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	/** 登录成功 */
	public static final String LOGIN_FAILED = "LOGIN_FAILED";
	/** 用户已被冻结 */
	public static final String FROZEN = "FROZEN";
	/** 角色名称已存在 */
	public static final String ROLE_NAME_EXISTS = "ROLE_NAME_EXISTS";
	/** 重置成功 */
	public static final String RESET_SUCCESS = "RESET_SUCCESS";
	/** 重置失败 */
	public static final String RESET_FAILED = "RESET_FAILED";
	/** 系统类别对象已存在 */
	public static final String CODETYPE_EXISTS = "CODETYPE_EXISTS";
	/** 系统数据已存在 */
	public static final String CODEDATA_EXISTS = "CODEDATA_EXISTS";
	/** 数据已删除 */
	public static final String DATA_DELETED = "DATA_DELETED";
	/** 操作成功 */
	public static final String OPERATE_SUCCESS = "OPERATE_SUCCESS";
	/** 操作失败 */
	public static final String OPERATE_FAILED = "OPERATE_FAILED";
	/**
	 * 设备数量不足
	 */
	public static final String NUMBER_NOT_ENOUGH = "NUMBER_NOT_ENOUGH";
	/**
	 * 该角色是系统角色，不允许删除
	 */
	public static final String IS_SYSTEM_ROLE = "IS_SYSTEM_ROLE";
	/** 路径分隔符 */
	public static final String PATH_SEPARATOR = "/";
	/** 是否--否 */
	public static final String NO = "0";
	/** 是否 --是 */
	public static final String YES = "1";
	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";

	/**
	 * 用户类型--教师
	 */
	public static final String TEACHER = "001";
	/**
	 * 用户类型--学生
	 */
	public static final String STUDENT = "002";
	/**
	 * 供应商已存在
	 */
	public static final String SUPPLIER_EXISTS = "SUPPLIER_EXISTS";
	/**
	 * 设备或者物资类别名称已存在
	 */
	public static final String EQUIPTYPE_EXISTS = "EQUIPTYPE_EXISTS";
	/**
	 * 计量单位已存在
	 */
	public static final String EQUIPMEASURE_EXISTS = "EQUIPMEASURE_EXISTS";
	/**
	 * 设备名称已存在
	 */
	public static final String EQUIPMENT_EXISTS = "EQUIPMENT_EXISTS";
	/**
	 * 动产
	 */
	public static final String MATERIAL_MOVABLE = "001";
	/**
	 * 不动产
	 */
	public static final String MATERIAL_IMMOVABLE = "002";
	/**
	 * 耗材
	 */
	public static final String MATERIAL_CONSUMABLE = "003";

	/**
	 * 未给您分配网络空间，无法上传
	 */
	public static final String NO_SPACE = "NO_SPACE";
	/**
	 * 您的网络空间不足，无法上传
	 */
	public static final String SPACE_NOT_ENOUGH = "SPACE_NOT_ENOUGH";
	/**
	 * 公开
	 */
	public static final String DOC_PUBLIC = "1";
	/**
	 * 不公开
	 */
	public static final String DOC_PRIVATE = "0";
	/**
	 * 修改
	 */
	public static final String OPERATE_UPDATE_CODE = "001";
	/**
	 * 修改
	 */
	public static final String OPERATE_UPDATE_TEXT = "修改";
	/**
	 * 删除
	 */
	public static final String OPERATE_DELETE_CODE = "002";
	/**
	 * 删除
	 */
	public static final String OPERATE_DELETE_TEXT = "删除";

	public static final String PAGE_CODE_001 = "001";

	public static final String PAGE_TEXT_001 = "设备基本信息";

	public static final String PAGE_CODE_002 = "002";

	public static final String PAGE_TEXT_002 = "设备购入信息";

	public static final String PAGE_CODE_003 = "003";

	public static final String PAGE_TEXT_003 = "设备借用信息";

	public static final String PAGE_CODE_004 = "004";

	public static final String PAGE_TEXT_004 = "设备报废信息";

	public static final String PAGE_CODE_005 = "005";

	public static final String PAGE_TEXT_005 = "设备使用信息";

	public static final String PAGE_CODE_006 = "006";

	public static final String PAGE_TEXT_006 = "耗材领用信息";

	public static final String PAGE_CODE_007 = "007";

	public static final String PAGE_TEXT_007 = "故障报修信息";
	/**
	 * 未维护
	 */
	public static final String EQUIP_UNREPAIR = "001";
	/**
	 * 已维护
	 */
	public static final String EQUIP_REPAIRED = "002";
	/**
	 * 已报废
	 */
	public static final String EQUIP_SCAP = "003";
	/**
	 * 教师角色ID
	 */
	public static final String ROLE_TEACHER_ID = "2";
	/**
	 * 教师角色文本
	 */
	public static final String ROLE_TEACHER_TEXT = "教师";
	/**
	 * 学生角色ID
	 */
	public static final String ROLE_STUDENT_ID = "3";
	/**
	 * 学生角色文本
	 */
	public static final String ROLE_STUDENT_TEXT = "学生";
	/**
	 * 用户状态--正常
	 */
	public static final String STATUS_NORMAL = "001";
	/**
	 * 用户状态--冻结
	 */
	public static final String STATUS_FROZEN = "002";
	/**
	 * 该用户的网络空间已分配
	 */
	public static final String SPACE_EXISTS = "SPACE_EXISTS";
	/**
	 * 性别
	 */
	public static final String GENDER = "002";
	/**
	 * 案件类别
	 */
	public static final String CASE_TYPE = "100";
	/**
	 * 涉案物品
	 */
	public static final String RELATED_THINGS = "101";
	/**
	 * 案件属性
	 */
	public static final String CASE_ATTR = "108";
	/**
	 * 案件环节
	 */
	public static final String CASE_LINK = "109";
	/**
	 * 处罚单位
	 */
	public static final String EXEC_ORG = "106";
	/**
	 * 案件来源
	 */
	public static final String CASE_SOURCE = "102";

	/**
	 * 发案地域
	 */
	public static final String CASE_AREA = "103";

	/**
	 * 发案地域
	 */
	public static final String CASE_PLACE = "104";
	/**
	 * 否
	 */
	public static final String CN_NO = "否";
	/**
	 * 是
	 */
	public static final String CN_YES = "是";
	/**
	 * 民族
	 */
	public static final String NATION = "114";
	
	/**
	 * 学历
	 */
	public static final String DEGREE = "115";
	
	public static final String COMPANY_TYPE ="116";

	public static final String HANDLE_TYPE ="117";
	
}
