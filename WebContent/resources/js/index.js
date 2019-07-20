$(function() {
	// 关闭tab页签
	tabClose();
	// 绑定页签关闭事件
	tabCloseEvent();
	// 初始化左侧菜单
	InitLeftMenu();
});

/**
 * 初始化左侧菜单
 */
function InitLeftMenu() {
	hoverMenuItem();
	$('#wnav li a').bind('click', function() {
		var tabTitle = $(this).attr("title");
		if(tabTitle==undefined){
			return;
		}
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		addTab(tabTitle, url);
		$('#wnav li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});
}

/**
 * 菜单项鼠标Hover
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

/**
 * 添加tab页签
 * 
 * @param subtitle
 * @param url
 */
function addTab(subtitle, url) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}
/**
 * 添加iframe
 * 
 * @param url
 * @returns {String}
 */
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}
/**
 * 关闭tab页签
 */
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
		$('#tabs').tabs('resize');
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
/**
 * 绑定右键菜单事件
 */
function tabCloseEvent() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if (url == undefined) {
			url = "default.do";
		}
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
		$('#tabs').tabs('resize');
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != "欢迎使用") {
				$('#tabs').tabs('close', t);
			}
		});
		$('#tabs').tabs('resize');
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		$('#tabs').tabs('resize');
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			if (t != "欢迎使用") {
				$('#tabs').tabs('close', t);
			}
		});
		$('#tabs').tabs('resize');
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}
/**
 * 退出系统
 */
function logout() {
	$.messager.confirm("提示", "您确定要退出系统？", function(r) {
		if (r) {
			document.location.href = basePath + '/logout.do';
		}
	});
}
