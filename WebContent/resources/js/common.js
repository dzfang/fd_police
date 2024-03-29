var uploadDir = "/resources/upload/";
/**
 * 调节宽度
 */
//$(function() {
//	$("div[class='datagrid-wrap panel-body panel-body-noheader']").css("width",
//			"auto");
//	// 去掉所有checkbox的边框
//	$("input:checkbox").css("border", "none");
//
//	// 取消grid下边框
//	$(".datagrid").find("div").first().css("border-bottom", "0px");
//
//	// 使IE浏览器下，回车可以换行
//	$("textarea").focus(function() {
//		$(this).css("white-space", "pre-wrap");
//	})
//	// 窗体大小改变时，重置grid的大小
//	/*
//	 * $(window).resize(function() { $('#dataList').datagrid('resize', { width :
//	 * $(window).width() - 10, height : $(window).height() - 35 }); });
//	 */
//	var toolbar = $("#toolbar");
//	if (toolbar != undefined) {
//		if ($.trim($(toolbar).html()) == "") {
//			$("#dataList").datagrid({
//				toolbar : ''
//			});
//		}
//	}
//});
/**
 * Grid初始化
 */
function dataGridInit1(columns, toolbar) {
	// var pager = $('#pagination');
	// pager.pagination({
	// // 每页显示的记录条数，默认为20
	// pageSize : 20,
	// // 可以设置每页记录条数的列表
	// pageList : [ 20, 40, 60, 100 ],
	// onSelectPage : loadData
	// });
	$("#dataList").datagrid({
		pagination : false,// 允许分页
		rownumbers : true,// 行号
		nowrap : false,//允许换行
		pageSize : 10,// 每一页数据数量
		pageList : [ 10, 20, 50 ],
		columns : columns,
		toolbar : toolbar
	})

	// 工具栏 加控件
	var pager = $("#dataList").datagrid().datagrid('getPager');
	pager.pagination({
		/*
		 * // 每页显示的记录条数，默认为20 pageSize : 5, // 可以设置每页记录条数的列表 pageList : [ 5, 40,
		 * 60, 100 ],
		 */
		onSelectPage : loadData
	});
}
/**
 * 查询Grid数据
 */
function findGridData1() {
	// var pager = $('#pagination');
	// var options = pager.data("pagination").options;
	// loadData(1, options.pageSize);
	var pageSize = $('#dataList').datagrid('options').pageSize;
	loadData(1, pageSize);

}

/**
 * 重新加载数据
 */
function reload1() {
	var options = $('#dataList').datagrid('options');
	reloadData(options.pageNumber, options.pageSize);
}
/**
 * Grid初始化
 */
function dataGridInit(columns, toolbar) {
	 var pager = $('#pagination');
	 pager.pagination({
	 // 每页显示的记录条数，默认为20
	 pageSize : 20,
	 // 可以设置每页记录条数的列表
	 pageList : [ 10, 20, 50 ],
	 onSelectPage : loadData
	 });
	$("#dataList").datagrid({
		pagination : false,// 允许分页
		rownumbers : true,// 行号
		nowrap : false,//允许换行
		columns : columns,
		toolbar : toolbar
	})
}
/**
 * 查询Grid数据
 */
function findGridData() {
	var pager = $('#pagination');
	var options = pager.data("pagination").options;
	loadData(1, options.pageSize);
}

/**
 * 重新加载数据
 */
function reload() {
	var pager = $('#pagination');
	var options = pager.data("pagination").options;
	reloadData(options.pageNumber, options.pageSize);
}

/**
 * 删除后执行的操作
 * 
 * @param data
 */
function afterDelete(data) {
	if (data != null) {
		if (data == "DELETE_SUCCESS") {
			$.messager.alert('提示信息', message.DELETE_SUCCESS, "info");
			reload();
		} else {
			$.messager.alert("提示信息", message.byId(data), "info");
		}
	}
}
/**
 * 操作成功
 * 
 * @param data
 */
