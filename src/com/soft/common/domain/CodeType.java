/** 
 * @项目名：Z-Cherry Mobile 
 * @包名：com.zcherry.common.domain 
 * @文件名：CodeType.java 
 * @版本信息：1.0.0 
 * @创建时间：2015年5月13日 上午10:28:27 
 *  
 */
package com.soft.common.domain;

import java.util.Date;
/**
 * 
 *  
 * @类名称：CodeType 
 * @类描述：(系统类别) 
 * @创建人：黄梦姣 
 * @创建时间：2015年5月13日 上午10:28:27 
 * @修改人：黄梦姣 
 * @修改时间：2015年5月13日 上午10:28:27 
 * @修改备注： 
 * @version 1.0.0 
 *
 */
public class CodeType extends BaseEntity{
	/**
	 * 序列化的版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 代码类型
	 */
    private String type;
    /**
	 * 类别名称
	 */
    private String text;
 
    /**
     * 区分父页面中的控件ID
     */
    private String popType;
    private String operate;

    public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
 
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getPopType() {
        return popType;
    }

    public void setPopType(String popType) {
        this.popType = popType == null ? null : popType.trim();
    }
}