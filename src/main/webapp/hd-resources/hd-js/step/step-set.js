// JavaScript Document
$(document).ready(function(){
	
//定义元素	
	var boxWidth=$(".set-step-box").width();
	var stepitemStep='<ul class="item-ul item-step" step-type="node"><div class="step-div"><p class="step-icon optional" val-open="true"><i icon-id="0" class="iconfont fsicon-default"></i></p><a href="###" val-href="请输入链接" class="step-name">点击修改节点</a></div><div class="description"></div></ul>';
	var steparrowLeft='<ul class="item-ul arrow-left pointX" step-type="point"><p></p></ul>';
	var steparrowRight='<ul class="item-ul arrow-right pointX" step-type="point"><p></p></ul>';
	var steparrowUp='<ul class="item-ul arrow-up pointY" step-type="point"><p></p></ul>';
	var steparrowDown='<ul class="item-ul arrow-down pointY" step-type="point"><p></p></ul>';
	var steplineX='<ul class="item-ul line-x pointX" step-type="point"><p></p></ul>';
	var steplineY='<ul class="item-ul line-y pointY" step-type="point"><p></p></ul>';
	
	var node='<div class="item-but"><i class="iconfont fsicon-btn-edit"></i><i class="iconfont fsicon-close"></i></div>';//元素操作按钮
    var point='<div class="item-but"><i class="iconfont fsicon-close"></i></div>';//元素操作按钮
//添加元素
	
	$("#new-add").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(stepitemStep);
		dragSet()
	})
	$(".tax-step-menu #btn-add").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(stepitemStep)
		dragSet()
	})
	$(".tax-step-menu #btn-left").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(steparrowLeft)
		dragSet()
		resizableX()
	})
	$(".tax-step-menu #btn-right").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(steparrowRight)
		dragSet()
		resizableX()
	})
	$(".tax-step-menu #btn-up").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(steparrowUp)
		dragSet()
		resizableY()
	})
	$(".tax-step-menu #btn-down").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(steparrowDown)
		dragSet()
		resizableY()
	})
	$(".tax-step-menu #btn-bar").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(steplineX)
		dragSet()
		resizableX()
	})
	$(".tax-step-menu #btn-vertical").on("click",function(){
		delEmpty()//删除提醒
		$(".set-step-box").append(steplineY)
		dragSet()
		resizableY()
	})
	
//删除元素
	
 function delEmpty(){
	 $(".set-empty").remove();
 }	

$(".set-step-box").on("click",".fsicon-close",function(){
	 
	$(this).parents(".item-ul").remove();
})	
	
//操作区域缩放

	
$(".set-step-box").resizable({ 

      maxWidth: boxWidth,
      minWidth: boxWidth

});

//约束拖动
function dragSet(){
	$(".set-step-box ul").draggable(({ 
		containment: ".set-step-box"
		,scroll: false 
		,grid: [ 1, 1]
		
		,drag: function() {
	  var X = $(this).position().top;
      var Y = $(this).position().left;	
	  $(".set-step-box .step-coordinates").text("T:"+X+" L:"+Y+" W:"+$(this).width()+" H:"+$(this).height())
         
      }
	
	}));
}
	
function resizableX(){
	
	$( ".pointX" ).resizable({
      maxHeight: 36,  
      minHeight: 36
	
    });
}
	
function resizableY(){
	$( ".pointY" ).resizable({
      maxWidth: 36,  
      minWidth: 36
	  
    });
}
	
//元素操作
	
	$(".set-step-box").on("mouseenter",".item-ul",function(){
		
	  var X = $(this).position().top;
      var Y = $(this).position().left;	
	  $(".set-step-box").append('<div class="step-coordinates"></div>')	
      $(".set-step-box .step-coordinates").text("T:"+X+" L:"+Y+" W:"+$(this).width()+" H:"+$(this).height())
	 if($(this).attr("step-type")=="node"){
		 $(this).append(node)
	 
	 }else{
		 $(this).append(point)
		 
	 };
	
	 	
		
	})

   $(".set-step-box").on("mouseleave",".item-ul",function(){
	 
	$(".item-but").remove();
	 $(".set-step-box .step-coordinates").remove()  
	
	})
	
