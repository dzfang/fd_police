package com.soft.manager.dao;

import com.soft.common.domain.TWebSite;

public interface TWebSiteDao {

	/**
	 * 
	 * insertWebSite(新增网站配置信息)
	 * 
	 * @param record
	 *            网站配置信息
	 * @return int 返回新增的数据笔数
	 * @exception
	 */
	public int insertWebSite(TWebSite record);
  
	/**
	 * 
	 * updateWebSiteById(根据网站配置信息ID更新网站配置信息)
	 * 
	 * @param record
	 *            网站配置信息
	 * @return int 返回更新的数据笔数
	 * @exception
	 */
	public int updateWebSiteById(TWebSite record);

	/**
	 * 
	 * getWebSiteCount(查询网站配置信息笔数)
	 * 
	 * @return int 返回数据总笔数
	 * @exception
	 */
	public int getWebSiteCount();

	/**
	 * 
	 * getWebSiteConfig(D查询网站配置信息)
	 * 
	 * @return TWebSite 网站配置信息对象
	 * @exception
	 */
	public TWebSite getWebSiteConfig();
}
