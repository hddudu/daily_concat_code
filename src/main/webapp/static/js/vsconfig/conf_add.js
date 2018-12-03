$(function(){
	$("#xxsm").hide();
	$("#module").html(parent.options);
	$("#cancel").click(function(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
	});
	//开发者点修改进来的情况下
	if(parent.dataTran.key!=null&&parent.dataTran.key!=""){
		var valueType=parent.dataTran.valueType;
		var constraint=parent.dataTran.constraint;
		$("#valueType").val(valueType);
		$("#valueType").attr("disabled","disabled");
		$("#module").attr("disabled","disabled");
		$("#key").attr("disabled","disabled");
		$("#module").val(parent.dataTran.module);
		$("#key").val(parent.dataTran.key);
		$("#desc").val(parent.dataTran.desc);
		$("#sfkgg").val(parent.dataTran.sfkgg);
		$("#sfkpz").val(parent.dataTran.sfkpz);
		$("#sfbt").val(parent.dataTran.sfbt);
		$("#sfqhc").val(parent.dataTran.sfqhc);
		changeValueType();
		$("#constraint").val(constraint);
		if(valueType=="2"){
			var json= JSON.parse(constraint);
			var min=json[0].min;
			var max=json[1].max;
			$("#min").val(min);
			$("#max").val(max);
		}else if(valueType=="3"){
			$("#valueDate").val(parent.dataTran.value);
			var json= JSON.parse(constraint);
			var min=json[0].minDate;
			var max=json[1].maxDate;
			$("#minDate").val(min);
			$("#maxDate").val(max);
			// 初始化日期组件
			layui.use('laydate', function(){
				var laydate = layui.laydate;
				var minDate=laydate.render({
					elem: '#minDate',
					max: max==""?"2099-12-31":max,
		            done: function (value, dates) {
		            	if(value!=""){
		            		if($("#valueDate").val()<value){
		            			$("#valueDate").val(value);
							}
		            		maxDate.config.min ={
		                        year:dates.year, 
		                        month:dates.month-1, 
		                        date: dates.date, 
		                        hours: 0, 
		                        minutes: 0, 
		                        seconds : 0
		                    };
		                    valueDate.config.min ={
		                       year:dates.year, 
		                       month:dates.month-1, 
		                       date: dates.date, 
		                       hours: 0, 
		                       minutes: 0, 
		                       seconds : 0
		                   };
		            	}else{
		            		maxDate.config.min ={
		                        year:1900, 
		                        month:2, 
		                        date: 1, 
		                        hours: 0, 
		                        minutes: 0, 
		                        seconds : 0
		                    };
		                    valueDate.config.min ={
		                		year:1900, 
		                        month:2, 
		                        date: 1, 
		                        hours: 0, 
		                        minutes: 0, 
		                        seconds : 0
		                   };
		            	}
		            }
				});
				var maxDate=laydate.render({
					elem: '#maxDate',
					min: min==""?"1900-01-01":min,
					done: function (value, dates) {
						if(value!=""){
							if($("#valueDate").val()>value){
								$("#valueDate").val(value);
							}
							minDate.config.max ={
			                    year:dates.year, 
			                    month:dates.month-1, 
			                    date: dates.date, 
			                    hours: 0, 
			                    minutes: 0, 
			                    seconds : 0
			               };   
						   valueDate.config.max ={
			                    year:dates.year, 
			                    month:dates.month-1, 
			                    date: dates.date, 
			                    hours: 0, 
			                    minutes: 0, 
			                    seconds : 0
				           }; 
			            }else{
			            	minDate.config.max ={
			                    year:2099, 
			                    month:11, 
			                    date: 30, 
			                    hours: 0, 
			                    minutes: 0, 
			                    seconds : 0
			               };   
						   valueDate.config.max ={
							   year:2099, 
			                   month:11, 
			                   date: 30, 
			                   hours: 0, 
			                   minutes: 0, 
			                   seconds : 0
				           }; 
			            }
					}
				});
				var valueDate=laydate.render({
					elem: '#valueDate',
					min: min==""?"1900-01-01":min,
					max: max==""?"2099-12-31":max
				});
			});
		}else if(valueType=="4"){
			changeConstraint(constraint);
		}
		$("#value").val(parent.dataTran.value);
		$("#save").click(function(){
			var data=new Array();
			data.valueType=$("#valueType").val();
			if(data.valueType=="3"){
				data.value=$("#valueDate").val();
			}else if(data.valueType=="4"){
				data.value=$("#valueSelect").val();
			}else{
				data.value=$.trim($("#value").val());
			}
			data.module=$("#module").val();
			data.key=$.trim($("#key").val());
			data.desc=$.trim($("#desc").val());
			data.sfkgg=$("#sfkgg").val();
			data.sfkpz=$("#sfkpz").val();
			data.sfbt=$("#sfbt").val();
			data.sfqhc=$("#sfqhc").val();
			if(data.valueType==2){
				data.constraint='[{"min":"'+$.trim($("#min").val())+'"},{"max":"'+$.trim($("#max").val())+'"}]';
			}else if(data.valueType==3){
				data.constraint='[{"minDate":"'+$("#minDate").val()+'"},{"maxDate":"'+$("#maxDate").val()+'"}]';
			}else{
				data.constraint=$.trim($("#constraint").val());
			}
			data.tableName=parent.dataTran.tableName;
	        confEdit(data);
		});
	}else{
		$("#save").click(function(){
			var data=new Array();
			data.valueType=$("#valueType").val();
			if(data.valueType=="3"){
				data.value=$("#valueDate").val();
			}else if(data.valueType=="4"){
				data.value=$("#valueSelect").val();
			}else{
				data.value=$.trim($("#value").val());
			}
			data.module=$("#module").val();
			data.key=$.trim($("#key").val());
			data.desc=$.trim($("#desc").val());
			data.sfkgg=$("#sfkgg").val();
			data.sfkpz=$("#sfkpz").val();
			data.sfbt=$("#sfbt").val();
			data.sfqhc=$("#sfqhc").val();
			if(data.valueType==2){
				data.constraint='[{"min":"'+$.trim($("#min").val())+'"},{"max":"'+$.trim($("#max").val())+'"}]';
			}else if(data.valueType==3){
				data.constraint='[{"minDate":"'+$("#minDate").val()+'"},{"maxDate":"'+$("#maxDate").val()+'"}]';
			}else{
				data.constraint=$.trim($("#constraint").val());
			}
			data.tableName=parent.dataTran.tableName;
	        confAdd(data);
		});
		// 初始化日期组件
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			
			var minDate=laydate.render({
				elem: '#minDate',
	            done: function (value, dates) {
	            	if(value!=""){
	            		if($("#valueDate").val()<value){
	            			$("#valueDate").val(value);
						}
	            		maxDate.config.min ={
	                        year:dates.year, 
	                        month:dates.month-1, 
	                        date: dates.date, 
	                        hours: 0, 
	                        minutes: 0, 
	                        seconds : 0
	                    };
	                    valueDate.config.min ={
	                       year:dates.year, 
	                       month:dates.month-1, 
	                       date: dates.date, 
	                       hours: 0, 
	                       minutes: 0, 
	                       seconds : 0
	                   };
	            	}else{
	            		maxDate.config.min ={
	                        year:1900, 
	                        month:2, 
	                        date: 1, 
	                        hours: 0, 
	                        minutes: 0, 
	                        seconds : 0
	                    };
	                    valueDate.config.min ={
	                		year:1900, 
	                        month:2, 
	                        date: 1, 
	                        hours: 0, 
	                        minutes: 0, 
	                        seconds : 0
	                   };
	            	}
	            }
			});
			var maxDate=laydate.render({
				elem: '#maxDate',
				done: function (value, dates) {
					if(value!=""){
						if($("#valueDate").val()>value){
							$("#valueDate").val(value);
						}
						minDate.config.max ={
		                    year:dates.year, 
		                    month:dates.month-1, 
		                    date: dates.date, 
		                    hours: 0, 
		                    minutes: 0, 
		                    seconds : 0
		               };   
					   valueDate.config.max ={
		                    year:dates.year, 
		                    month:dates.month-1, 
		                    date: dates.date, 
		                    hours: 0, 
		                    minutes: 0, 
		                    seconds : 0
			           }; 
		            }else{
		            	minDate.config.max ={
		                    year:2099, 
		                    month:11, 
		                    date: 30, 
		                    hours: 0, 
		                    minutes: 0, 
		                    seconds : 0
		               };   
					   valueDate.config.max ={
						   year:2099, 
		                   month:11, 
		                   date: 30, 
		                   hours: 0, 
		                   minutes: 0, 
		                   seconds : 0
			           }; 
		            }
				}
			});
			var valueDate=laydate.render({
				elem: '#valueDate'
			});
		});
	}
	layui.use('form', function(){
		var form = layui.form;
		form.on('select(changeValueType)', function(){
			changeValueType();
	    });
		form.render();
	});
});
function changeValueType(){
	var valueType=$("#valueType").val();
	$("#constraintDiv").hide();
	$("#valueSelect").html("");
    $("#sfbt").removeAttr("disabled");
	$("#constraint").val("");
	$("#value").val("");
	$("#maxMinDateDiv").hide();
	$("#valueSelectDiv").hide();
	$("#constraint").removeAttr("onBlur");
	$("#valueDiv").show();
	if(valueType=="2"){
		$("#maxMinDiv").show();
		$("#value").attr("oninput","value=value.replace(/[^\\d.\\d]/g,'')");
		$("#value").attr("onBlur","if(value!=''&&!/^[0-9]+.?[0-9]*$/.test(value)){value='';}");
	}else{
		$("#value").removeAttr("oninput");
		$("#value").removeAttr("onBlur");
		$("#maxMinDiv").hide();
		if(valueType=="3"){
			$("#maxMinDateDiv").show();
			$("#valueDate").val("");
			$("#valueDate").show();
			$("#value").hide();
		}else{
			$("#value").show();
			$("#valueDate").hide();
			if(valueType=="1"){
				$("#wbsm").show();
				$("#xxsm").hide();
				$("#constraintDiv").show();
				$("#constraintName").html("取值约束");
			}else if(valueType=="4"){
				$("#xxsm").show();
				$("#wbsm").hide();
				$("#constraint").attr("onBlur","changeConstraint(this.value);");
				$("#constraintDiv").show();
				$("#valueDiv").hide();
				$("#valueSelectDiv").show();
				$("#constraintName").html("选项设置");
				$("#sfbt").val("Y");
				$("#sfbt").attr("disabled","disabled");
				$("#constraint").val('[{"":""},{"":""}]');
			}
		} 
	} 
	if(typeof(form)!=undefined&&form!=null){
		form.render();
	}
}
function confAdd(data){
	if(!validate(data)){
		return;
	}
    $.ajax({
        type: "POST",
        url: "./insertConf",
        dataType: "json",
		data:{
            'module':data.module,
            'key':data.key,
            'value':data.value,
            'desc':data.desc,
            'tableName':data.tableName,
            'valueType':data.valueType,
            'constraint':data.constraint,
            'sfkgg':data.sfkgg,
            'sfkpz':data.sfkpz,
            'sfbt':data.sfbt,
            'sfqhc':data.sfqhc
		},
        success: function (data) {
            layer.msg(data.msg);
            if(data.code=="0"){
            	parent.fillTable();
        		var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        }
    });
}
function confEdit(data){
	if(!validate(data)){
		return;
	}
    $.ajax({
        type: "POST",
        url: "./confEditKfz",
        dataType: "json",
		data:{
            'module':data.module,
            'key':data.key,
            'value':data.value,
            'desc':data.desc,
            'tableName':data.tableName,
            'valueType':data.valueType,
            'constraint':data.constraint,
            'sfkgg':data.sfkgg,
            'sfkpz':data.sfkpz,
            'sfbt':data.sfbt,
            'sfqhc':data.sfqhc
		},
        success: function (data) {
            layer.msg(data.msg);
            parent.fillTable();
    		var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
    });
}

function validate(data){     
	var value=data.value;
	var module=data.module;
	var key=data.key;
	var desc=data.desc;
	var valueType=data.valueType;
	var constraint=data.constraint
	//校验模块名
	if(module==""){
    	layer.msg('模块名不可为空!'); 
    	return false;
    }else{
    	if(!/^[0-9a-zA-Z]+$/ig.test(module)){   
        	layer.msg('您输入的模块名格式有误,只能输入英文字母和数字!'); 
        	return false;
        }  
    }
	//校验配置项名称
	if(key==""){
    	layer.msg('配置项名称不可为空!'); 
    	return false;
    }else{
    	if(!/^[0-9a-zA-Z\.]+$/.test(key)){     
        	layer.msg('您输入的配置项名称式不正确,只能输入英文字母、数字和小数点!'); 
        	return false;
        }  
    }
	//校验配置值
    if(value!=""||data.sfbt=="N"){
    	if(valueType=="1"){
    		if(constraint!=""){
        		constraint=constraint.replace(/\/\^/g,'^');
        		constraint=constraint.replace(/\$\//g,'$');
        		try{
        			if(!new RegExp(constraint).test(value)){
        				layer.msg('您输入的配置值格式有误,必须与取值约束匹配!'); 
                    	return false;
            		}
        			data.constraint=constraint.replace(/\\/g,'\\\\');;
        		}catch(err){
                	layer.msg('您输入的取值约束格式有误,请检查!'); 
                	return false;
        		}
    		}
    	}else if(valueType=="2"){
    		var min=$("#min").val();
    		var max=$("#max").val();
    		if(value!=""&&!/^[0-9]+.?[0-9]*$/.test(value)){   
            	layer.msg('您输入的配置值格式有误,必须是数值格式!'); 
            	return false;
            }else{
            	if(min!=""&&!/^[0-9]+.?[0-9]*$/.test(min)){
            		layer.msg('您输入的取值范围最小值格式有误,必须是数值格式!'); 
                	return false;
            	}
            	if(max!=""&&!/^[0-9]+.?[0-9]*$/.test(max)){
            		layer.msg('您输入的取值范围最大值格式有误,必须是数值格式!'); 
                	return false;
            	}
            	if(min!=""&&max!=""&&parseFloat(min)>parseFloat(max)){
            		layer.msg('取值范围最小值不能大于最大值，请重新填写!'); 
                	return false;
            	}
            	if(min!=""&&parseFloat(value)<parseFloat(min)){
            		layer.msg('配置值小于取值范围最小值，请重新填写!'); 
                	return false;
            	}else if(max!=""&&parseFloat(value)>parseFloat(max)){
            		layer.msg('配置值大于取值范围最大值，请重新填写!'); 
                	return false;
            	}
            }
    	}else if(valueType=="3"){
    		if(value!=""&&!/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/.test(value)){   
            	layer.msg('您输入的配置值格式有误,必须是日期格式!'); 
            	return false;
            }
    	}else if(valueType=="4"){
    		if(value==""){   
            	layer.msg('“值类型”为“选项”时，配置值不能为空。'); 
            	return false;
            }
    		if(constraint==""){   
            	layer.msg('“值类型”为“选项”时，取值约束不能为空。'); 
            	return false;
            }
    		var json;
    		try {
    			json=JSON.parse(constraint);
    		  }  catch (err) {
				layer.msg('您输入的取值约束格式有误,必须是JSON格式“[{"":""},{"":""}]”!'); 
			    return false;
			  }
    		if(typeof(json.length)=="undefined"||json.length=="undefined"||json.length==null||json.length<=0){
    			layer.msg('您输入的取值约束格式有误,必须是JSON格式“[{"":""},{"":""}]”!'); 
			    return false;
    		}
    		var hasValue=false;
			for(var i=0,l=json.length;i<l;i++){
				for(var key in json[i]){
					if(key==""){
						layer.msg('取值约束JSON字符串键值对中键不能为空,请检查!'); 
					    return false;
					}else if(json[i][key]==""){
						layer.msg('取值约束JSON字符串键值对中值不能为空,请检查!'); 
					    return false;
					}else{
						if(json[i][key]==value){
							hasValue=true;
						}
					}
				}
			}
			if(!hasValue){
				layer.msg('配置值必须包含在取值约束之中!'); 
				return false;
			}
    	} 
    }else{
    	layer.msg('配置值不可为空!'); 
    	return false;
    }
   
    //校验描述
    if(desc==""){
    	layer.msg('描述不可为空!'); 
    	return false;
    }
    return true;
}
function changeConstraint(constraint){
	$("#valueSelect").html("");
	var json;
	try {
		json=JSON.parse(constraint);
	  }  catch (err) {
		layer.msg('您输入的取值约束格式有误,必须是JSON格式“[{"":""},{"":""}]”!'); 
	    return;
	  }
	if(typeof(json.length)=="undefined"||json.length=="undefined"||json.length==null||json.length<=0){
		layer.msg('您输入的取值约束格式有误,必须是JSON格式“[{"":""},{"":""}]”!'); 
	    return;
	}
	for(var i=0,l=json.length;i<l;i++){
		for(var key in json[i]){
			$("#valueSelect").append('<option value="'+json[i][key]+'">'+key+'('+json[i][key]+')</option>');
		}
	}
	if(typeof(form)!=undefined&&form!=null){
		form.render();
	}
}