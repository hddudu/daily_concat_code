<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layui-fluid" style="margin-top: 10px;">
    <div class="layui-row layui-col-space3">
        <div class="layui-col-xs4" style="width:500px;">
            <form class="layui-form">
                <select id="t_name" lay-verify="required" lay-filter="changeTable"></select>
            </form>
        </div>
        <div class="layui-col-xs2" style="width:200px;">
            <input id="key" type="text" placeholder="请输入搜索关键字" class="layui-input"/>
        </div>
        <div class="layui-col-xs2">
            <button id="search" class="layui-btn layui-btn-primary">查询</button>
            <button id="add" class="layui-btn">新增</button>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs12" style="margin-top: 8px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
    <script src="static/js/vsconfig/csb_content.js"></script>
</div>