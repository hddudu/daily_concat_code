<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="static/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="static/zTree_v3/js/jquery.ztree.core.js"></script>
<div class="layui-fluid">
    <div id="treeDiv" class="layui-col-xs3">
        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree showIcon"></ul>
        </div>
    </div>
    <div class="layui-col-xs9">
        <div class="layui-col-xs12" style="margin-top: 10px;">
            <div class="layui-col-xs2">
                <button id="new" class="layui-btn">新增</button>
                <span style="color: white;">a</span>
            </div>
            <div class="layui-col-xs4">
                <form class="layui-form">
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <input type="radio" lay-filter="redioFilter" name="syfw" value="1" title="企业" checked>
                            <input type="radio" lay-filter="redioFilter" name="syfw" value="2" title="个人">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="layui-col-xs12" style="margin-top: -10px;margin-right: 10px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
</div>
<script src="static/js/vsconfig/index_menu_content.js"></script>