/**
 * 文件名:sample.js
 * 描述: 用于展示各种jsp、js控件和标签以及共通机能的用法
 * 画面名:样例画面
 * 
 * @author  li_changjiang 2014/09/13
 * @version 1.0
 * @since   1.0
 */
$(function(){
	$('#startDate').datebox({
		onSelect: function(date){
			var v = $('#startDate').datebox('getValue');
			$("#age").val(getAge(v));
		}
	});
	$("#submitButton").click(function(){
		setPageTitle("测试页面的title");
		var isValidate = $('#queryForm').form('validate');//验证表单			
		if(!isValidate)//验证成功
		{
			$.messager.alert("提示",message.E_00015,"warning");
			return false;
		}
		else{
			$.messager.alert("提示","校验成功","info");
		}
	});
	$("#reportButton").click(function(){
		$("#reportForm").submit();
	});
	
	$("#exportButton").click(function(){
		
		$("#jsonData").val(JSON.stringify({
			cJDate:0,
			text:'test',
        }));
		
		$("#exportForm").submit();
	});
	
	$("#importButton").click(function(){
		var files = $("[name='files']");
		var filename = $(files[0]).val();
		 if(!filename){
			 $.messager.alert("提示",message.byId('I_00104',['要导入的文件']),"warning");
			 return false;
		 }
		 if(!/\.*[.]xls/.test(filename.toLowerCase())){
			 $.messager.alert("提示", message.byId('I_00105', ["产品导入文件", " xls "]), "warning");
			 return false;
		 }
		 
		 $("#importForm").ajaxSubmit({
			type : 'post',
			url : 'importFile.do',
			data : {text1:$("#text1").val(),text2:$("#text2").val()},
			success : function(data) {
				Message.show(data);
			},
			error:function(data) {
				Message.show($.parseJSON(data.responseText));
			}
		 });
	});
	
});

//@ sourceURL=sample.js