<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地图</title>
<%@include file="../script.jsp"%>
<link href='http://121.42.165.238:9000/bigemap.js/v2.1.0/bigemap.css'
	rel='stylesheet' />
<script type="text/javascript"
	src="http://121.42.165.238:9000/bigemap.js/v2.1.0/bigemap.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/resources/js/jsps/map.js"></script>

<link rel="stylesheet"
	href="<%=contextPath%>/resources/mouseDraw/Bigemap.draw.css" />
<script src="<%=contextPath%>/resources/mouseDraw/Bigemap.draw.js"></script>
<script src="<%=contextPath%>/resources/mouseDraw/Bigemap.Draw.Event.js"></script>

<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/Edit.Poly.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/Edit.SimpleShape.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/Edit.Rectangle.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/Edit.Marker.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/Edit.CircleMarker.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/Edit.Circle.js"></script>

<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.Feature.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.Polyline.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.Polygon.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.SimpleShape.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.Rectangle.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.Circle.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.Marker.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/draw/handler/Draw.CircleMarker.js"></script>

<script src="<%=contextPath%>/resources/mouseDraw/ext/TouchEvents.js"></script>
<script src="<%=contextPath%>/resources/mouseDraw/ext/LatLngUtil.js"></script>
<script src="<%=contextPath%>/resources/mouseDraw/ext/GeometryUtil.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/ext/LineUtil.Intersect.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/ext/Polyline.Intersect.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/ext/Polygon.Intersect.js"></script>

<script src="<%=contextPath%>/resources/mouseDraw/Control.Draw.js"></script>
<script src="<%=contextPath%>/resources/mouseDraw/Tooltip.js"></script>
<script src="<%=contextPath%>/resources/mouseDraw/Toolbar.js"></script>

<script src="<%=contextPath%>/resources/mouseDraw/draw/DrawToolbar.js"></script>
<script src="<%=contextPath%>/resources/mouseDraw/edit/EditToolbar.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/EditToolbar.Edit.js"></script>
<script
	src="<%=contextPath%>/resources/mouseDraw/edit/handler/EditToolbar.Delete.js"></script>
<script>
	var basePath = "${pageContext.request.contextPath}";
</script>
<style>
body {
	margin: 0;
	padding: 0;
}

#map {
	position: absolute;
	top: 0;
	bottom: 0;
	width: 100%;
}

#position {
	background-color: #eee;
	padding: 5px;
	position: absolute;
	top: 0;
	right: 20px;
	z-index: 9;
}

.search {
	position: absolute;
	top: 10px;
	left: 60px;
	padding: 8px;
	background-color: #fff;
	border: 1px solid #bfbfbf;
}

.result {
	position: absolute;
	width: 316px;
	top: 70px;
	left: 60px;
	padding: 10px;
	background-color: #fff;
	border: 1px solid #bfbfbf;
	display: none;
	overflow-y:scroll;
}

.col-l {
	float: left;
}

.n-blue {
	color: #3385ff !important;
}

.n-grey {
	color: #666 !important
}

.addr {
	display: block;
	line-height: 25px;
	height: 25px;
	text-indent: 0;
	overflow:hidden;
}
.l-btn-selected{ color:#3385ff}
.ml_30 {
	margin-left: 30px !important
}

.cf {
	padding: 10px;
	border-bottom: 1px solid #eee;
}
.pagination{background-color:#fff}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div class="" region="center" style="padding: 5px;" border="false">
			<div id='map'></div>

			<div class="search" id="divBox">
				<input class="easyui-searchbox" id="searchbox"
					data-options="searcher:doSearch"
					style="width: 310px; height: 25px;"></input>
				<input type="hidden" id="keyword"/> 
			</div>

			<div class="result" id="sr">
				 <div id="result"></div>
				 <div class="easyui-pagination" id="pagination" style="padding-top:5px;"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		mapInit();
	</script>
</body>
</html>
