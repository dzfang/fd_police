<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://cxtag.bm.com" prefix="cx"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档搜索</title>
<%@include file="../script.jsp"%>
<link rel="stylesheet" href="<%=contextPath%>/resources/css/search.css"
	type="text/css" />
<script>
	var basePath = "${pageContext.request.contextPath}";
	function doSearch(){
		var keywords = $('#keywords').val();
		if($.trim(keywords)!=""){
			$("#queryForm").submit();
		}
	}
</script>
</head>
<body link=#0000cc>
	<div id=out>
		<div id=in>
			<div id=wrapper>
				<div id=container class=container_s>
					<div id="m">
						<div id="fm" style="margin-top:15px;">
							<form id="queryForm"
								action="<%=contextPath%>/document/searchFile.do" method="post">
								<span class="s_ipt_wr"> <input name="keywords" id="keywords"
									value="${keywords}" maxlength="100" class="s_ipt" type="text">
								</span> <span class="s_btn_wr"> <input value="搜索一下" id="su"
									class="s_btn" type="button" onclick="doSearch();">
								</span>
							</form>
						</div>
					</div>
					<div id=content_left>
						<c:if test="${documentModelList.size()==0 }">未查询到任何结果！</c:if>
						<c:forEach var="document" items="${documentModelList}"
							varStatus="loop">
							<div class="result c-container " id="6">
								<h3 class="t">
									<a href="<%=contextPath%>/document/downloadFile.do?fileId=${document.fileId}&keywords=${keywords}"
										target="_self">${document.fileName }
									</a>
								</h3>
								<div class="c-abstract">
									${document.highlighting }
								</div>
								<div class="f13">
									<span class="c-icons-outer"><span class="c-icons-inner"></span></span><!-- &nbsp;-&nbsp;<a
										href=""	target="_blank" class="m">在线预览</a> -->&nbsp;-&nbsp;<a
										href="<%=contextPath%>/document/downloadFile.do?fileId=${document.fileId}&keywords=${keywords}"
										target="_self" class="m">下载</a>
								</div>
							</div>
							<br>
						</c:forEach>
					</div>
					<%-- <%
    // 查询开始
    SolrQuery params = new SolrQuery("text:(" + q + ")");
    params.set("start", startRow);
    params.set("rows", PAGE_ROWS);
    params.set("fl", "*,score");
    params.setHighlight(true); // 开启高亮组件
    params.addHighlightField("webTitle");// 高亮字段
    params.addHighlightField("webContent");// 高亮字段

    try {
        QueryResponse solrResponse = serverNews.query(params);

        // 处理查询结果开始...
        SolrDocumentList sdList = solrResponse.getResults();
        Map<String, Map<String, List<String>>> map = solrResponse.getHighlighting();
        long numfound = sdList.getNumFound(); // 查询返回多少条记录
        long elapsedtime = solrResponse.getElapsedTime(); //耗时
        
        // 下面是输出内容的代码
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        out.println("<div id=content_left>");
        for (int i = 0; i < sdList.size(); i++) {
            SolrDocument sdocument = sdList.get(i);
            
            // 下面这些参数是存储在Lucene索引中的值，在sdList中
            String objectId = (String)sdocument.get("objectId");
            String webHtml = (String)sdocument.get("webHtml");
            String webUrl = (String)sdocument.get("webUrl");
            String webTime = "";
            try {
                webTime = sdf.format(sdocument.get("webTime"));
            }catch (Exception e){
            }
            String webCommit = (String)sdocument.get("webCommit");
            String webUrlShort = webUrl;
            
            // 下面这两个字段有可能都包含关键字，也有可能只有一个包含
            String title = (String)sdocument.get("webTitle");
            String content = (String)sdocument.get("webContent");
            if(content.length() > 100) {
                content = content.substring(0,100) + "...";
            }

            Map<String, List<String>> valueMap = map.get(objectId);
            for (Map.Entry<String, List<String>> entry : valueMap.entrySet()) {
                String subkeyname = entry.getKey().toString();
                String subkeyvalue = entry.getValue().get(0);
                
                if("webTitle".equals(subkeyname)) {
                    title = subkeyvalue;
                }else if("webContent".equals(subkeyname)) {
                    // 高亮是摘要信息。默认值是100
                    content = subkeyvalue;
                }
            }
            
            out.println("<TABLE id=1 class=result cellSpacing=0 cellPadding=0>");
            out.println("<TBODY>");
            out.println("<TR>");
            out.println("<TD class=f>");
            out.println("<H3 class=t><A href=\"" + webUrl + "\" target=_blank data-click=\"{'F':'778317EA','F1':'9D33F1E4','F2':'CCA6DE6B','F3':'54E5243F','T':'1358404362','y':'8BBC7F73'}\">");
            out.println(title);
            out.println("</A></H3>");
            out.println("<FONT size=-1>");
            out.println(content);
            out.println("</FONT><BR><FONT size=-1></FONT><FONT size=-1>");
            out.println("<SPAN class=g>" + webUrlShort + " " + webTime + "-" + webCommit + "</SPAN>");
            out.println("<A class=m href=\"" + webHtml + "\" target=_blank data-nolog>精度快照</A><SPAN id=like_2663726754990454856 class=liketip data-nolog></SPAN>");
            out.println("</FONT></TD></TR></TBODY></TABLE><BR>");
        }
        out.println("</div>");
        
        // 下面是输出分页的代码
        
        // 总页数，起始页，实际分页值
        long totalPages = (long)(Math.ceil((double)numfound/(double)PAGE_ROWS));
        int[] pageParams = caluPages(curPage, totalPages);
        int startPage = pageParams[0];
        int pages = pageParams[1];
        System.out.println("总页数："+ totalPages + "，当前页：" + curPage + "，起始页：" + startPage + "，实际分页：" + pages + "，Solr开始记录：" + startRow);
        
        out.println("<P id=page>");
        // 如果当前页大于1，则显示上一页
        if(curPage > 1) {
            out.println("<A class=n href=\"" + url + "&pg=" + (curPage-1) + "\">&lt;上一页</A>");
        }
        if(totalPages >= 1) {
            for(int i=startPage; i<startPage+pages; i++) {
                if(i > totalPages) {
                    break;
                }
                if(curPage == i) {
                    out.println("<STRONG><SPAN class=\"fk fk_cur\"></SPAN><SPAN class=pc>" + i + "</SPAN></STRONG>");
                }else {
                    out.println("<A href=\"" + url + "&pg=" + i + "\"><SPAN class=\"fk fkd\"></SPAN><SPAN class=pc>" + i + "</SPAN></A>");
                }
            }
        }
        // 如果当前页小于总页数，则显示下一页（如果无结果，则当前页为1，总页数为0） 
        if(curPage < totalPages) {
            out.println("<A class=n href=\"" + url + "&pg=" + (curPage+1) + "\">下一页&gt;</A>");
        }
        out.println("<SPAN style=\"MARGIN-LEFT: 20px\" class=nums>精度为您找到相关结果约" + numfound + "个，耗时：" + elapsedtime + "毫秒</SPAN>");
        out.println("</P>");
        out.println("<BR/>");
        
    } catch (SolrServerException e) {
        e.printStackTrace();
    }
%> --%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>