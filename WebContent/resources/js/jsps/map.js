var map = null;
var searchSuccess = false;
var markerArray = new Array();
function mapInit() {
	$("#sr").height($("#map").height() - 100);
	pagerInit();

	$("#divBox").mouseenter(function() {
		if (searchSuccess) {
			$("#sr").fadeTo(500, 1);
		}
	});
	// 鼠标移出
	$("#sr").mouseleave(function() {
		$(this).fadeOut();
	});

	$("#searchbox").bind("input propertychange", function() {
		alert(1)
	});
	
	BM.Config.HTTP_URL = 'http://121.42.165.238:9000';
	map = BM.map('map', 'bigemap.aic2vjuo', {
		center : [ 34.219947814941406, 108.74063873291016 ],
		zoom : 12,
		zoomControl : true
	});

	map.fitBounds([ [ 33.69595718383789, 107.65833282470703 ],
			[ 34.74394226074219, 109.82295227050781 ] ]);

	// var marker;
	// map.on('contextmenu', function(e) {
	// marker = BM.marker(e.latlng, {
	// draggable : true
	// }).addTo(this);
	// marker.bindTooltip("my tooltip text").openTooltip();
	// marker.on('move', function(e) {
	// document.getElementById('position').innerHTML = 'lat:'
	// + e.latlng.lat + ',lng:' + e.latlng.lng;
	// });
	// });

	// 创建一个图形覆盖物的集合来保存点线面
	var drawnItems = new BM.FeatureGroup();
	// 添加在地图上
	map.addLayer(drawnItems);
	// 为多边形设置一个标题
	BM.drawLocal.draw.toolbar.buttons.polygon = '添加一个多边形';
	// 实例化鼠标绘制的控件
	var drawControl = new BM.Control.Draw({
		position : 'topright',// 位置
		// 控制绘制的图形
		draw : {
			polyline : true,
			polygon : true,
			circle : true,
			marker : true
		},
	});

	map.addControl(drawControl);
	// 监听绘画完成事件
	map.on(BM.Draw.Event.CREATED, function(e) {
		var type = e.layerType, layer = e.layer;
		if (type === 'marker') {
			// 如果是标注，实现一个点击出现的提示
			layer.bindPopup('A popup!');
		}
		drawnItems.addLayer(layer);
	});
	// var marker = BM.marker([ 34.225988, 108.886062 ]).addTo(map);
	// BM.marker([ 34.197855, 108.916595 ]).addTo(map);
	// 创建一个标注，并设置坐标为当前地图的中心,详细API请参见
	// http://www.bigemap.com/offlinemaps/api/#marker
	// var marker=BM.marker(map.getCenter(),{draggable:true}).addTo(map);
	// 添加一个单击事件。事件列表请参见
	// ：http://www.bigemap.com/offlinemaps/api/#marker-dragstart
	// marker.on('move',function (e) {
	// document.getElementById('position').innerHTML='lat:'+e.latlng.lat+',lng:'+e.latlng.lng;
	// });
	// 添加一个单击事件。事件列表请参见
	// ：http://www.bigemap.com/offlinemaps/api/#marker-dragstart
	// marker.on('move',function (e) {
	// document.getElementById('position').innerHTML='lat:'+e.latlng.lat+',lng:'+e.latlng.lng;
	// });

	// 添加一个简单的提示 详细API请参见 http://www.bigemap.com/offlinemaps/api/#popup
	// marker.bindTooltip("my tooltip text").openTooltip();
}
function pagerInit() {
	var pager = $('#pagination');
	pager.pagination({
		// 每页显示的记录条数，默认为20
		pageSize : 10,
		layout : [ 'links' ],
		links : 7,
		onSelectPage : loadData
	});
}

function doSearch() {
	var pager = $('#pagination');
	var options = pager.data("pagination").options;
	var name = $('#searchbox').searchbox('getValue');
	if ($.trim(name) == "") {
		return;
	}
	$("#keyword").val($.trim(name));
	loadData(1, options.pageSize);
}
function loadData(pageIndex, pageSize) {
	// var name = $('#searchbox').searchbox('getValue');
	// if ($.trim(name) == "") {
	// return;
	// }
	var name = $("#keyword").val();
	searchSuccess = false;
	for (var k = 0; k < markerArray.length; k++) {
		markerArray[k].remove();
	}
	markerArray.length = 0;

	$.ajax({
		type : "POST",
		url : basePath + '/openlayers/queryPosition.do?pageIndex=' + pageIndex
				+ '&pageSize=' + pageSize,
		data : {
			'name' : name
		},
		beforeSend : onBeforeSend,
		success : function(data) {
			$('#searchbox').searchbox('setValue', name);
			searchSuccess = true;
			map.setZoom(16);
			$("#sr").fadeTo(500, 1);
			var html = generateHtml(data.rows);
			$("#result").html(html);
			var pager = $('#pagination');
			pager.pagination({
				pageNumber : pageIndex,
				total : data.total
			});
		},
		error : doError,
		complete : onComplete
	});

}
//
function generateHtml(rows) {
	var html = "";
	for (var i = 0; i < rows.length; i++) {
		html += '<div class="cf">';
		html += '	<div class="col-l">';
		html += '		<a href="javascript:void(0)" style="display:block;width:21px;height:30px;background-image:url(\''
				+ basePath
				+ '/resources/images/markers/mark'
				+ (i + 1)
				+ '.png' + '\')"></a>';
		html += '	</div>';
		html += '	<div class="ml_30 mr_90">';
		html += '		<div>';
		html += '			<span> <a href="javascript:void(0);" class="n-blue" onclick="goCenter(\''
				+ rows[i].location + '\')" >' + rows[i].name + '</a>';
		html += '			</span> <span> </span>';
		html += '		</div>';
		html += '		<div class="addr">';
		html += '			<span class="n-grey" title="' + rows[i].address + '">'
				+ rows[i].address + '</span>';
		html += '		</div>';
		html += '	</div>';
		html += '</div>';

		var location = rows[i].location;
		var lnglat = location.split(",")
		if (i == 0) {
			var latlng = BM.latLng(lnglat[1], lnglat[0]);
			map.setView(latlng);
		}
		var marker = BM.marker(
				[ lnglat[1], lnglat[0] ],
				{
					icon : BM.icon({
						iconUrl : basePath + '/resources/images/markers/mark'
								+ (i + 1) + '.png'
					})
				}).addTo(map);
		markerArray.push(marker);
	}
	return html;
}

function goCenter(location) {
	var lnglat = location.split(",")
	var latlng = BM.latLng(lnglat[1], lnglat[0]);
	map.setView(latlng);
}