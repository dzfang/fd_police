package com.soft.manager.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.soft.common.domain.TWebSite;
import com.soft.manager.Constants;
import com.soft.manager.dao.TWebSiteDao;

@Service
public class TWebSiteService {
	@Resource
	private TWebSiteDao WebSiteDao;

	private static TWebSite webSite;

	/**
	 * 
	 * insertWebSite(新增网络空间信息)
	 * 
	 * @param record
	 *            TWebSite对象
	 * @return String 成功：SAVE_SUCCESS；失败：SAVE_FAILED
	 * @exception
	 */
	public String insertWebSite(TWebSite record) {

		int count = WebSiteDao.getWebSiteCount();
		int result = 0;
		if (count > 0) {
			result = WebSiteDao.updateWebSiteById(record);
		} else {
			result = WebSiteDao.insertWebSite(record);
		}
		//刷新内存数据
		if (result > 0) {
			webSite = WebSiteDao.getWebSiteConfig();
		}
		return result > 0 ? Constants.SAVE_SUCCESS : Constants.SAVE_FAILED;
	}

	/**
	 * 
	 * getWebSiteConfig(查询网络空间信息)
	 * 
	 * @return TWebSite 网络空间信息对象
	 * @exception
	 */
	public TWebSite getWebSiteConfig() {
		if (webSite == null) {
			webSite = WebSiteDao.getWebSiteConfig();
		}
		return webSite;
	}

}
