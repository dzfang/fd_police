package com.soft.manager.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.LoginRecord;
import com.soft.common.domain.UserInfo;
import com.soft.common.domain.UserRole;
import com.soft.manager.Constants;
import com.soft.manager.dao.LoginRecordDao;
import com.soft.manager.dao.UserInfoDao;
import com.soft.manager.dao.UserRoleDao;
import com.soft.util.Config;
import com.soft.util.EncryptUtil;
import com.soft.util.LoginInfoUtil;
import com.soft.util.Utils;

@Service
public class UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;

	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private LoginRecordDao loginRecordInfoDao;

	/**
	 * 用户登录
	 * 
	 * @param userInfo
	 * @return
	 */
	public UserInfo doLogin(UserInfo userInfo) {
		userInfo.setPassword(EncryptUtil.GetMD5Code(userInfo.getPassword()));
		// 返回用户信息
		UserInfo user = userInfoDao.doLogin(userInfo);
		if (user != null && user.getRoleId() != null) {
			String roleIds = userInfoDao.getChildList(user.getRoleId());
			if (Utils.isNotEmptyString(roleIds)) {
				user.setChildRoleIdString(roleIds);
				String[] childRoleIdArray = null;
				if (roleIds.contains(",")) {
					childRoleIdArray = roleIds.split(",");
				} else {
					childRoleIdArray = new String[] { roleIds };
				}
				user.setChildRoleIdArray(childRoleIdArray);
			}
		}
		return user;
	}

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo findUserById(Integer userId) {
		// 返回用户信息
		return userInfoDao.findUserById(userId);
	}

	/**
	 * 根据用户ID批量删除用户信息
	 * 
	 * @param params
	 * @return
	 */
	public String deleteUsersById(Long[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		params.put("updateUser", LoginInfoUtil.getCurrentUser().getUserId());
		params.put("updateDate", new Date());
		int result = userInfoDao.deleteUsersById(params);
		if (result > 0) {
			userRoleDao.deleteUserRolesByUserId(params);
		}
		return result > 0 ? Constants.DELETE_SUCCESS : Constants.DELETE_FAILED;
	}

	/**
	 * 新增会员
	 * 
	 * @param userInfo
	 * @return
	 */
	public String insertUser(UserInfo userInfo) {
		// 判断用户名是否已存在
		int count = userInfoDao.getUserCountByLoginId(userInfo);
		if (count > 0) {
			return Constants.LOGIN_ID_EXISTS;
		}
		// 获取当前登录用户的ID
		Timestamp createDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		// 加密密码
		userInfo.setPassword(EncryptUtil.GetMD5Code(Config.getDefaultPassword()));
		// if (userInfo.getPassword() == null) {
		// userInfo.setPassword(Config.getDefaultPassword());
		// }
		UserInfo currentUser = LoginInfoUtil.getCurrentUser();
		if (currentUser != null) {
			userInfo.setCreateUser(currentUser.getUserId());
			userInfo.setUpdateUser(currentUser.getUserId());
		}
		userInfo.setUserStatus(Constants.STATUS_NORMAL);
		userInfo.setCreateDate(createDate);
		userInfo.setUpdateDate(createDate);
		// 新增用户
		int result = userInfoDao.insertUser(userInfo);
		if (result > 0) {
			UserRole userRole = new UserRole();
			userRole.setRoleId(userInfo.getRoleId());
			userRole.setUserId(userInfo.getUserId());
			userRoleDao.insertUserRole(userRole);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 根据用户ID更新用户信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public String updateUserById(UserInfo userInfo) {
		UserInfo orgUserInfo = userInfoDao.getUserByLoginId(userInfo);
		// 判断更新的用户是否与根据用户名查询出的用户是否是同一个，如果不是，提示用户名重复
		if (orgUserInfo != null && !Utils.toString(userInfo.getUserId()).equals(Utils.toString(orgUserInfo.getUserId()))) {
			return Constants.LOGIN_ID_EXISTS;
		}
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		// 设定用户状态为正常
		userInfo.setUpdateUser(userId);
		userInfo.setUpdateDate(updateDate);
		// 更新用户信息
		int result = userInfoDao.updateUserById(userInfo);
		if (result > 0) {
			// 删除角色
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idArray", new Long[] { userInfo.getUserId() });
			userRoleDao.deleteUserRolesByUserId(params);
			// 新增角色
			UserRole userRole = new UserRole();
			userRole.setRoleId(userInfo.getRoleId());
			userRole.setUserId(userInfo.getUserId());
			userRoleDao.insertUserRole(userRole);
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 查询会员信息
	 * 
	 * @param userInfo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> getUserList(UserInfo userInfo, int pageIndex, int pageSize) {
		// 查询数据笔数
		int count = userInfoDao.getUserCount(userInfo);

		// 计算每页起始下标
		int startIndex = Utils.getStartIndex(pageIndex, pageSize, count);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userInfo", userInfo);
		params.put("startIndex", startIndex);
		params.put("pageSize", pageSize);
		// 查询用户列表
		List<UserInfo> userList = userInfoDao.getUserList(params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 数据笔数
		resultMap.put("total", count);
		// 用户信息
		resultMap.put("rows", userList);
		return resultMap;
	}

	/**
	 * 
	 * updatePasswordById(根据用户ID批量重置用户密码)
	 * 
	 * @param params
	 *            Map对象，包含了一个用户ID数组、用户密码、更新者ID、更新时间 @return int
	 *            返回执行结果(成功：RESET_SUCCESS;失败：RESET_FAILED) @exception
	 */
	public String updatePasswordById(Integer[] idArray) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", idArray);
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		// 设定更新者
		params.put("updateUser", userId);
		// 设定更新时间
		params.put("updateDate", updateDate);
		// 重置密码
		params.put("password", EncryptUtil.GetMD5Code(Config.getDefaultPassword()));
		int result = userInfoDao.updatePasswordById(params);
		return result > 0 ? Constants.RESET_SUCCESS : Constants.RESET_FAILED;
	}

	/**
	 * 
	 * updatePassword(修改用户密码)
	 * 
	 * @param userInfo
	 *            用户信息对象 @return String
	 *            返回执行结果(成功：RESET_SUCCESS;失败：RESET_FAILED) @exception
	 */
	public String updatePassword(UserInfo userInfo) {
		// 获取当前登录用户的ID
		long userId = LoginInfoUtil.getCurrentUser().getUserId();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idArray", new Long[] { userId });
		Timestamp updateDate = Utils.getTimestampDate(Utils.DATE_FORMAT_1);
		// 设定更新者
		params.put("updateUser", userId);
		// 设定更新时间
		params.put("updateDate", updateDate);
		// 重置密码
		params.put("password", EncryptUtil.GetMD5Code(userInfo.getPassword()));
		int result = userInfoDao.updatePasswordById(params);
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * inserLoginRecord(添加登录记录)
	 * 
	 * @param record
	 *            登录记录 @return int 返回新增数据笔数 @exception
	 */
	public int inserLoginRecord(LoginRecord record) {
		// 用户对象
		UserInfo userInfo = new UserInfo();
		// 用户ID
		userInfo.setUserId(record.getUserId());
		// 登录时间
		userInfo.setLoginTime(record.getLoginDate());
		// 更新者ID
		userInfo.setUpdateUser(record.getUserId());
		// 更新时间
		userInfo.setUpdateDate(record.getLoginDate());
		// 根据用户ID更新最后登录时间
		userInfoDao.updateLoginTimeById(userInfo);
		// 添加新的登录记录
		return loginRecordInfoDao.inserLoginRecord(record);
	}
}