//编就修改元素
	
	$(".set-step-box").on("click",".fsicon-btn-edit",function(){
		
		$(".set-step-box .item-ul").removeClass("eidt-this")
		$(this).parents(".item-ul").addClass("eidt-this")
		
			layer.open({
            type: 1, area: ['710px', '500px'],
			title: '编辑节点',
            content: $("#edit")
           ,btn: ['确定', '关闭']
           ,btnAlign: 'r' //按钮居中
           ,yes: function(){
			 
		    saveStep();
            layer.closeAll();
			   
           }
		    ,btn2: function(index, layero){
			//按钮【按钮二】的回调

			//return false 开启该代码可禁止点击该按钮关闭
		  }
            }); 


//设置表单	
var node=$(".set-step-box .eidt-this .step-name").text();
var http=$(".set-step-box .eidt-this .step-name").attr("val-href");
var description=$(".set-step-box .eidt-this .description").text();
var open=$(".set-step-box .eidt-this .step-icon").attr("val-open");
	
layui.use(['form'], function(){
  var form = layui.form
//表单初始赋值
  form.val('eidt-step', {
    "node": node //节点名称
	,"http": http //链接地址
	,"description": description
  })

	
})

$(".step-icon-item span").removeClass("icon-this")	;	
var iconid=$(".set-step-box .eidt-this .step-icon i").attr("icon-id");	
$(".step-icon-item span").eq(iconid).addClass("icon-this");	
		
		

})
	
	
	
//保存编辑

function saveStep(){

var node=$('input[name="node"]').val()
var http=$('input[name="http"]').val()
var description=$('input[name="description"]').val()
if(http=="请输入链接地址" || http.length<10){
 $(".set-step-box .eidt-this .step-div .step-icon").addClass("optional");
}else{$(".set-step-box .eidt-this .step-div .step-icon").removeClass("optional");}
	
var iconItem=$(".step-icon-item .icon-this").html();
	
//设置大厅
$(".set-step-box .eidt-this .step-div .step-name").text(node);	
$(".set-step-box .eidt-this .step-div .step-name").attr("val-href",http);	
$(".set-step-box .eidt-this .description").text(description)
$(".set-step-box .eidt-this .step-icon").html(iconItem)	
$(".set-step-box .eidt-this").removeClass("eidt-this")
	
}


$(".step-icon-item").on("click","span",function(){
$(this).addClass("icon-this").siblings().removeClass("icon-this");
	
})
	
$(".step-icon-item span").removeClass("icon-this");	
	

	
	
	
	
//保存步骤图
	
	
	$("#btn-save").on("click",function(){
		
		$("#json01").remove();
		$("body").append('<div class="win-center" id="json01" style="display: none;"></div>')
		
		var itemLength=$(".set-step-box .item-ul").length;
		
		for(var i=0; i<itemLength;i++){
			var itemUi=$(".set-step-box .item-ul").eq(i)
			if(itemUi.attr("step-type")=="node"){
				var type="node"
				var t=itemUi.position().top;
				var l=itemUi.position().left;
				var w=itemUi.width();
				var h=itemUi.height();
				var bg=itemUi.find(".step-icon").attr("class");
				var icon=itemUi.find("i").attr("class") ;
				var iconId=itemUi.find("i").attr("icon-id") ;
				var name=itemUi.find("a").text();
				var href=itemUi.find("a").attr("href");
				var description=itemUi.children(".description").text();
				var data='{'+'"type":'+'"'+type+'",'+'"t":'+'"'+t+'",'+'"l":'+'"'+l+'",'+'"w":'+'"'+w+'",'+'"h":'+'"'+h+'",'+'"bg":'+'"'+bg+'",'+'"icon":'+'"'+icon+'",'+'"iconId":'+'"'+iconId+'",'+'"name":'+'"'+name+'",'+'"href":'+'"'+href+'",'+'"description":'+'"'+description+'"'+'},';
				
				 
			}else{
    			var type="point"
				var t=itemUi.position().top;
				var l=itemUi.position().left;
				var w=itemUi.width();
				var h=itemUi.height();
				var style=itemUi.attr("class");
				var data='{'+'"type":'+'"'+type+'",'+'"t":'+'"'+t+'",'+'"l":'+'"'+l+'",'+'"w":'+'"'+w+'",'+'"h":'+'"'+h+'",'+'"style":'+'"'+style+'"'+'},';
				
				
			}
			

			
			$("#json01").append(data);

		}
		
		$("#json01").append('"box":[{"width":'+'"'+$(".set-step-box").width()+'"'+'"height":'+$(".set-step-box").height()+'"'+'}');
		
		layer.open({
            type: 1, area: ['710px', '500px'],
			title: '生成json',
            content: $("#json01")
           ,btn: ['确定', '关闭']
           ,btnAlign: 'r' //按钮居中
           ,yes: function(){
	
            layer.closeAll();
			   
           }
		    ,btn2: function(index, layero){
			//按钮【按钮二】的回调

			//return false 开启该代码可禁止点击该按钮关闭
		  }
            }); 
		
	})
	
	
	
	
	
	
})