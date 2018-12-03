var dataTran;
var tableIns;
var primaryKey="";
var tabSchema="";
var pkMap=new Object();
var tabSchemaMap=new Object();
var orderByMap=new Object();
var columnNames;
var sfkxgMap=new Object();//是否可修改
var sfkxzMap=new Object();//是否可新增
var sfktbMap=new Object();//是否可同步
var excelData;//导入的数据
var tableData;//原先表中的数据
var hxData;//同步时从核心查到的所有数据
var importAddData=new Array();//导入时模板中的我们库里没有的数据或同步时核心表中查出的我们库里没有的数据
var existDataMap=new Object();
$(function () {
	String.prototype.endWith = function(str) {
		var reg = new RegExp(str + "$");
		return reg.test(this);
	}
	layui.use('upload', function(){
		var upload = layui.upload;
		 excelData=null;
		 upload.render({
			    elem: '#uploadExcel'
			    ,url: './uploadExcel'
			    ,accept: 'file' //普通文件
			    ,before: function(obj){
                    this.data={
                            'tableName': $("#t_name").val(),
                            'primaryKey':primaryKey,
                            'tabSchema':tabSchema
                        };
                } 
			    ,done: function(data){
		        	existDataMap=new Object();
		        	if(data.code=="0"){
			        	importAddData=new Array();
			            	var existNum=0;//表中已存在的数据数量
			            	var differentNum=0;//不完全相同的数据数量
			            	var existPkValue="||";
			            	excelData=data.data;
							var pk=primaryKey;
							if(primaryKey=="UUID"||primaryKey=="uuid"||$("#t_name").val()=="dm_code_value"){
								pk="code";
							}
			            	 for (var i = 0; i < excelData.length; i++) {
			            		 var excelDataPk = excelData[i][pk];
			            		 for(var j = 0; j < tableData.length; j++){
			            			 var tableDataPk = tableData[j][pk];
			            			 if(tableDataPk==excelDataPk){
			            				 var wqxt=true;
			            				 for(var key in excelData[i]){
			            					 var tableDataValue=tableData[j][key];
			            					  if(excelData[i][key]!=tableDataValue){
			            						  wqxt=false;
			            					  }
			            				 }
			            				 if(!wqxt){
			            					 var existData =new Object();
		    	            				 existData[0]=tableData[j];
		    	            				 existData[1]=excelData[i];
		    	            				 existDataMap[differentNum]=existData;
		    	            				 differentNum++;
			            				 }
			            				 existNum++;
			            				 existPkValue+=excelDataPk+'||';
			            			 }
			            		 }
			            	 }
	
			            	 //新增的数据
			            	 for(var j = 0; j < excelData.length; j++){
			        			 var excelDataPk = excelData[j][pk];
			        			 if(existPkValue.indexOf('||'+excelDataPk+'||')==-1){
			        				 importAddData[importAddData.length]=excelData[j];
			        			 }
			        		 }
			            	 if(differentNum!=0){
			            		 layer.confirm("共导入"+excelData.length+"条数据，其中"+(excelData.length-existNum)+"条为新增数据，"+existNum+"条为已存在的数据，已存在数据中包含"+differentNum+"条有差异的数据，请选择您的操作。", {btn: ['全部覆盖', '比较差异数据', '取消'], title: "提示"}, 
			            		   function () {
			            			 for(var j = 0; j < differentNum; j++){
			    	        			importAddData[importAddData.length]=existDataMap[j][1];
			    	        		 }
			            			 $.ajax({
			             		        type: "POST",
			             		        url: "./insertDmList",
			             		        dataType: "json",
			             				data:{
			             					'jsonStr':JSON.stringify(importAddData),
			             					'tableName':$("#t_name").val(),
			                 	            'tabSchema':tabSchema,
			                 	            'primaryKey':primaryKey
			             				},
			             		        success: function (data) {
			             		        	if(data.code=="0"){
			             		        		layer.msg(data.msg);
			             		        		fillTable();
			             		        	}else{
			             		        		layer.msg(data.msg);
			             		        	}
			             		        }
			             		    });
			            		 },function () {
			            			 $("#sjbjTbody").html("");
			            			 var titleHtml="<tr><th style='width:10%'>全选<input type='checkbox' name='checkeAll' id='checkeAll' onclick='checkedAll()'/></th>";
			        				 var tabDataHtml="";
			            			 for(var i=0;i<differentNum;i++){
			            				 var tabData=existDataMap[i][0];
			            				 var newData=existDataMap[i][1];
			            				 var trHtml="";
			            				 var tdHtml="";
			            				 tdHtml+='<td>';
			            				 tdHtml+='<input type="checkbox" value="'+i+'" id="data'+i+'" name="checkedData"/>';
			            				 tdHtml+='</td>';
			            				 var wqxt=true;
			            				 for (var k = 0; k < columnNames.length; k++) {
			            					 if(columnNames[k].column_name=="uuid"||columnNames[k].column_name=="UUID"){
			            						 continue;
			            					 }
			            					 if(i==0){
			            						 titleHtml+="<th style='width:15%'>"+columnNames[k].column_comment+"</th>";
			            					 }
			            					 var tabDataValue=tabData[columnNames[k].column_name]!=null&&typeof(tabData[columnNames[k].column_name])!=undefined?tabData[columnNames[k].column_name]:"";
			            					 var excelDataValue=newData[columnNames[k].column_name]!=null&&typeof(newData[columnNames[k].column_name])!=undefined?newData[columnNames[k].column_name]:"";;
			            					 if(tabDataValue!=excelDataValue){
			            						 tdHtml+="<td style='color:red;'>【"+tabDataValue+"】→【"+excelDataValue+"】</td>";
		    	            					 wqxt=false;
			            					 }else{
			            						 tdHtml+="<td>"+excelDataValue+"</td>";
			            					 }
			            				 }
			            				 if(wqxt){
			            					 trHtml="<tr style='display:none'>"; 
			            				 }else{
			            					 trHtml="<tr class='different'>"; 
			            				 }
			            				 tabDataHtml+=trHtml+tdHtml+"</tr>";
			            			 }
			            			 titleHtml+="</tr>";
			            			 $("#sjbjTbody").html(titleHtml+tabDataHtml);
			            			 layer.open({
			            	                type: 1,
			            	                shadeClose: true,
			            	                shade: 0.8,
			            	                area: ['1000px', '550px'],
			            	                title:'请选择要覆盖的数据',
			            	                content: "<div id='tcc'>"+$('#sjbj').html()+"</div>" //放提示的div层类名
			            	        }); 
			            		 },function () {
			            			 layer.close(layer.index);
			            		 });
			            	 }else{
			            		 if(importAddData.length>0){
			            			 $.ajax({
				            		        type: "POST",
				            		        url: "./insertDmList",
				            		        dataType: "json",
				            				data:{
				            					'jsonStr':JSON.stringify(importAddData),
				            					'tableName':$("#t_name").val(),
				                	            'tabSchema':tabSchema,
				                	            'primaryKey':primaryKey
				            				},
				            		        success: function (data) {
				            		        	if(data.code=="0"){
				            		        		layer.msg(data.msg);
				            		        		fillTable();
				            		        	}else{
				            		        		layer.msg(data.msg);
				            		        	}
				            		        }
				            		    });
			            		 }else{
			            			 layer.msg("操作完成，模板中的数据在代码表中已存在，无需导入！");
			            		 }
			            		
			            	 }
			            }else{
			            	layer.msg(data.msg);
			            }
			    }
		});
	});
    fillSelectOptions();
    $("#search").click(function () {
        fillTable();
    });
/*    $("#uploadExcel").click(function(){
    	importExp();
    });*/
    $("#downloadExcel").click(function () {
    	createExcel();
    });
    $("#tb").click(function () {
    	$("#sjbjTbody").html("");
    	 $.ajax({
    	        type: "POST",
    	        url: "./dmbTb",
    	        dataType: "json",
    			data:{
    				'tableName':$("#t_name").val(),
    	            'tabSchema':tabSchema,
    	            'primaryKey':primaryKey
    			},
    	        success: function (data) {
    	        	if(data.code=="0"){
    	        		importAddData=new Array();
    	            	var existNum=0;//表中已存在的数据数量
    	            	var differentNum=0;//不完全相同的数据数量
    	            	var existPkValue="||";
    	            	hxData=data.data;
    	            	 for (var i = 0; i < hxData.length; i++) {
    	            		 var hxDataPk = hxData[i][primaryKey.toUpperCase()];
    	            		 for(var j = 0; j < tableData.length; j++){
    	            			 var tableDataPk = tableData[j][primaryKey];
    	            			 if(tableDataPk==hxDataPk){
    	            				 var wqxt=true;
    	            				 for(var key in hxData[i]){
    	            					 var tableDataValue=tableData[j][key]!=null?tableData[j][key]:tableData[j][key.toLowerCase()];
    	            					  if(hxData[i][key]!=tableDataValue){
    	            						  wqxt=false;
    	            					  }
    	            				 }
    	            				 if(!wqxt){
    	            					 var existData =new Object();
        	            				 existData[0]=tableData[j];
        	            				 existData[1]=hxData[i];
        	            				 existDataMap[differentNum]=existData;
        	            				 differentNum++;
    	            				 }
    	            				 existNum++;
    	            				 existPkValue+=hxDataPk+'||';
    	            			 }
    	            		 }
    	            	 }
    	            	 //新增的数据
    	            	 for(var j = 0; j < hxData.length; j++){
    	        			 var hxDataPk = hxData[j][primaryKey.toUpperCase()];
    	        			 if(existPkValue.indexOf('||'+hxDataPk+'||')==-1){
    	        				 importAddData[importAddData.length]=hxData[j];
    	        			 }
    	        		 }
    	            	 if(differentNum!=0){
    	            		 layer.confirm("共从核心查询库中获取到"+hxData.length+"条数据，其中"+(hxData.length-existNum)+"条为新增数据，"+existNum+"条为已存在的数据，已存在数据中包含"+differentNum+"条有差异的数据，请选择您的操作。", {btn: ['全部覆盖', '比较差异数据', '取消'], title: "提示"}, 
    	            		   function () {
    	            			 for(var j = 0; j < differentNum; j++){
    	    	        			importAddData[importAddData.length]=existDataMap[j][1];
    	    	        		 }
    	            			 $.ajax({
    	             		        type: "POST",
    	             		        url: "./insertDmList",
    	             		        dataType: "json",
    	             				data:{
    	             					'jsonStr':JSON.stringify(importAddData),
    	             					'tableName':$("#t_name").val(),
    	                 	            'tabSchema':tabSchema,
    	                 	            'primaryKey':primaryKey
    	             				},
    	             		        success: function (data) {
    	             		        	if(data.code=="0"){
    	             		        		layer.msg(data.msg);
    	             		        		fillTable();
    	             		        	}else{
    	             		        		layer.msg(data.msg);
    	             		        	}
    	             		        }
    	             		    });
    	            		 },function () {
    	            			 $("#sjbjTbody").html("");
    	            			 var titleHtml="<tr><th style='width:10%'>全选<input type='checkbox' name='checkeAll' id='checkeAll' onclick='checkedAll()'/></th>";
    	        				 var tabDataHtml="";
    	            			 for(var i=0;i<differentNum;i++){
    	            				 var tabData=existDataMap[i][0];
    	            				 var newData=existDataMap[i][1];
    	            				 var trHtml="";
    	            				 var tdHtml="";
    	            				 tdHtml+='<td>';
    	            				 tdHtml+='<input type="checkbox" value="'+i+'" id="data'+i+'" name="checkedData"/>';
    	            				 tdHtml+='</td>';
    	            				 var wqxt=true;
    	            				 for (var k = 0; k < columnNames.length; k++) {
    	            					 if(columnNames[k].column_name=="uuid"||columnNames[k].column_name=="UUID"){
    	            						 continue;
    	            					 }
    	            					 if(i==0){
    	            						 titleHtml+="<th style='width:15%'>"+columnNames[k].column_comment+"</th>";
    	            					 }
    	            					 var tabDataValue=tabData[columnNames[k].column_name]!=null&&typeof(tabData[columnNames[k].column_name])!=undefined?tabData[columnNames[k].column_name]:"";
    	            					 var hxDataValue=newData[columnNames[k].column_name.toUpperCase()]!=null&&typeof(newData[columnNames[k].column_name.toUpperCase()])!=undefined?newData[columnNames[k].column_name.toUpperCase()]:"";;
    	            					 if(tabDataValue!=hxDataValue){
    	            						 tdHtml+="<td style='color:red;'>【"+tabDataValue+"】→【"+hxDataValue+"】</td>";
        	            					 wqxt=false;
    	            					 }else{
    	            						 tdHtml+="<td>"+hxDataValue+"</td>";
    	            					 }
    	            				 }
    	            				 if(wqxt){
    	            					 trHtml="<tr style='display:none'>"; 
    	            				 }else{
    	            					 trHtml="<tr class='different'>"; 
    	            				 }
    	            				 tabDataHtml+=trHtml+tdHtml+"</tr>";
    	            			 }
    	            			 titleHtml+="</tr>";
    	            			 $("#sjbjTbody").html(titleHtml+tabDataHtml);
    	            			 layer.open({
    	            	                type: 1,
    	            	                shadeClose: true,
    	            	                shade: 0.8,
    	            	                area: ['1000px', '550px'],
    	            	                title:'请选择要覆盖的数据',
    	            	                content: "<div id='tcc'>"+$('#sjbj').html()+"</div>" //放提示的div层类名
    	            	        }); 
    	            		 },function () {
    	            			 layer.close(layer.index);
    	            		 });
    	            	 }else{
    	            		 if(importAddData.length>0){
	    	            		 $.ajax({
	    	            		        type: "POST",
	    	            		        url: "./insertDmList",
	    	            		        dataType: "json",
	    	            				data:{
	    	            					'jsonStr':JSON.stringify(importAddData),
	    	            					'tableName':$("#t_name").val(),
	    	                	            'tabSchema':tabSchema,
	    	                	            'primaryKey':primaryKey
	    	            				},
	    	            		        success: function (data) {
	    	            		        	if(data.code=="0"){
	    	            		        		layer.msg(data.msg);
	    	            		        		fillTable();
	    	            		        	}else{
	    	            		        		layer.msg(data.msg);
	    	            		        	}
	    	            		        }
	    	            		    });
    	            		 }else{
    	            			 layer.msg("操作完成，核心查询库中数据与表中数据完全一致，无需同步！");
    	            		 }
    	            	 }
    	            }else{
    	            	layer.msg(data.msg);
    	            }
    	        }
    	    });
    });
    $("#add").click(function () {
    	dataTran=new Object();
    	dataTran.tableName = $("#t_name").val();
    	layer.open({
            type: 2,
            title: '新增代码',
            area: ['680px', '468px'],
            shade: 0.2,
            maxmin: false,
            content: './dmb_add'
        });
    });
});

