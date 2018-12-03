<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="static/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="static/zTree_v3/js/jquery.ztree.core.js"></script>
<div class="layui-fluid" style="margin-top: 10px;">
    <div id="treeDiv" class="layui-col-xs3">
        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree showIcon"></ul>
        </div>
        <div class="zTreeDemoBackground left">
            <ul id="treeDemoGr" class="ztree showIcon"></ul>
        </div>
        <div class="zTreeDemoBackground left">
            <ul id="treeDemoDlq" class="ztree showIcon"></ul>
        </div>
    </div>
    <div class="layui-col-xs9">
        <div class="layui-col-xs12">
            <button id="new" class="layui-btn">新增</button>
            <button id="clearSycdCache" class="layui-btn">清除首页菜单缓存</button>
            <button class="layui-btn" style="visibility:hidden;">占位</button>
        </div>
        <div class="layui-col-xs12" style="margin-top: 8px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
</div>
<script src="static/js/vsconfig/business_menu_content.js"></script>
	<script type="text/html" id="barDemo">
	<div>
	<button class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</button>
	<button class="layui-btn layui-btn-danger layui-btn-xs" style="border:1px solid #FF5722;margin-left:10px;" lay-event="delete">删除</button>
	</div>
</script>