function afterOperate(data) {
	if (data != null) {
		if (data == "OPERATE_SUCCESS") {
			$.messager.alert('提示信息', message.OPERATE_SUCCESS, "info");
			reload();
		} else {
			$.messager.alert("提示信息", message.byId(data), "info");
		}
	}
}
/**
 * 省市区街道四级联动
 */
function addressInit() {
	var array = new Array();
	array.push({
		id : 'addressProvince',
		type : '100',
		hid : 'hidProvince'
	});
	array.push({
		id : 'addressCity',
		type : '101',
		hid : 'hidCity'
	});
	array.push({
		id : 'addressDistrict',
		type : '102',
		hid : 'hidDistrict'
	});
	array.push({
		id : 'addressStreet',
		type : '103',
		hid : 'hidStreet'
	});
	// 初始化区域下拉列表
	comboboxInit(array, 0, "");
}

/**
 * 初始化区域下拉列表
 * 
 * @param array
 * @param index
 * @param newValue
 */
function comboboxInit(array, index, newValue) {
	var item = array[index];
	var combobox = $("#" + item.id);
	combobox.combobox({
		disabled : false
	});
	combobox.combobox({
		url : basePath + '/xtbm/getXtbmListHadNull.do?bmlx=' + item.type
				+ '&code2=' + newValue,
		valueField : 'code1',
		textField : 'value',
		onLoadSuccess : function() {
			var value = $("#" + item.hid).val();
			if (value != "") {
				combobox.combobox('setValue', value);
			}
			// 设置下拉列表空白项高度
			$(".combobox-item").height("16px");
		},
		onChange : function(newValue, oldValue) {
			$("#" + item.hid).val("");
			if (index < array.length - 1) {
				for (var i = index + 1; i < array.length; i++) {
					$("#" + array[i].id).combobox("clear").combobox("disable");
				}
				if (newValue != "") {
					comboboxInit(array, index + 1, newValue);
				}
			}
		}
	});
}
/**
 * 上传控件
 * 
 * @param id
 * @param url
 * @param limit
 * @param w
 * @param h
 * @param image
 * @param imageId
 * @param hasImage
 * @param hid
 */
function uploadifyInit(id, url, limit, w, h, image, imageId, hasImage, hid) {
	var text = "";
	var deleteId = "del_" + imageId;
	text += '<div class="imgDiv">';
	text += '<img id="' + imageId + '" class="btn-img" src="' + image + '"/>';
	text += '<img style="cursor:pointer;" id="'
			+ deleteId
			+ '" src="'
			+ basePath
			+ '/resources/images/delete.png" class="delete" onclick="deleteImage(\''
			+ deleteId + '\',\'' + imageId + '\',\'' + hid + '\')" />';
	text += '</div>';

	$("#" + id)
			.uploadify(
					{
						swf : basePath + '/resources/uploadify/uploadify.swf',
						uploader : url,
						buttonText : text,
						queueSizeLimit : limit,
						fileSizeLimit : "1MB",
						width : w,
						height : h,
						auto : true,
						// 在浏览窗口底部的文件类型下拉菜单中显示的文本
						fileTypeDesc : '支持的格式：',
						// 允许上传的文件后缀
						fileTypeExts : '*.jpg;*.jpeg;*.png;*.bmp',
						overrideEvents : [ 'onUploadError', 'onSelectError' ],
						onSelectError : uploadify_onSelectError,
						onUploadError : uploadify_onUploadError,

						// 上传到服务器，服务器返回相应信息到data里
						'onUploadSuccess' : function(file, data, response) {
							if (data) {
								if (limit > 1) {
									imageId = "image_" + file.id;
									deleteId = "delete_" + file.id;
									image = basePath + uploadDir + data;
									var content = "";
									content += '<div class="imgDiv">';
									content += '<img id="' + imageId
											+ '" class="btn-img" src="' + image
											+ '"/>';
									content += '<img style="cursor:pointer;" id="'
											+ deleteId
											+ '" src="'
											+ basePath
											+ '/resources/images/delete.png" class="delete" onclick="deleteLi(\''
											+ file.id + '\')" />';
									content += '<input type="hidden" name="articleImage" value="'
											+ data + '"/>';
									content += '</div>';
									$('#j_pic70 > li:last-child').before(
											'<li id="li_' + file.id + '">'
													+ content + '</li>')

									addImageCss(imageId, w, h, deleteId, true);

									var count = $(".imgDiv").length;
									if (count > 9) {
										$("#btnUserImage").hide();
									}
								} else {
									var src = basePath + uploadDir + data;
									var img = $("#" + imageId);
									img.attr("src", src);
									$("#" + deleteId).show();
									$("#" + hid).val(data);
								}
							}
						}
					});
	addImageCss(imageId, w, h, deleteId, hasImage);
}