var cols=new Array();
function fillTable() {
	var tableName=$("#t_name").val();
	$("#excel_file").val("");
	primaryKey=pkMap[tableName];
	tabSchema=tabSchemaMap[tableName];
	var sfkxg=sfkxgMap[tableName];
	var sfkxz=sfkxzMap[tableName];
	var sfktb=sfktbMap[tableName];
	if(sfkxz=="Y"){
		$("#add").show();
	}else{
		$("#add").hide();
	}
	if(sfktb=="Y"){
		$("#tbDiv").show();
	}else{
		$("#tbDiv").hide();
	}
	cols[0]=new Array();
	cols[0][0]={title: '序号',
            type: 'numbers',width:'5%'};
	$.ajax({
        type: "POST",
        url: "./getDmbColumnNames",
        dataType: "json",
		data:{
            'tableName':tableName,
            'tabSchema':tabSchema
		},
        success: function (data) {
        	columnNames=new Array();
        	var width=10;
        	if(primaryKey!=""&&(primaryKey.indexOf("id")>0||(primaryKey.indexOf("ID")>0))){
        		if(data.length<=10){
            		width=85/(data.length-1);
            	}
        	}else{
        		if(data.length<=9){
            		width=85/data.length;
            	}
        	}
        	var j=0;
        	for (var i = 0; i < data.length; i++) {
                var item = data[i];
                if (item.column_comment == "") {
                    item.column_comment = item.column_name;
                }
                if(primaryKey==""){
                	if(item.column_name.endWith("_dm")||item.column_name.endWith("_DM")){
                		primaryKey=item.column_name;
                	}
                }if(primaryKey==item.column_name&&(primaryKey.indexOf("id")>0||(primaryKey.indexOf("ID")>0))){
                }else{
                	cols[0][j+1]={title: item.column_comment,field: item.column_name,width:width+'%'};  
                	j++;
                }
                
            }
        	columnNames=data;
        	cols[0][cols[0].length]={
                    title: '操作',
                    align: "center",
                    width:"10%"
                	,templet: function(d){
            			if(sfkxg=="Y"){
            				return '<div><button class="layui-btn layui-btn-sm layui-btn-xs" lay-event="edit">修改</button>&nbsp;&nbsp;<button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button></div>';
            			}else{
            				return '<div><button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button></div>';
            			}
                	}};
        	tableIns = table.render({
                elem: '#table',
                height: $(window).height() - 162,
                url: './dmbData',
                where: {
                    'tableName': $("#t_name").val(),
                    'key': encodeURIComponent($("#key").val()),
                    'primaryKey':primaryKey,
                    'tabSchema':tabSchema
                },
                cols: cols,
                done: function (res, curr, count) {
                	tableData=res.data;
                    $(".layui-table-box").css("width","100%");
                }
            });
		}
		
    });

   
    table.on('tool(filter)', function (obj) {
        dataTran = obj.data;
        dataTran.tableName = $("#t_name").val();
        if ('edit' == obj.event) {
        		layer.open({
                    type: 2,
                    title: '编辑代码表',
                    area: ['680px', '468px'],
                    shade: 0.2,
                    maxmin: false,
                    content: './dmb_edit'
                });
        }
        if ('delete' == obj.event) {
        	layer.confirm("是否要删除本代码？", {btn: ['确定', '取消'], title: "提示"}, function () {
        		layer.close(layer.index);
        		$.ajax({
        	        type: "POST",
        	        url: "./deleteDm",
        	        dataType: "json",
        			data:{
        				'primaryKey':primaryKey,
        				'value':dataTran[primaryKey],
        	            'tableName':dataTran.tableName,
        	            'tabSchema':tabSchema,
        	            'jsonStr':JSON.stringify(dataTran)
        			},
        	        success: function (data) {
        	        	fillTable();
        	            if(data.code != '0'){
        	            	layer.msg(data.msg);
        				}
        	        }
        	    });
       	 });
        }
        /*
         * var data = obj.data; if (obj.event === 'setValue') {
         * layer.prompt({ formType : 2, title : '修改', value : data.value },
         * function(value, index) { layer.close(index); // 这里一般是发送修改的Ajax请求 //
         * 同步更新表格和缓存对应的值 obj.update({ value : value }); }); }
         */
    });
}
function fillSelectOptions() {
    $("#t_name").empty();
    $.ajax({
        type: "GET",
        url: "./getDmbTablesInfo",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                if (item.table_comment == "") {
                    item.table_comment = item.table_name;
                }
                tabSchemaMap[item.table_name]=item.tabSchema;
                pkMap[item.table_name]=item.pk;
                sfkxgMap[item.table_name]=item.sfkxg;
                sfkxzMap[item.table_name]=item.sfkxz;
                sfktbMap[item.table_name]=item.sfktb;
                $("#t_name").append("<option value='" + item.table_name + "'>" + item.table_comment + " (" + item.table_name + ")</option>");
            }
            form.on('select(changeTable)', function(obj){
            	$("#key").val("");
            	
            	fillTable();
            });
            form.render();
            fillTable();
        }
    });
}
function reloadTable() {
    tableIns.reload();
}
function createExcel(){
	var form = $("<form>");
	form.attr("style","display:none");
	form.attr("target","");
	form.attr("method","post");
	form.attr("action","./generateExcel");
	var input1 = $("<input>");
	input1.attr("type","hidden");
	input1.attr("name","tableName");
	input1.attr("id","tableName");
	input1.attr("value",$("#t_name").val());
	var input2 = $("<input>");
	input2.attr("type","hidden");
	input2.attr("name","tabSchema");
	input2.attr("id","tabSchema");
	input2.attr("value",tabSchema);
	$("body").append(form);
	form.append(input1);
	form.append(input2);
	form.submit();
	form.remove();

}

