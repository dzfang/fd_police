package com.soft.util;


import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p>所有Tag的基类</p>
 *
 * @page Core
 * @module Ice
 * @author Ice框架组
 * @since 2012-11-27
 * @version 1.0
 */
public abstract class TagBase extends TagSupport
{
    /* 序列号 */
    private static final long serialVersionUID = 1L;

    /**
     * 所处的tabIndex
     */
    protected String tabindex;

    // Mouse Events

    /** Mouse click event. */
    private String onclick;

    /** Mouse double click event. */
    private String ondblclick;

    /** Mouse over component event. */
    private String onmouseover;

    /** Mouse exit component event. */
    private String onmouseout;

    /** Mouse moved over component event. */
    private String onmousemove;

    /** Mouse pressed on component event. */
    private String onmousedown;

    /** Mouse released on component event. */
    private String onmouseup;

    // Keyboard Events

    /** Key down in component event. */
    private String onkeydown;

    /** Key released in component event. */
    private String onkeyup;

    /** Key down and up together in component event. */
    private String onkeypress;

    // Text Events

    /** Text selected in component event. */
    private String onselect;

    /** Content changed after component lost focus event. */
    private String onchange;

    // Focus Events and States

    /** Component lost focus event. */
    private String onblur;

    /** Component has received focus event. */
    private String onfocus;

    /** Component is disabled. */
    private boolean disabled = false;

    /** Component is readonly. */
    private boolean readonly = false;

    /** Named Style class associated with component. */
    private String style;
    private String styleClass;

    /** The alternate text of this element. */
    private String alt;

    /** The advisory title of this element. */
    private String title;
    private String width;

    /**
     *
     * @param htmlBuf 输出内容
     * @throws Exception 异常
     */
    public abstract void startTag(StringBuilder htmlBuf) throws Exception;

    /**
     *
     * 标记开始执行
     *
     * @return 标识
     */
    @Override
    public int doStartTag()
    {
        try
        {
            StringBuilder htmlBuf = new StringBuilder();
            startTag(htmlBuf);
            pageContext.getOut().write(htmlBuf.toString());
            pageContext.getOut().flush();
        }
        catch (Exception e)
        {
            LogService logger = LogService.getLogger(this.getClass());
            logger.error("", e.getMessage(), e, null);
        }
        return SKIP_BODY;
    }

    public String getTabindex() {
		return tabindex;
	}

	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	public String getOnmouseover() {
		return onmouseover;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public String getOnmouseout() {
		return onmouseout;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public String getOnmousemove() {
		return onmousemove;
	}

	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	public String getOnmousedown() {
		return onmousedown;
	}

	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	public String getOnmouseup() {
		return onmouseup;
	}

	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	public String getOnselect() {
		return onselect;
	}

	public void setOnselect(String onselect) {
		this.onselect = onselect;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
     * Release any acquired resources.
     */
    @Override
    public void release()
    {
        super.release();
        alt = null;
        onclick = null;
        ondblclick = null;
        onmouseover = null;
        onmouseout = null;
        onmousemove = null;
        onmousedown = null;
        onmouseup = null;
        onkeydown = null;
        onkeyup = null;
        onkeypress = null;
        onselect = null;
        onchange = null;
        onblur = null;
        onfocus = null;
        disabled = false;
        readonly = false;
        styleClass = null;
        tabindex = null;
        title = null;
    }

    /**
     * Prepares  attributes for inclusion in the component's HTML tag.
     *
     * @return The prepared String for inclusion in the HTML tag.
     */
    protected String prepareAttributes()
    {
        StringBuilder styles = new StringBuilder();


        if (getStyle() != null)
        {
            prepareAttribute(styles, "style", getStyle());
        }

        if (getStyleClass() != null)
        {
            prepareAttribute(styles, "class", getStyleClass());
        }
        
        if (getWidth() != null)
        {
        	prepareAttribute(styles, "width", getWidth());
        }
        
        if (getTabindex() != null)
        {
        	prepareAttribute(styles, "tabindex", getTabindex());
        }
        if (readonly)
        {
            styles.append(" readonly ");
        }
        if (disabled)
        {
        	styles.append(" disabled ");
        }
        //prepareAttribute(styles, "describe", getDescribe());

        return styles.toString();

    }

    /**
     * Prepares the event handlers for inclusion in the component's HTML tag.
     *
     * @return The prepared String for inclusion in the HTML tag.
     */
    protected String prepareEventHandlers()
    {
        StringBuilder handlers = new StringBuilder();
        prepareMouseEvents(handlers);
        prepareKeyEvents(handlers);
        prepareTextEvents(handlers);
        return handlers.toString();
    }

    /**
     * Prepares the mouse event handlers, appending them to the the given StringBuffer.
     *
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareMouseEvents(StringBuilder handlers)
    {
        prepareAttribute(handlers, "onclick", getOnclick());
        prepareAttribute(handlers, "ondblclick", getOndblclick());
        prepareAttribute(handlers, "onmouseover", getOnmouseover());
        prepareAttribute(handlers, "onmouseout", getOnmouseout());
        prepareAttribute(handlers, "onmousemove", getOnmousemove());
        prepareAttribute(handlers, "onmousedown", getOnmousedown());
        prepareAttribute(handlers, "onmouseup", getOnmouseup());

    }

    /**
     * Prepares the keyboard event handlers, appending them to the the given StringBuffer.
     *
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareKeyEvents(StringBuilder handlers)
    {
        prepareAttribute(handlers, "onkeydown", getOnkeydown());
        prepareAttribute(handlers, "onkeyup", getOnkeyup());
        prepareAttribute(handlers, "onkeypress", getOnkeypress());
    }

    /**
     * Prepares the text event handlers, appending them to the the given StringBuffer.
     *
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareTextEvents(StringBuilder handlers)
    {
        prepareAttribute(handlers, "onselect", getOnselect());
        prepareAttribute(handlers, "onchange", getOnchange());

    }

    /**
     * 'Hook' to enable tags to be extended and additional attributes added.
     *
     * @param handlers The StringBuffer that output will be appended to.
     */
    protected void prepareOtherAttributes(StringBuilder handlers)
    {
    }

    /**
     * Prepares an attribute if the value is not null, appending it to the the given StringBuffer.
     *
     * @param handlers The StringBuffer that output will be appended to.
     * @param name name.
     * @param value value.
     */
    protected void prepareAttribute(StringBuilder handlers, String name, Object value)
    {
        if (value != null)
        {
            handlers.append(" ");
            handlers.append(name);
            handlers.append("=\"");
            handlers.append(value);
            handlers.append("\"");
        }
    }

    protected Object findAttribute(String name)
    {
        return pageContext.findAttribute(name);
    }

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
}
