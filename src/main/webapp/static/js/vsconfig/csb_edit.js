var columnNames=parent.columnNames;
$(function(){
	var data = parent.dataTran;
	var div="";
	for (var i = 0; i < columnNames.length; i++) {
        var item = columnNames[i]; 
        var value="";
        if(typeof(data[item.column_name])!=undefined&&data[item.column_name]!=null){
        	value=data[item.column_name];
        }
        if(item.column_name=='YXBZ'||item.column_name=='XYBZ'||item.column_name=='yxbz'||item.column_name=='xybz'){
        	div+='<div class="layui-form-item"  style="width:620px">'
    			+'<div>'
    			+'<label>'+item.column_comment+'：</label><br/>'
    			+'<select id="'+item.column_name+'" >';
	        	if(value=='Y'){
	        		div+='<option value="Y" selected>Y</option>'
	    			+'<option value="N">N</option>';
	        	}else{
	        		div+='<option value="Y">Y</option>'
	        			+'<option value="N" selected>N</option>';
	        	}
    			div+='</select>'
    			+'</div>'
    			+'</div>';
        }else{
        	//特殊字段不能修改
        	if(item.column_name=='lrrq'||item.column_name=='lrr_dm'||item.column_name=='xgrq'||item.column_name=='xgr_dm'||item.column_name=='LRRQ'||item.column_name=='LRR_DM'||item.column_name=='XGRQ'||item.column_name=='XGR_DM'){
        		div+='<div class="layui-form-item" style="width:620px;display:none;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input" id="'+item.column_name+'" value="'+value+'">'
        			+'</div>'
        			+'</div>';
        	}else if(parent.primaryKey==item.column_name&&(parent.primaryKey.indexOf("id")>0||(parent.primaryKey.indexOf("ID")>0))){
        		div+='<div class="layui-form-item" style="width:620px;display:none;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input" id="'+item.column_name+'" value="'+value+'">'
        			+'</div>'
        			+'</div>';
        	}else if(item.data_type=="date"||item.data_type=="datetime"){
        		div+='<div class="layui-form-item" style="width:620px;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input layui-date-input" id="'+item.column_name+'" value="'+value+'" readOnly>'
        			+'</div>'
        			+'</div>';
        	}else{
        		div+='<div class="layui-form-item" style="width:620px;">'
        			+'<div>'
        			+'<label>'+item.column_comment+'：</label><br/>'
        			+'<input type="text" class="layui-input" id="'+item.column_name+'" value="'+value+'">'
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
	$("#"+parent.primaryKey).attr("disabled","disabled");
	$("#save").click(function(){
		csbEdit(data);
	});
	$("#cancel").click(function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
});
function csbEdit(data){
	for (var i = 0; i < columnNames.length; i++) {
		var item = columnNames[i]; 
		data[item.column_name]=$("#"+item.column_name).val();
	}
	data.primaryKey=parent.primaryKey;
	data.tabSchema=parent.tabSchema;
    $.ajax({
        type: "POST",
        url: "./CsbEdit",
        dataType: "json",
		data:{
			'jsonStr':JSON.stringify(data)
		},
        success: function (data) {
        	if(data.code=="0"){
        		layer.msg("修改成功！");
        		parent.fillTable();
        		var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
        	}else{
        		layer.msg(data.msg);
        	}
        }
    });
}