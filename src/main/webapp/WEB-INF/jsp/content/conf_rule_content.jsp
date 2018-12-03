<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layui-container" style="margin: 3px;">
    <div class="layui-row layui-col-space3">
        <div class="layui-col-xs2">
            <input id="module" type="text" placeholder="请输入模块名称"
                   class="layui-input"/>
        </div>
        <div class="layui-col-xs2">
            <input id="key" type="text" placeholder="请输入配置项名称" class="layui-input"/>
        </div>
        <div class="layui-col-xs3">
            <button id="search" class="layui-btn">查询</button>
            <button id="addRule" class="layui-btn">新增</button>
            <button id="reload" class="layui-btn">reload</button>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs12" style="margin-top: -8px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
    <script src="static/js/vsconfig/conf_rule_content.js"></script>
</div>