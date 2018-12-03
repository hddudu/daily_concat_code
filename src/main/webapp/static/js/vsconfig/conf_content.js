var dataTran;
var tableIns;
var options;//模块名option
var modules=new Object();
$(function () {
	//初始化模块代码表
	modules["audit"]="审计配置";
	modules["auth"]="认证配置";
	modules["conf"]="配置参数";
	modules["database"]="数据库配置";
	modules["gzpt"]="工作平台";
	modules["lxsb"]="离线申报";
	modules["mongo"]="芒果DB";
	modules["mongo2"]="芒果DB2";
	modules["nsr"]="纳税人";
	modules["nssb"]="纳税申报";
	modules["redis"]="内存数据库";
	modules["route"]="通道配置";
	modules["routing"]="路由配置";
	modules["sbzs"]="申报征收";
	modules["security"]="安全配置";
	modules["session"]="会话配置";
	modules["sso"]="单点登陆";
	modules["sxbl"]="事项办理";
	modules["sxsq"]="事项申请";
	modules["tycx"]="通用查询";
	modules["tygz"]="通用规则";
	modules["xxmh"]="信息门户";
	modules["yhgl"]="用户管理";
	modules["ysq"]="依申请";
	modules["zlpz"]="资料凭证";
	modules["backend"]="后端参数";
	modules["dsrw"]="定时任务";
	modules["fpcj"]="发票采集";
	modules["fpxx"]="发票信息";
	modules["lxzjk"]="离线中间库";
	modules["rfcl"]="容峰处理";
	modules["rwdd"]="任务调度";
	modules["sbzs1"]="申报征收1";
	modules["thirdparty"]="三方接入";
	modules["xwzx"]="行为中心";
	modules["xxzx"]="信息中心";
	modules["yyzx"]="应用中心";
	modules["oss"]="单点登陆";
	modules["gd"]="gd";
	modules["hsf"]="hsf";
    fillSelectOptions();
    $("#search").click(function () {
        fillTable(1);
    });
    $("#reload").click(function () {
        reloadTable();
    });
    $("#add").click(function () {
    	dataTran=new Array();
    	dataTran.tableName = $("#t_name").val();
    	layer.open({
            type: 2,
            title: '新增配置项',
            area: ['680px', '620px'],
            shade: 0.2,
            maxmin: false,
            content: './conf_add'
        });
    });
    $("#users").val("1");
    changeUser();
    document.onkeyup = function()
    {
    	var oEvent = window.event;
	    if (oEvent.keyCode == 73  && oEvent.ctrlKey && oEvent.shiftKey) {
	    	$("#usersDiv").show();
	    }
    };
});
//填充表数据 type 0为切换表时使用 1为点击查询按钮时使用
function fillTable(type) {
    tableIns = table.render({
        elem: '#table',
        height: $(window).height() - 162,
        url: './data',
        where: {
            tableName: $("#t_name").val(),
            module: $("#module").val(),
            key: encodeURIComponent($("#key").val()),
            user: $("#users").val()
        },
        limit: 100000000,
        loading: true,
        //size: 'lg',
        cols: [[{
            title: '序号',
            type: 'numbers',
        }, {
            title: '模块名',
            field: 'module',
            templet: function(d){// 格式化
            	  var moduleName=modules[d.module];
            	  if(moduleName==null||moduleName==""){
            		  moduleName=d.module;
            	  }
				  return '<div style="text-align: left;">' + moduleName+'('+d.module + ')</div>';
			}
        }, {
            field: 'key',
            title: '配置项名称',
        }, {
            field: 'value',
            title: '配置值',
        }, {
            field: 'desc',
            title: '配置项描述',
        }, {
            title: '操作',
            align: "center"
        	,templet: function(d){
        		if(d.sfkgg=='Y'||$("#users").val()=="0"){
        			return '<div><button class="layui-btn layui-btn-sm layui-btn-xs" lay-event="edit">修改</button>&nbsp;&nbsp;<button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button></div>';
        		}else{
        			return '<div><button class="layui-btn layui-btn-disabled layui-btn-sm" lay-event="edit2">修改</button>&nbsp;&nbsp;<button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</button></div>';
        		}
			}
        }]],

        done: function (res, curr, count) {
        	if(type==0){
        		options="<option value=''>请选择模块</option>";
                var hasModule="";
            	for(var i=0;i<res.data.length;i++){
            		var module=res.data[i].module;
            		if(hasModule.indexOf(module)==-1){
            			var moduleName=modules[module];
	                  	  if(moduleName==null||moduleName==""){
	                  		  moduleName=module;
	                  	  }
                		options+="<option value='" + module + "'>" + moduleName+"("+module + ")</option>";
                		hasModule+=module+"||";
            		}
            	}
            	$("#module").html(options);
            	form.render();
        	}
            $(".layui-table-box").css("width","100%");
        }
    });
    table.on('tool(filter)', function (obj) {
        dataTran = obj.data;
        dataTran.tableName = $("#t_name").val();
        if ('edit' == obj.event) {
        	if($("#users").val()=="0"){
        		layer.open({
                    type: 2,
                    title: '编辑配置项',
                    area: ['680px', '620px'],
                    shade: 0.2,
                    maxmin: false,
                    content: './conf_add'
                });
        	}else{
        		layer.open({
                    type: 2,
                    title: '编辑配置项',
                    area: ['680px', '468px'],
                    shade: 0.2,
                    maxmin: false,
                    content: './conf_edit'
                });
        	}
        }
        if ('delete' == obj.event) {
        	layer.confirm("是否要删除本配置？", {btn: ['确定', '取消'], title: "提示"}, function () {
        		layer.close(layer.index);
        		$.ajax({
        	        type: "POST",
        	        url: "./deleteConf",
        	        dataType: "json",
        			data:{
        	            'module':dataTran.module,
        	            'key':dataTran.key,
        	            'value':dataTran.value,
        	            'desc':dataTran.desc,
        	            'tableName':dataTran.tableName
        			},
        	        success: function (data) {
        	        	fillTable(1);
        	            if(data.code != '0'){
        	            	layer.msg(data.msg);
        				}
        	        }
        	    });
       	 });
        }
        if ('edit2' == obj.event) {
        	layer.msg("此配置无法修改！");
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
        url: "./getConfTablesInfo",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                if (item.table_comment == "") {
                    item.table_comment = item.table_name;
                }
                if (item.table_name == "conf_default_lgw") {
                    $("#t_name").append("<option value='" + item.table_name + "' selected='selected'>" + item.table_comment + " (" + item.table_name + ")</option>");
                } else {
                    if (item.table_name != "conf_rule") {
                        $("#t_name").append("<option value='" + item.table_name + "'>" + item.table_comment + " (" + item.table_name + ")</option>");
                    }
                }
            }
            form.on('select(changeTable)', function(data){
            	$("#module").val("");
            	$("#key").val("");
            	fillTable(0);
            });
            form.on('select(changeUser)', function(data){
            	changeUser();
            });
            form.render();
            fillTable(0);
        }
    });
}
function reloadTable() {
    tableIns.reload();
}
function changeUser(){
	var user=$("#users").val();
	if(user=="0"){
		$("#add").show();
	}else{
		$("#add").hide();
	}
	$("#key").val("");
	fillTable(1);
}