var uploadify_onSelectError = function(file, errorCode, errorMsg) {
	var msgText = "上传失败\n";
	switch (errorCode) {
	case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
		msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
		break;
	case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
		msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
		break;
	case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
		msgText += "文件大小为0";
		break;
	case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
		msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
		break;
	default:
		msgText += "错误代码：" + errorCode + "\n" + errorMsg;
	}
	$.messager.alert('提示信息', msgText);
};

var uploadify_onUploadError = function(file, errorCode, errorMsg, errorString) {
	// 手工取消不弹出提示
	if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
			|| errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
		return;
	}
	var msgText = "上传失败\n";
	switch (errorCode) {
	case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
		msgText += "HTTP 错误\n" + errorMsg;
		break;
	case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
		msgText += "上传文件丢失，请重新上传";
		break;
	case SWFUpload.UPLOAD_ERROR.IO_ERROR:
		msgText += "IO错误";
		break;
	case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
		msgText += "安全性错误\n" + errorMsg;
		break;
	case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
		msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
		break;
	case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
		msgText += errorMsg;
		break;
	case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
		msgText += "找不到指定文件，请重新操作";
		break;
	case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
		msgText += "参数错误";
		break;
	default:
		msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n" + errorMsg
				+ "\n" + errorString;
	}
	$.messager.alert('提示信息', msgText);
}

/**
 * 从画面上删除上传的图片
 * 
 * @param deleteId
 * @param orgImage
 * @param imageId
 * @param hId
 */
function deleteImage(deleteId, imageId, hId) {
	var buttonImage = basePath + '/resources/images/btn/btn_image.png';
	$("#" + imageId).attr("src", buttonImage);
	$("#" + deleteId).hide();
	$("#" + hId).val("");

}
/**
 * 添加图片样式
 * 
 * @param imageId
 * @param w
 * @param h
 * @param deleteId
 * @param hasImage
 */
function addImageCss(imageId, w, h, deleteId, hasImage) {
	var img = $("#" + imageId);
	img.css("max-width", w + "px");
	img.css("max-height", h + "px");
	img.css("_width", 'expression(this.width>=' + w + '&&this.width/' + w
			+ '>=this.height/' + w + '?' + w + 'px":"auto")');
	img.css("_height", 'expression(this.width>=' + h + '&&this.width/' + h
			+ '>=this.height/' + h + '?' + h + 'px":"auto")');
	if (hasImage) {
		$("#" + deleteId).show();
	}
}
/**
 * 是否列格式化
 * 
 * @param value
 * @param row
 * @param index
 */
