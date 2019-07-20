
/**
 * 网站信息保存画面初始化
 */
function webSiteSaveInit() {
	// 添加网站信息
	$("#btnSave").bind("click", saveWebSite);
}
/**
 * 保存网站信息
 */
function saveWebSite() {
	if (!$("#saveForm").form("validate")) {
		return;
	}
	
	$.ajax({
		type : "POST",
		url : basePath + '/webSite/saveWebSite.do',
		data : $("#saveForm").serialize(),

		success : function(data) {
			if (data != null) {
				$.messager.alert("提示信息", message.byId(data), "info");
			}
		},
		error : doError,
		beforeSend : onBeforeSend,
		complete : onComplete
	});
}