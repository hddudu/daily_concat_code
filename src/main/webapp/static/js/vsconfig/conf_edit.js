var reg = "";
var sfqhc="";
$(function(){
	var data = parent.dataTran;
	sfqhc=parent.dataTran.sfqhc;
	$("#valueSelectDiv").hide();
	$("#module").val(data.module);
	$("#key").val(data.key);
	$("#value").val(data.value);
	$("#desc").val(data.desc);
	if(data.valueType=="1"){
		$("#valueType").val("文本");
	}else if(data.valueType=="2"){
		$("#value").attr("oninput","value=value.replace(/[^\\d.\\d]/g,'')");
		$("#value").attr("onBlur","if(value!=''&&!/^[0-9]+.?[0-9]*$/.test(value)){value='';}");
		reg = /^[0-9]+.?[0-9]*$/;
		var json= JSON.parse(data.constraint);
		var min=json[0].min;
		var max=json[1].max;
		var typeMc="数值";
		if(min!=""||max!=""){
			typeMc+="（取值范围：";
			if(min!=""&&max!=""){
				typeMc+=min+"—"+max+"）";
			}else if(min!=""){
				typeMc+="最小值 "+min+"）";
			}else if(max!=""){
				typeMc+="最大值 "+max+"）";
			}
		}
		$("#valueType").val(typeMc);
	}else if(data.valueType=="3"){
		reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
		$("#value").attr("class","layui-input layui-date-input");
		$("#value").attr("readOnly","readOnly");
		// 初始化日期组件
		if(data.constraint!=""){
			var json= JSON.parse(data.constraint);
			var minDate=json[0].minDate==""?"1900-01-01":json[0].minDate;
			var maxDate=json[1].maxDate==""?"2099-12-31":json[1].maxDate;
			layui.use('laydate', function(){
				var laydate = layui.laydate;
				laydate.render({
					elem: '#value',
					min: minDate,
					max: maxDate
				});
			});
		}else{
			layui.use('laydate', function(){
				var laydate = layui.laydate;
				laydate.render({
					elem: '#value' 
				});
			});
		}
		var json= JSON.parse(data.constraint);
		var minDate=json[0].minDate;
		var maxDate=json[1].maxDate;
		var typeMc="日期";
		if(minDate!=""||maxDate!=""){
			typeMc+="（日期范围：";
			if(minDate!=""&&maxDate!=""){
				typeMc+=minDate+"—"+maxDate+"）";
			}else if(minDate!=""){
				typeMc+="最小值 "+minDate+"）";
			}else if(maxDate!=""){
				typeMc+="最大值 "+maxDate+"）";
			}
		}
		$("#valueType").val(typeMc);
	}else if(data.valueType=="4"){
		$("#value").hide();
		$("#valueSelectDiv").show();
		var json= JSON.parse(data.constraint);
		for(var i=0,l=json.length;i<l;i++){
			for(var key in json[i]){
				if(json[i][key]==data.value){
					$("#valueSelect").append('<option value="'+json[i][key]+'" selected>'+key+'('+json[i][key]+')</option>');
				}else{
					$("#valueSelect").append('<option value="'+json[i][key]+'">'+key+'('+json[i][key]+')</option>');
				}
			}
		}
		$("#valueType").val("选项");
	}

	$("#save").click(function(){
        confEdit(data);
	});
	$("#cancel").click(function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
});
function confEdit(data){
	if(data.valueType=="4"){
		$("#value").val($("#valueSelect").val());
	}
	data.value=$("#value").val();
	if(!validate(data)){
		return;
	}
    $.ajax({
        type: "POST",
        url: "./confEdit",
        dataType: "json",
		data:{
            'module':data.module,
            'key':data.key,
            'value':data.value,
            'desc':data.desc,
            'tableName':data.tableName
		},
        success: function (data) {
        	if(data.code=="0"){
        		if(sfqhc=="Y"){
        			layer.msg("修改成功！提醒：该配置需清理缓存才可生效！");
        		}else{
        			layer.msg("修改成功！");
        		}
        		parent.fillTable();
        		var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
        	}else{
        		layer.msg(data.msg);
        	}
        }
    });
}


function validate(data){  
	if(data.value==""&&data.sfbt=="Y"){
		layer.msg('配置值不可为空!'); 
    	return false;
	}
	if(data.valueType=="1"){
		if(data.constraint!=""&&!new RegExp(data.constraint).test(data.value)){
			layer.msg('您输入的配置值格式有误,必须与取值约束【/'+data.constraint+'/】匹配!'); 
	    	return false;
		}
	}else if(data.valueType=="2"){
		if(data.constraint!=""){
			var json= JSON.parse(data.constraint);
			var min=json[0].min;
			var max=json[1].max;
			if(data.value!=""){
				if(min!=""&&parseFloat(data.value)<parseFloat(min)){
		    		layer.msg('配置值小于取值范围最小值【'+min+'】，请重新填写!'); 
		        	return false;
		    	}else if(max!=""&&parseFloat(data.value)>parseFloat(max)){
		    		layer.msg('配置值大于取值范围最大值【'+max+'】，请重新填写!'); 
		        	return false;
		    	}
			}
		}
	}
    if(data.value!=""&&reg!=""&&!reg.test(data.value)){   
    	layer.msg('您输入的配置值格式有误!'); 
    	return false;
    }    
    
    return true;
}