//导入文件
function importExp() {
	excelData=null;
    var formData = new FormData();
    var name = $("#excel_file").val();
    if(name==null||name==""){
    	layer.msg("请先上传文件！");
    	return;
    }
    formData.append("file",$("#excel_file")[0].files[0]);
    formData.append("name",name);
    formData.append("tabSchema",tabSchema);
    formData.append("tableName",$("#t_name").val());
    formData.append("primaryKey",primaryKey);
    $.ajax({
        url : './uploadExcel',
        type : 'POST',
        async : false,
        data : formData,
        processData : false,
        contentType : false,
        beforeSend:function(){
            console.log("正在导入，请稍候");
        },
        success : function(data) {
        	existDataMap=new Object();
        	if(data.code=="0"){
	        	importAddData=new Array();
	            	var existNum=0;//表中已存在的数据数量
	            	var differentNum=0;//不完全相同的数据数量
	            	var existPkValue="||";
	            	excelData=data.data;
					var pk=primaryKey;
					if(primaryKey=="UUID"||primaryKey=="uuid"||$("#t_name").val()=="dm_code_value"){
						pk="code";
					}
	            	 for (var i = 0; i < excelData.length; i++) {
	            		 var excelDataPk = excelData[i][pk];
	            		 for(var j = 0; j < tableData.length; j++){
	            			 var tableDataPk = tableData[j][pk];
	            			 if(tableDataPk==excelDataPk){
	            				 var wqxt=true;
	            				 for(var key in excelData[i]){
	            					 var tableDataValue=tableData[j][key];
	            					  if(excelData[i][key]!=tableDataValue){
	            						  wqxt=false;
	            					  }
	            				 }
	            				 if(!wqxt){
	            					 var existData =new Object();
    	            				 existData[0]=tableData[j];
    	            				 existData[1]=excelData[i];
    	            				 existDataMap[differentNum]=existData;
    	            				 differentNum++;
	            				 }
	            				 existNum++;
	            				 existPkValue+=excelDataPk+'||';
	            			 }
	            		 }
	            	 }
	            	 //新增的数据
	            	 for(var j = 0; j < excelData.length; j++){
	        			 var excelDataPk = excelData[j][pk];
	        			 if(existPkValue.indexOf('||'+excelDataPk+'||')==-1){
	        				 importAddData[importAddData.length]=excelData[j];
	        			 }
	        		 }
	            	 if(differentNum!=0){
	            		 layer.confirm("共导入"+excelData.length+"条数据，其中"+(excelData.length-existNum)+"条为新增数据，"+existNum+"条为已存在的数据，已存在数据中包含"+differentNum+"条有差异的数据，请选择您的操作。", {btn: ['全部覆盖', '比较差异数据', '取消'], title: "提示"}, 
	            		   function () {
	            			 for(var j = 0; j < differentNum; j++){
	    	        			importAddData[importAddData.length]=existDataMap[j][1];
	    	        		 }
	            			 $.ajax({
	             		        type: "POST",
	             		        url: "./insertDmList",
	             		        dataType: "json",
	             				data:{
	             					'jsonStr':JSON.stringify(importAddData),
	             					'tableName':$("#t_name").val(),
	                 	            'tabSchema':tabSchema,
	                 	            'primaryKey':primaryKey
	             				},
	             		        success: function (data) {
	             		        	if(data.code=="0"){
	             		        		layer.msg(data.msg);
	             		        		fillTable();
	             		        	}else{
	             		        		layer.msg(data.msg);
	             		        	}
	             		        }
	             		    });
	            		 },function () {
	            			 $("#sjbjTbody").html("");
	            			 var titleHtml="<tr><th style='width:10%'>全选<input type='checkbox' name='checkeAll' id='checkeAll' onclick='checkedAll()'/></th>";
	        				 var tabDataHtml="";
	            			 for(var i=0;i<differentNum;i++){
	            				 var tabData=existDataMap[i][0];
	            				 var newData=existDataMap[i][1];
	            				 var trHtml="";
	            				 var tdHtml="";
	            				 tdHtml+='<td>';
	            				 tdHtml+='<input type="checkbox" value="'+i+'" id="data'+i+'" name="checkedData"/>';
	            				 tdHtml+='</td>';
	            				 var wqxt=true;
	            				 for (var k = 0; k < columnNames.length; k++) {
	            					 if(columnNames[k].column_name=="uuid"||columnNames[k].column_name=="UUID"){
	            						 continue;
	            					 }
	            					 if(i==0){
	            						 titleHtml+="<th style='width:15%'>"+columnNames[k].column_comment+"</th>";
	            					 }
	            					 var tabDataValue=tabData[columnNames[k].column_name]!=null&&typeof(tabData[columnNames[k].column_name])!=undefined?tabData[columnNames[k].column_name]:"";
	            					 var excelDataValue=newData[columnNames[k].column_name.toUpperCase()]!=null&&typeof(newData[columnNames[k].column_name.toUpperCase()])!=undefined?newData[columnNames[k].column_name.toUpperCase()]:"";;
	            					 if(tabDataValue!=excelDataValue){
	            						 tdHtml+="<td style='color:red;'>【"+tabDataValue+"】→【"+excelDataValue+"】</td>";
    	            					 wqxt=false;
	            					 }else{
	            						 tdHtml+="<td>"+excelDataValue+"</td>";
	            					 }
	            				 }
	            				 if(wqxt){
	            					 trHtml="<tr style='display:none'>"; 
	            				 }else{
	            					 trHtml="<tr class='different'>"; 
	            				 }
	            				 tabDataHtml+=trHtml+tdHtml+"</tr>";
	            			 }
	            			 titleHtml+="</tr>";
	            			 $("#sjbjTbody").html(titleHtml+tabDataHtml);
	            			 layer.open({
	            	                type: 1,
	            	                shadeClose: true,
	            	                shade: 0.8,
	            	                area: ['1000px', '550px'],
	            	                title:'请选择要覆盖的数据',
	            	                content: "<div id='tcc'>"+$('#sjbj').html()+"</div>" //放提示的div层类名
	            	        }); 
	            		 },function () {
	            			 layer.close(layer.index);
	            		 });
	            	 }else{
	            		 $.ajax({
	            		        type: "POST",
	            		        url: "./insertDmList",
	            		        dataType: "json",
	            				data:{
	            					'jsonStr':JSON.stringify(importAddData),
	            					'tableName':$("#t_name").val(),
	                	            'tabSchema':tabSchema,
	                	            'primaryKey':primaryKey
	            				},
	            		        success: function (data) {
	            		        	if(data.code=="0"){
	            		        		layer.msg(data.msg);
	            		        		fillTable();
	            		        	}else{
	            		        		layer.msg(data.msg);
	            		        	}
	            		        }
	            		    });
	            	 }
	            }else{
	            	layer.msg(data.msg);
	            }
        }
    });
}
function fg(){
	if($('#tcc .different input[name=checkedData]:checked').length>0){
		$.each($('#tcc .different input[name=checkedData]:checked'),function(){
		       var value=$(this).val();
		       importAddData[importAddData.length]=existDataMap[value][1];
		});
			$.ajax({
		        type: "POST",
		        url: "./insertDmList",
		        dataType: "json",
				data:{
					'jsonStr':JSON.stringify(importAddData),
					'tableName':$("#t_name").val(),
		            'tabSchema':tabSchema,
		            'primaryKey':primaryKey
				},
		        success: function (data) {
		        	if(data.code=="0"){
		        		layer.close(layer.index);
		        		layer.msg(data.msg);
		        		fillTable();
		        	}else{
		        		layer.msg(data.msg);
		        	}
		        }
		    });
	}else{
			if(importAddData.length>0){
				$.ajax({
			        type: "POST",
			        url: "./insertDmList",
			        dataType: "json",
					data:{
						'jsonStr':JSON.stringify(importAddData),
						'tableName':$("#t_name").val(),
			            'tabSchema':tabSchema,
			            'primaryKey':primaryKey
					},
			        success: function (data) {
			        	if(data.code=="0"){
			        		layer.close(layer.index);
			        		layer.msg(data.msg);
			        		fillTable();
			        	}else{
			        		layer.msg(data.msg);
			        	}
			        }
			    });
			}else{
        		layer.close(layer.index);
				layer.msg("没有新增任何数据！");
			}
	}
	
    
}
function qx(){
	layer.close(layer.index);
}
function checkedAll(){
	if($('input[name=checkeAll]:checked').length>0){
		$.each($('#tcc .different input[name=checkedData][type=checkbox]'),function(){
			$(this).attr("checked",true);
		 });
	}else{
		$.each($('#tcc .different input[name=checkedData][type=checkbox]'),function(){
			$(this).removeAttr("checked");
		 });
	}
}