function booleanFormatter(value, row, index) {
	if (value == null) {
		return "";
	}
	return value ? "是" : "否";
}
/**
 * 日期格式化
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function dateFormatter(value, row, index) {
	if (isNull(value) == "") {
		return "";
	}

	var registerDate = new Date(value);
	return registerDate.format("yyyy-MM-dd");
}
/**
 * 时间格式化
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function timeFormatter(value, row, index) {
	if (isNull(value) == "") {
		return "";
	}
	var registerDate = new Date(value);
	return registerDate.format("yyyy-MM-dd hh:mm:ss");
}
/**
 * 日期格式化
 * 
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		// month
		"M+" : this.getMonth() + 1,
		// day
		"d+" : this.getDate(),
		// hour
		"h+" : this.getHours(),
		// minute
		"m+" : this.getMinutes(),
		// second
		"s+" : this.getSeconds(),
		// quarter
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		// millisecond
		"S" : this.getMilliseconds()
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
/**
 * 打开弹出窗口
 * 
 * @param href
 * @param width
 * @param height
 */
function openWindow(title, href, width, height, windowId) {
	windowId = windowId == undefined ? "myWindow" : windowId;
	var window = $('#' + windowId);
	window.remove();
	var window = '<div id="' + windowId
			+ '" class="easyui-dialog" closed="true"></div>';

	$('body').append(window);
	$('#' + windowId).window(
			{
				title : title,
				width : width === undefined ? 600 : width,
				height : height === undefined ? 400 : height,
				content : '<iframe scrolling="yes" frameborder="0"  src="'
						+ href + '" ></iframe>',
				href : href,
				modal : true,
				minimizable : false,
				maximizable : false,
				shadow : false,
				cache : false,
				closed : false,
				collapsible : false,
				resizable : false,
			});
}
/**
 * 关闭弹出窗口
 */
function closeWindow(windowId) {
	windowId = windowId == undefined ? "myWindow" : windowId;
	$('#' + windowId).window('close')
}

/**
 * 删除li
 * 
 * @param fileId
 */
function deleteLi(fileId) {
	$("#li_" + fileId).remove();
	var count = $(".imgDiv").length;
	if (count > 9) {
		$("#btnUserImage").hide();
	} else {
		$("#btnUserImage").show();
	}
}
/**
 * 将null转换为空字符串
 */
function isNull(value) {
	if (value == null) {
		return "";
	}
	return value;
}
/**
 * 日期相减
 * 
 * @param startDate
 * @param endDate
 * @returns {Number}
 */
function dateDiff(startDate, endDate) {
	return (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
}

/**
 * indexOf方法，hack IE8 BUG
 */
Array.prototype.indexOf = function(item) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == item)
			return i;
	}
	return -1;
}

/**
 * 判断是否是由于session超时引起的，如果是，跳转到登陆页面
 * 
 * @param xhr
 * @param status
 * @param e
 */
function doError(xhr, status, e) {
	var sessionStatus = xhr.getResponseHeader('sessionstatus');
	if (sessionStatus == 'timeout') {
		var top = getTopWinow();
		var msg = "由于您长时间没有操作, session已过期, 请重新登录!";
		$.messager.confirm("提示信息", msg, function(r) {
			if (r) {
				top.location.href = basePath + '/admin/login.do';
			}
		});
	} else {
		$.messager.alert('提示信息', message.SYSTEM_ERROR
				+ "<BR/>XMLHttpRequest.status=" + xhr.status
				+ ",XMLHttpRequest.readyState=" + xhr.readyState + ",status="
				+ status);
	}
}
/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * 
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}

/**
 * Ajax执行完执行
 * 
 * @param event
 * @param xhr
 * @param settings
 */
function onComplete(event, xhr, settings) {
	$.messager.progress('close');
}
/**
 * Ajax提交前执行
 */
function onBeforeSend() {
	var win = $.messager.progress({
		title : "请稍等...",
		text : "",
		msg : "正在处理中，请稍等..."
	});
}

/**
 * 重置确认框按钮文本
 */
function resetConfirmButtonText() {
	$.messager.defaults.ok = "确定";
	$.messager.defaults.cancel = "取消";
}
/**
 * 显示确认框，确认框按钮文本分别为是，否
 * 
 * @param message
 *            显示的确认信息
 * @param okFunc
 *            点击“是”，调用的函数
 * @param cancelFunc
 *            点击“否”，调用的桉树
 */
