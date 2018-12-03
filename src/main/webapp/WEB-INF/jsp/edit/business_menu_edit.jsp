<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<style type="text/css">
.layui-form-select dl{
	max-width:100%;
	max-height:250px;
}
</style>
<body style="margin:10px 18px 10px 10px;">
<div class="layui-form">
    
    
    <div class="layui-form-item">
	    <div class="layui-col-xs8">
	        <label class="layui-form-label">菜单名称</label>
	        <div class="layui-input-block">
	            <input id="cdmc" type="text" class="layui-input"/>
	        </div>
	    </div>
	    <div class="layui-col-xs4">
	        <div style="margin-left:100px;">
            <input id="selectCdtb" type="hidden">
            <img id="selectCdtbImg" class="selectCdtbImg" style="width: 53px;height: 53px;border: 1px solid #e2e2e2;cursor: pointer;"/>
            <span class="selectCdtbImg" style="cursor: pointer;">选择图标</span>
        </div>
	    </div>
    </div>
    
    <div class="layui-form-item layui-col-xs8">
        <label class="layui-form-label">关联功能</label>
        <div class="layui-input-block">
            <select id="gndm" name="gndm" lay-verify="" lay-search>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单ID</label>
        <div class="layui-input-block">
            <div class="layui-col-xs12">
                <div class="layui-col-xs7">
                    <input id="cdid" type="text" class="layui-input"/>
                </div>
                <div class="layui-col-xs5">
                    <button id="uuid" class="layui-btn layui-btn-fluid">自动生成</button>
                </div>
            </div>
        </div>
       
    </div>
    <div class="layui-form-item" >
    <div class="layui-col-xs6">
        <label class="layui-form-label">父菜单</label>
        <div class="layui-input-block">
            <input id="fcdmc" type="text" class="layui-input">
            <input id="fcdid" type="hidden" class="layui-input"/>
        </div>
        </div>
         <div class="layui-col-xs6">
		        <label class="layui-form-label" style="width:120px">首页菜单打开方式</label>
		        <div class="layui-input-block" style="margin-left:140px;">
		            <select id="sycddkfs">
		            	<option value=""></option>
		                <!-- <option value="0">本页打开</option> -->
		                <option value="1">从旧门户框架打开</option>
		                <option value="2">新标签页打开</option>
		            </select>
		        </div>
	        </div>
    </div>
    
    <div class="layui-form-item">
	    <div class="layui-col-xs12">
	    	<div class="layui-col-xs6">
	    	<label class="layui-form-label">菜单顺序</label>
        <div class="layui-input-block">
            <input id="xh" type="text" placeholder="请输入菜单顺序（数字）" onkeyup="value=value.replace(/[^\d]/g,'')"
                   maxlength="3" class="layui-input"/>
        </div>
	    	</div>
	        <div class="layui-col-xs6">
		        <label class="layui-form-label">是否启用</label>
		        <div class="layui-input-block">
		            <select id="tybz">
		                <option value="N">是</option>
		                <option value="Y">否</option>
		            </select>
		        </div>
	        </div>
	    </div>
    </div>
     <div class="layui-form-item">
     	<div class="layui-col-xs6">
	        <label class="layui-form-label">菜单属性</label>
	        <div class="layui-input-block">
	            <select id="cdsx">
	                <option value="1">功能菜单(1)</option>
	                <option value="0">目录菜单(0)</option>
	            </select>
	        </div>
        </div>
    </div>
   <!--  <div class="layui-form-item">
        
    </div> -->
    <!-- <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block" style="text-align: center;">
            <input id="selectCdtb" type="hidden">
            <img id="selectCdtbImg" style="width: 53px;height: 53px;background-color: #009688;cursor: pointer;"/>点击图标更换
        </div>
    </div> -->
</div>
<div style="text-align: center;margin-top:50px;">
    <button id="save" class="layui-btn">保存</button>
    <button id="cancel" class="layui-btn">取消</button>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="static/js/vsconfig/business_menu_eidt.js"></script>
</body>
</html>