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
$(function () {
    fillSelectOptions();
    $("#search").click(function () {
        fillTable();
    });
    $("#add").click(function () {
    	dataTran=new Object();
    	dataTran.tableName = $("#t_name").val();
    	layer.open({
            type: 2,
            title: '新增参数',
            area: ['680px', '468px'],
            shade: 0.2,
            maxmin: false,
            content: './csb_add'
        });
    });
});

var cols=new Array();
function fillTable() {
	var tableName=$("#t_name").val();
	primaryKey=pkMap[tableName];
	tabSchema=tabSchemaMap[tableName];
	var sfkxg=sfkxgMap[tableName];
	var sfkxz=sfkxzMap[tableName];
	if(sfkxz=="Y"){
		$("#add").show();
	}else{
		$("#add").hide();
	}
	cols[0]=new Array();
	cols[0][0]={title: '序号',
            type: 'numbers',width:'5%'};
	$.ajax({
        type: "POST",
        url: "./getCsbColumnNames",
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
                	if(item.column_name.endsWith("_cs")||item.column_name.endsWith("_CS")){
                		primaryKey=item.column_name;
                	}
                }
                var k=0;
                if(primaryKey==item.column_name&&(primaryKey.indexOf("id")>0||(primaryKey.indexOf("ID")>0))){
                	k=1;
                }else{
                	if(j==0){
                        cols[0][j+1]={title: item.column_comment,field: item.column_name,width:width+'%',templet: function(d){
                        	var colName;
                        	if(primaryKey.indexOf("id")>0||(primaryKey.indexOf("ID")>0)){
                            	colName=data[1].column_name;
                        	}else{
                            	colName=data[0].column_name;
                        	}
            				return '<div><u><a lay-event="edit">'+d[colName]+'</a></u></div>';
                    		}
                        };  
                	}else{
                        cols[0][j+1]={title: item.column_comment,field: item.column_name,width:width+'%'};  
                	}
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
                url: './csbData',
                where: {
                    'tableName': $("#t_name").val(),
                    'key': encodeURIComponent($("#key").val()),
                    'tabSchema':tabSchema,
                    'orderBy':orderByMap[tableName]
                },
                limit: 100000000,
                loading: true,
                cols: cols,
                done: function (res, curr, count) {
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
                    title: '编辑参数表',
                    area: ['680px', '468px'],
                    shade: 0.2,
                    maxmin: false,
                    content: './csb_edit'
                });
        }
        if ('delete' == obj.event) {
        	layer.confirm("是否要删除本参数？", {btn: ['确定', '取消'], title: "提示"}, function () {
        		layer.close(layer.index);
        		$.ajax({
        	        type: "POST",
        	        url: "./deleteCs",
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
        url: "./getCsbTablesInfo",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                if (item.table_comment == "") {
                    item.table_comment = item.table_name;
                }
                tabSchemaMap[item.table_name]=item.tabSchema;
                pkMap[item.table_name]=item.pk;
                orderByMap[item.table_name]=item.orderBy;
                sfkxgMap[item.table_name]=item.sfkxg;
                sfkxzMap[item.table_name]=item.sfkxz;
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
