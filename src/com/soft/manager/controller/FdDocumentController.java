package com.soft.manager.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.soft.common.domain.DocumentModel;
import com.soft.common.domain.FdDocument;
import com.soft.manager.service.FdDocumentService;
import com.soft.util.Config;

@Controller
@RequestMapping("document")
public class FdDocumentController extends BaseController {
	@Resource
	private FdDocumentService documentService;

	/**
	 * 
	 * documentList(文档列表页面初始化)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("documentList.do")
	public String documentList() {
		return "document/documentList";
	}

	/**
	 * 
	 * getEmployeeList(查询文档信息列表)
	 * 
	 * @param record
	 *            FdDocument对象 @param pageIndex 当前页码 @param pageSize
	 *            每页数据笔数 @return Map
	 *            <String,Object> 返回一个Map对象，包含数据笔数及文档信息列表 @exception
	 */
	@RequestMapping("getDocumentList.do")
	public @ResponseBody Map<String, Object> getDocumentList(FdDocument record, int pageIndex, int pageSize) {
		return documentService.getDocumentList(record, pageIndex, pageSize);
	}

	/**
	 * 
	 * uploadInit(文档上传页面初始化)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("documentUpload.do")
	public String documentUpload() {
		return "document/documentUpload";
	}

	/**
	 * 
	 * uploadFile(文件上传) @param file 文件 @param attr @return String @exception
	 */
	@RequestMapping("uploadFile.do")
	public String uploadFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
		try {
			System.out.println("fileName：" + file.getOriginalFilename());
			String filePath = request.getContextPath() + "/resources/document/";
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String fullFileName = filePath + file.getOriginalFilename();
			// String path = "E:/" + new Date().getTime() +
			// file.getOriginalFilename();
			File newFile = new File(fullFileName);
			// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
			file.transferTo(newFile);

			FdDocument document = new FdDocument();
			document.setFileName(file.getOriginalFilename());
			document.setFileSize(String.valueOf(file.getSize()));
			documentService.insertDocument(document);
			indexFilesSolrCell(fullFileName, String.valueOf(document.getId()));
		} catch (Exception ex) {

		}
		return "redirect:/document/documentUpload.do";
	}

	/**
	 * Method to index all types of files into Solr.
	 * 
	 * @param fileName
	 * @param solrId
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void indexFilesSolrCell(String fileName, String id) throws IOException, SolrServerException {
		HttpSolrClient solrServer = new HttpSolrClient.Builder(Config.getSorlUrl()).build();
		ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
		File file = new File(fileName);
		up.addFile(file, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		up.setParam("literal.file_name", file.getName());
		up.setParam("literal.file_path", file.getPath());
		up.setParam("literal.file_process", id);
		up.setParam("fmap.content", "file_text");
		up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
		solrServer.request(up);
	}

	/**
	 * 
	 * uploadInit(文档上传页面初始化)
	 * 
	 * @return String @exception
	 */
	@RequestMapping("documentSearch.do")
	public String documentSearch() {
		return "document/documentSearch";
	}

	/**
	 * @throws IOException
	 * @throws SolrServerException
	 * 
	 *             searchFile(全文检索) @param keywords 关键字 @return
	 *             String @exception
	 */
	@RequestMapping("searchFile.do")
	public ModelAndView searchFile(String keywords) throws SolrServerException, IOException {
		HttpSolrClient solrServer = new HttpSolrClient.Builder(Config.getSorlUrl()).build();
		SolrQuery query = new SolrQuery();
		// 设置高亮
		query.setQuery("file_keywords:" + keywords);
		query.setHighlight(true); // 开启高亮组件或用query.setParam("hl", "true");
		query.addHighlightField("file_name");// 高亮字段
		query.addHighlightField("file_text");// 高亮字段
		query.setHighlightSimplePre("<font color='red'>");// 标记，高亮关键字前缀
		query.setHighlightSimplePost("</font>");// 后缀
		// query.setHighlightSnippets(1);//结果分片数，默认为1
		// query.setHighlightFragsize(1000);//每个分片的最大长度，默认为100

		System.out.println(query);// 用于调试程序

		QueryResponse rsp = solrServer.query(query);
		SolrDocumentList results = rsp.getResults();
		NamedList<?> list = (NamedList<?>) rsp.getResponse().get("highlighting");
		Map<String, Map<String, List<String>>> documents = rsp.getHighlighting();
		SolrDocument solrDocumnet = null;
		List<DocumentModel> documentModelList = new ArrayList<DocumentModel>();
		for (int i = 0; i < results.size(); i++) {
			solrDocumnet = results.get(i);
			DocumentModel documentModel = new DocumentModel();
			documentModel.setId(solrDocumnet.get("id").toString());
			Map<String, List<String>> document = documents.get((String) documentModel.getId());
			if (document.get("file_name") != null) {
				documentModel.setFileName(document.get("file_name").get(0));
			} else {
				documentModel.setFileName(solrDocumnet.get("file_name").toString());
			}
			if (document.get("file_text") != null) {
				documentModel.setHighlighting(document.get("file_text").get(0));
			} else {
				documentModel.setHighlighting(solrDocumnet.get("file_text").toString());
			}
			documentModel.setFileId(solrDocumnet.get("file_process").toString());
			documentModelList.add(documentModel);
		}

		System.out.println(list);// 用于显示list中的值

		for (int i = 0; i < list.size(); i++) {
			System.out.println("id=" + list.getName(i) + "文档中高亮显示的字段：" + list.getVal(i));
		}
		ModelAndView mv = new ModelAndView("document/searchResult");
		mv.addObject("keywords", keywords);
		mv.addObject("documentModelList", documentModelList);
		return mv;
	}

	/**
	 * 
	 * downloadFile(文档下载)
	 * 
	 * @param response
	 * @param id
	 *            记录ID @return @throws Exception ModelAndView @exception
	 */
	@RequestMapping("downloadFile.do")
	public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response, String fileId,
			String keywords) throws Exception {
		FdDocument fdDocument = documentService.findDocumentById(Long.parseLong(fileId));
		if(fdDocument==null){
			PrintWriter out = response.getWriter();
			String script = "alert('文件不存在！'); window.location.href='searchFile.do?keywords=" + keywords + "'";
			out.write("<html>" + "<head><meta http-equiv='Content-Type' content='text/html;charset=utf-8'>"
					+ "<script type='text/javascript'>" + script + "</script>" + "</head></html>");
			return null;
		}
		String downLoadPath = request.getContextPath() + "/resources/document/" + fdDocument.getFileName();
		response.setContentType("text/html;charset=utf-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		try {
			File file = new File(downLoadPath);
			if (!file.exists()) {
				PrintWriter out = response.getWriter();

				String script = "alert('文件不存在！'); window.location.href='searchFile.do?keywords=" + keywords + "'";
				out.write("<html>" + "<head><meta http-equiv='Content-Type' content='text/html;charset=utf-8'>"
						+ "<script type='text/javascript'>" + script + "</script>" + "</head></html>");
				return null;
			}
			long fileLength = file.length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + URLEncoder.encode(file.getName(), "utf-8"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

}
