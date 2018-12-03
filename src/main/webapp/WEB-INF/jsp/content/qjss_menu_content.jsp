<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="static/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="static/zTree_v3/js/jquery.ztree.core.js"></script>
<!-- <script type="text/javascript" src="static/zTree_v3/js/fuzzysearch.js"></script>
<script type="text/javascript" src="static/zTree_v3/js/jquery.ztree.exhide.js"></script> -->
<div class="layui-fluid" style="margin-top: 10px;">
    <div id="treeDiv" class="layui-col-xs3">
        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree showIcon"></ul>
        </div>
        <div class="zTreeDemoBackground left">
            <ul id="treeDemoGr" class="ztree showIcon"></ul>
        </div>
    </div>
    <div class="layui-col-xs9">
        <div class="layui-col-xs12">
	        <div class="layui-row" id ="tool" style="visibility:hidden;">
	        	<div class="layui-col-xs3">
	            	<input id="cdmc" type="text" class="layui-input" placeholder="请输入菜单名称查询"/>
	            </div>
	            <div class="layui-col-xs3"  style="margin-left: 3px;">
	            	<input id="zdygjz" type="text" class="layui-input" placeholder="请输入搜索关键字查询"/>
	            </div>
	            <div class="layui-col-xs3" style="margin-left: 3px;">
		            <button id="search" class="layui-btn">查询</button>
	            </div>
	        </div>
        </div>
        <div class="layui-col-xs12" style="margin-top: 8px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
</div>
<script src="static/js/vsconfig/qjss_menu_content.js"></script>