function showYesNoConfirm(message, okFunc, cancelFunc) {
	$.messager.defaults.ok = "是";
	$.messager.defaults.cancel = "否";
	$.messager.confirm('提示信息', message, function(r) {
		if (r) {
			okFunc();
		} else {
			cancelFunc();
		}
	});
}

/**
 * 用户添加画面初始化
 */
function openPasswordWindow() {
	var href = basePath + '/user/updatePasswordInit.do';
	openWindow("修改密码", href, 320, 150);
}
/**
 * 将input中的值同步到隐藏域中
 * 
 * @param obj
 * @param id
 */
function syncValue(obj, id) {
	$("#" + id).val($(obj).val());
}
/**
 * 创建年月控件
 * 
 * @param id
 */
function monthbox(id) {
	var p = $('#' + id).datebox('panel'), // 日期选择对象
	tds = false, // 日期选择对象中月份
	span = p.find('span.calendar-text'); // 显示月份层的触发控件

	$('#' + id).datebox({
		onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
			span.trigger('click'); // 触发click事件弹出月份层
			if (!tds)
				setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
					tds = p.find('div.calendar-menu-month-inner td');
					tds.click(function(e) {
						e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件
						var year = /\d{4}/.exec(span.html())[0]// 得到年份
						, month = parseInt($(this).attr('abbr'), 10); // 月份
						$('#' + id).datebox('hidePanel')// 隐藏日期对象
						.datebox('setValue', year + '-' + month); // 设置日期的值
					});
				}, 0)
		},
		parser : function(s) {// 配置parser，返回选择的日期
			if (!s)
				return new Date();
			var arr = s.split('-');
			return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		},
		formatter : function(d) {
			return d.format("yyyy-MM");
		}// 配置formatter，只返回年月
	});
}
/**
 * 设置combogrid的分页信息
 * 
 * @param grid
 */
function combogridPager(grid) {
	var pager = grid.combogrid("grid").datagrid('getPager');
	$(pager).pagination({
		showPageList : false,
		displayMsg : '共{total}条记录'
	});
}
/**
 * 初始化教师下拉列表
 * 
 * @param comboId
 *            控件ID
 * @param mult
 *            是否可多选
 * @param width
 *            控件宽度
 */
