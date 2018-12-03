var primaryKeyComment="";
$(function(){
	var data = parent.dataTran;
	var columnNames=parent.columnNames;
	var div="";
	for (var i = 0; i < columnNames.length; i++) {
        var item = columnNames[i]; 
        if(primaryKeyComment==""&&item.column_name==parent.primaryKey){
        	primaryKeyComment=item.column_comment;
        }
        if(item.column_name=='YXBZ'||item.column_name=='XYBZ'||item.column_name=='yxbz'||item.column_name=='xybz'){
        	div+='<div class="layui-form-item"  style="width:620px">'
    			+'<div>'
    			+'<label>'+item.column_comment+'：</label><br/>'
    			+'<select id="'+item.column_name+'" >'
    			+'<option value="Y" selected="selected">Y</option>'
    			+'<option value="N">N</option>'
    			+'</select>'
    			+'</div>'
    			+'</div>';
        }else{
        	if(item.column_name=='lrrq'||item.column_name=='lrr_dm'||item.column_name=='xgrq'||item.column_name=='xgr_dm'||item.column_name=='LRRQ'||item.column_name=='LRR_DM'||item.column_name=='XGRQ'||item.column_name=='XGR_DM'){
        		div+='<div class="layui-form-item" style="width:620px;display:none;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input" id="'+item.column_name+'" value="">'
        			+'</div>'
        			+'</div>';
        	}else if(parent.primaryKey==item.column_name&&(parent.primaryKey.indexOf("id")>0||(parent.primaryKey.indexOf("ID")>0))){
        		div+='<div class="layui-form-item" style="width:620px;display:none;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input" id="'+item.column_name+'" value="">'
        			+'</div>'
        			+'</div>';
        	}else if(item.data_type=="date"||item.data_type=="datetime"||item.data_type=="timestamp"){
        		div+='<div class="layui-form-item" style="width:620px;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input layui-date-input" id="'+item.column_name+'" value="" readOnly>'
        			+'</div>'
        			+'</div>';
        	}else{
        		div+='<div class="layui-form-item"  style="width:620px">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input" id="'+item.column_name+'" value="">'
        			+'</div>'
        			+'</div>';
        	}
        }
        
    }
	$("#form").html(div);
	
	layui.use('laydate', function(){
		var laydate = layui.laydate;
		for (var i = 0; i < columnNames.length; i++) {
	        var item = columnNames[i]; 
	        if(item.data_type=="date"||item.data_type=="datetime"||item.data_type=="timestamp"){
	        	laydate.render({
					elem: '#'+item.column_name,
					max: '9999-12-31'
				});
	        }  
		}
	});
	
	$("#save").click(function(){
		csbAdd(data);
	});
	$("#cancel").click(function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
	layui.use('form', function(){
		var form = layui.form;
		form.render();
	});
});
function csbAdd(data){
	var columnNames=parent.columnNames;
	var div="";
	for (var i = 0; i < columnNames.length; i++) {
		var item = columnNames[i]; 
		data[item.column_name]=$("#"+item.column_name).val();
	}
	var primaryKeyValue=$("#"+parent.primaryKey).val();
	if(primaryKeyValue==""&&parent.primaryKey.indexOf("id")==-1&&(parent.primaryKey.indexOf("ID")==-1)){
		layer.msg(primaryKeyComment+"不能为空！");
		return;
	}
	data.primaryKey=parent.primaryKey;
	data.tabSchema=parent.tabSchema;
    $.ajax({
        type: "POST",
        url: "./insertCs",
        dataType: "json",
		data:{
			'jsonStr':JSON.stringify(data)
		},
        success: function (data) {
        	if(data.code=="0"){
        		layer.msg("新增成功！");
        		parent.fillTable();
        		var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
        	}else{
        		layer.msg(data.msg);
        	}
        }
    });
}