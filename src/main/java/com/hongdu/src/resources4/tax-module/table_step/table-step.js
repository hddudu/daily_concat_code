// JavaScript Document

$(document).ready(function(){
	
	
	$(".table-step-box").on("mouseenter",function(){
	 
		var width=document.body.clientWidth
		 
		$(".table-step-center").show();
		$(".table-step-center").stop().animate({width:width,opacity:1},300)
		 
	})
	
	$(".table-step-box").on("mouseleave",function(){
	 
		
		$(".table-step-center").stop().animate({width:"0px",opacity:0},300)
		
		setTimeout(function(){ $(".table-step-center").hide();}, 300)
		 
	})
	
})