function combogridUser(comboId, mult, width) {

	var tb = "<div class='datagrid-toolbar' style='width: 100%; height: 30px; display:none;' id='toolbar_"
			+ comboId
			+ "' ><table><tr><th style='padding:0 5px;'>账号:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboUserCode_"
			+ comboId
			+ "\" )'></td><th  style='padding:0 5px;'>姓名:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboUserName_"
			+ comboId
			+ "\" )' /></td><td><a href='javascript:void(0)' class='easyui-linkbutton c0' onclick=\"findUser('"
			+ comboId
			+ "')\" style=\"padding:0 3px;height:22px;\">查询</a></td></tr></table></div>";

	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : width,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'userId',
		textField : 'userName',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : basePath + '/comm/getUserList.do',
		columns : [ [ {
			field : 'loginId',
			title : '账号',
			width : '80'
		}, {
			field : 'userName',
			title : '姓名',
			width : '70'
		}, {
			field : 'userType',
			title : '用户类型',
			width : '70',
			align : 'center',
			formatter : function(value, row, index) {
				if (isNull(value) == "001") {
					return "教师";
				} else {
					return "学生";
				}
			}
		}, {
			field : 'telephone',
			title : '电话号码',
			width : '90'
		}, {
			field : 'email',
			title : '邮箱',
			width : '90'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
			var text = $("#comboUserText_" + comboId).val();
			if ($.trim(text) != "") {
				grid.combogrid('setText', text);
				$("#comboUserText_" + comboId).val("");
			}
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findUser(comboId) {
	var code = "comboUserCode_" + comboId;
	var name = "comboUserName_" + comboId;
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'loginId' : $('#' + code).val(),
		'userName' : $('#' + name).val()
	});
}

/**
 * 初始化教师下拉列表
 * 
 * @param comboId
 *            控件ID
 * @param mult
 *            是否可多选
 * @param width
 *            控件宽度
 */
function combogridTeacher(comboId, mult, width) {

	var tb = "<div class='datagrid-toolbar' style='width: 100%; height: 30px; display:none;' id='toolbar_"
			+ comboId
			+ "' ><table><tr><th style='padding:0 5px;'>账号:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboTeacherCode_"
			+ comboId
			+ "\" )'></td><th  style='padding:0 5px;'>姓名:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboTeacherName_"
			+ comboId
			+ "\" )' /></td><td><a href='javascript:void(0)' class='easyui-linkbutton c0' onclick=\"findTeacher('"
			+ comboId
			+ "')\" style=\"padding:0 3px;height:22px;\">查询</a></td></tr></table></div>";

	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : width,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'userId',
		textField : 'userName',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : basePath + '/comm/getTeacherList.do',
		columns : [ [ {
			field : 'loginId',
			title : '账号',
			width : '100'
		}, {
			field : 'userName',
			title : '姓名',
			width : '100'
		}, {
			field : 'telephone',
			title : '电话号码',
			width : '100'
		}, {
			field : 'orgName',
			title : '所属实验室',
			width : '100'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
			var text = $("#comboTeacherText_" + comboId).val();
			if ($.trim(text) != "") {
				grid.combogrid('setText', text);
				$("#comboTeacherText_" + comboId).val("");
			}
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findTeacher(comboId) {
	var code = "comboTeacherCode_" + comboId;
	var name = "comboTeacherName_" + comboId;
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'loginId' : $('#' + code).val(),
		'userName' : $('#' + name).val()
	});
}

/**
 * 初始化供应商下拉列表
 * 
 * @param comboId
 *            控件ID
 * @param mult
 *            是否可多选
 * @param width
 *            控件宽度
 */
function combogridSupplier(comboId, mult, width) {

	var tb = "<div class='datagrid-toolbar' style='width: 100%; height: 30px; display:none;' id='toolbar_"
			+ comboId
			+ "' ><table><tr><th style='padding:0 5px;'>供应商:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboSupplierCode_"
			+ comboId
			+ "\" )'></td><th  style='padding:0 5px;'>负责人:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboSupplierName_"
			+ comboId
			+ "\" )' /></td><td><a href='javascript:void(0)' class='easyui-linkbutton c0' onclick=\"findSupplier('"
			+ comboId
			+ "')\" style=\"padding:0 3px;height:22px;\">查询</a></td></tr></table></div>";

	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : width,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'id',
		textField : 'name',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : basePath + '/comm/getSupplierList.do',
		columns : [ [ {
			field : 'name',
			title : '供应商',
			width : '100'
		}, {
			field : 'principal',
			title : '负责人',
			width : '100'
		}, {
			field : 'contact',
			title : '联系人',
			width : '100'
		}, {
			field : 'contactPhone',
			title : '联系电话',
			width : '100'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
			var text = $("#comboSupplierText_" + comboId).val();
			if ($.trim(text) != "") {
				grid.combogrid('setText', text);
				$("#comboSupplierText_" + comboId).val("");
			}
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findSupplier(comboId) {
	var code = "comboSupplierCode_" + comboId;
	var name = "comboSupplierName_" + comboId;
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'name' : $('#' + code).val(),
		'principal' : $('#' + name).val()
	});
}

function combogridEquipment(comboId, mult, width, type) {
	var tb = "<div class='datagrid-toolbar' style='width: 100%; height: 30px; display:none;' id='toolbar_"
			+ comboId
			+ "' ><table><tr><th style='padding:0 5px;'>设备名称:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboEquipmentName_"
			+ comboId
			+ "\" )'></td><td><a href='javascript:void(0)' class='easyui-linkbutton c0' onclick=\"findEquipment('"
			+ comboId
			+ "')\" style=\"padding:0 3px;height:22px;\">查询</a></td></tr></table></div>";
	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : width,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'id',
		textField : 'name',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : basePath + '/comm/getEquipmentList.do?borrowable=' + type,
		columns : [ [ {
			field : 'name',
			title : '设备名称',
			width : '115'
		}, {
			field : 'equpTypeName',
			title : '设备分类',
			width : '70'
		}, {
			field : 'brand',
			title : '品牌',
			width : '70'
		}, {
			field : 'type',
			title : '型号',
			width : '70'
		}, {
			field : 'measureName',
			title : '单位',
			width : '60'
		}, {
			field : 'typeName',
			title : '设备类型',
			align : 'center',
			width : '70'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
			var text = $("#comboEquipmentText_" + comboId).val();
			if ($.trim(text) != "") {
				grid.combogrid('setText', text);
				$("#comboEquipmentText_" + comboId).val("");
			}
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findEquipment(comboId) {
	var name = "comboEquipmentName_" + comboId;
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'name' : $('#' + name).val()
	});
}
/**
 * 查询设备购入信息
 * 
 * @param comboId
 * @param mult
 * @param width
 * @param type
 */
function combogridEquipRecord(comboId, mult, width, type, orgIds) {
	var tb = "<div class='datagrid-toolbar' style='width: 100%; height: 30px; display:none;' id='toolbar_"
			+ comboId
			+ "' ><table><tr><th style='padding:0 5px;'>设备名称:</th><td><input class='easyui-textbox' onkeyup='syncValue(this,\"comboEquipmentName_"
			+ comboId
			+ "\" )'></td><td><a href='javascript:void(0)' class='easyui-linkbutton c0' onclick=\"findEquipRecord('"
			+ comboId
			+ "')\" style=\"padding:0 3px;height:22px;\">查询</a></td></tr></table></div>";
	var tbb = $(tb);
	var grid = $('#' + comboId);
	grid.combogrid({
		panelWidth : width,
		panelHeight : 217,
		pagination : true,
		editable : false,
		idField : 'equipId',
		textField : 'equipName',
		rownumbers : true,
		multiple : mult,
		toolbar : tbb,
		pagination : true,
		pageSize : 10,
		pageList : [ 10 ],
		method : 'post',
		url : basePath + '/comm/getEquipRecordList.do?borrowable=' + type
				+ '&orgIds=' + orgIds,
		columns : [ [ {
			field : 'equipName',
			title : '设备名称',
			width : '100'
		}, {
			field : 'equpTypeName',
			title : '设备分类',
			width : '60'
		}, {
			field : 'brand',
			title : '品牌',
			width : '70'
		}, {
			field : 'type',
			title : '型号',
			width : '70'
		}, {
			field : 'orgName',
			title : '所属实验室',
			width : '85'
		}, {
			field : 'typeName',
			title : '设备类型',
			align : 'center',
			width : '70'
		} ] ],
		onLoadSuccess : function() {
			combogridPager(grid);
			var text = $("#comboEquipmentText_" + comboId).val();
			if ($.trim(text) != "") {
				grid.combogrid('setText', text);
				$("#comboEquipmentText_" + comboId).val("");
			}
		},
		onClickRow : function(index, row) {
			$("#comboEquipmentOrg_" + comboId).val(row.orgId);
		}
	});
}
/**
 * 重新加载combogrid的数据
 * 
 * @param comboId
 */
function findEquipRecord(comboId) {
	var name = "comboEquipmentName_" + comboId;
	$('#' + comboId).combogrid("grid").datagrid("reload", {
		'name' : $('#' + name).val()
	});
}
/**
 * 重置代码表下拉列表
 * 
 * @param id
 */
function resetCXSelect(id) {
	var data = $('#' + id).combobox('getData');
	if (data != null && data.length > 0) {
		$('#' + id).combobox('setValue', data[0].value);
	}
}
/**
 * 清除combogrid
 * 
 * @param id
 */
function combogridClear(id, measureId) {
	$("#" + id).textbox('setValue', '');
	$("#" + id).combogrid('grid').datagrid('clearSelections');
	if (measureId != undefined) {
		$("#" + measureId).val("");
	}
}
/**
 * 打开菜单选择画面
 */
function openMenuWindow() {
	var href = basePath + '/admin/menuListInit.do';
	parent.openWindow("菜单列表", href, 480, 359);
}

/*
 * 根据〖中华人民共和国国家标准 GB
 * 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * 地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。 出生日期码表示编码对象出生的年、月、日，其中年份用四位数字表示，年、月、日之间不用分隔符。
 * 顺序码表示同一地址码所标识的区域范围内，对同年、月、日出生的人员编定的顺序号。顺序码的奇数分给男性，偶数分给女性。
 * 校验码是根据前面十七位数字码，按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。
 * 
 * 出生日期计算方法。 15位的身份证编码首先把出生年扩展为4位，简单的就是增加一个19或18,这样就包含了所有1800-1999年出生的人;
 * 2000年后出生的肯定都是18位的了没有这个烦恼，至于1800年前出生的,那啥那时应该还没身份证号这个东东，⊙﹏⊙b汗... 下面是正则表达式:
 * 出生日期1800-2099 (18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01]) 身份证正则表达式
 * /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i
 * 15位校验规则 6位地址编码+6位出生日期+3位顺序号 18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
 * 
 * 校验位规则 公式:∑(ai×Wi)(mod 11)……………………………………(1) 公式(1)中：
 * i----表示号码字符从由至左包括校验码在内的位置序号； ai----表示第i位置上的号码字符值；
 * Wi----示第i位置上的加权因子，其数值依据公式Wi=2^(n-1）(mod 11)计算得出。 i 18 17 16 15 14 13 12 11 10
 * 9 8 7 6 5 4 3 2 1 Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
 * 
 */
// 身份证号合法性验证
// 支持15位和18位身份证号
// 支持地址编码、出生日期、校验位验证
function IdCardValid(code) {
	var city = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江 ",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北 ",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏 ",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外 "
	};
	var tip = "";
	var pass = true;

	if (!code
			|| !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i
					.test(code)) {
		tip = "身份证号格式错误";
		pass = false;
	}

	else if (!city[code.substr(0, 2)]) {
		tip = "地址编码错误";
		pass = false;
	} else {
		// 18位身份证需要验证最后一位校验位
		if (code.length == 18) {
			code = code.split('');
			// ∑(ai×Wi)(mod 11)
			// 加权因子
			var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
			// 校验位
			var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for (var i = 0; i < 17; i++) {
				ai = code[i];
				wi = factor[i];
				sum += ai * wi;
			}
			var last = parity[sum % 11];
			if (parity[sum % 11] != code[17]) {
				tip = "校验位错误";
				pass = false;
			}
		}
	}
	return pass;
}

/* 根据出生日期算出年龄 */
function getAge(strBirthday) {
	var returnAge;
	var strBirthdayArr = strBirthday.split("-");
	var birthYear = strBirthdayArr[0];
	var birthMonth = strBirthdayArr[1];
	var birthDay = strBirthdayArr[2];

	d = new Date();
	var nowYear = d.getFullYear();
	var nowMonth = d.getMonth() + 1;
	var nowDay = d.getDate();

	if (nowYear == birthYear) {
		returnAge = 0;// 同年 则为0岁
	} else {
		var ageDiff = nowYear - birthYear; // 年之差
		if (ageDiff > 0) {
			if (nowMonth == birthMonth) {
				var dayDiff = nowDay - birthDay;// 日之差
				if (dayDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			} else {
				var monthDiff = nowMonth - birthMonth;// 月之差
				if (monthDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			}
		} else {
			returnAge = -1;// 返回-1 表示出生日期输入错误 晚于今天
		}
	}
	return returnAge;// 返回周岁年龄
}
