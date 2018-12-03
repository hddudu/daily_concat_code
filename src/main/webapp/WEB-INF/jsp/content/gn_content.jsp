<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layui-fluid" style="margin-top: 10px;">
    <div class="layui-row layui-col-space3">
        <div class="layui-col-xs12">
            <div class="layui-row">
                <div class="layui-col-xs2">
                    <input id="gnDm" type="text" class="layui-input" placeholder="请输入功能代码模糊查询"/>
                </div>
                <div class="layui-col-xs2"  style="margin-left: 3px;">
                    <input id="gnmc" type="text" class="layui-input" placeholder="请输入功能名称模糊查询"/>
                </div>
                <div class="layui-col-xs2" style="margin-left: 3px;">
                    <button id="select" class="layui-btn">查询</button>
                    <button id="insert" class="layui-btn">新增</button>
                </div>
            </div>
        </div>
        <div class="layui-col-xs12" style="margin-top: 8px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
</div>
<script src="static/js/vsconfig/gn_content.js"></script>
<script type="text/html" id="barDemo">
	<div>
	<button class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</button>
	<button class="layui-btn layui-btn-danger layui-btn-xs" style="border:1px solid #FF5722;margin-left:10px;" lay-event="delete">删除</button>
	</div>
</script>