<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body style="margin: 2px;">
<input id="pid" value="${pid}" type="hidden"/>
<form class="layui-form layui-form-pane">
    <div class="layui-form-item">
        <label class="layui-form-label">父菜单名称</label>
        <div class="layui-input-block">
            <input id="fcdmc" type="text" style="color: red;" disabled="disabled" placeholder="父菜单名称"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
            <input id="menu_name" type="text" placeholder="请输入菜单名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单ID</label>
        <div class="layui-input-block">
            <input id="cdid" type="text" placeholder="请选择菜单ID" class="layui-input" style="cursor: pointer;">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单URL</label>
        <div class="layui-input-block">
            <input id="url" type="text" placeholder="请输入菜单URL" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">打开方式</label>
        <div class="layui-input-block">
            <select id="dkfs">
                <option value="0">本页跳转打开</option>
                <option value="1">从旧门户页面打开</option>
                <option value="2">新页面打开</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单顺序</label>
        <div class="layui-input-block">
            <input id="cdsx" type="text" placeholder="请输入菜单顺序（数字）" onkeyup="value=value.replace(/[^\d]/g,'')"
                   maxlength="3" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-block" style="text-align: center;">
            <input id="selectCdtb" type="hidden">
            <img id="selectCdtbImg" style="width: 53px;height: 53px;background-color: #009688;cursor: pointer;"/>点击图标更换
        </div>
    </div>
</form>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-xs5 layui-col-xs-offset3">
            <button class="layui-btn" id="save">保存</button>
        </div>
        <div class="layui-col-xs2">
            <button class="layui-btn" id="cancel">取消</button>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="static/js/vsconfig/index_menu_edit.js"></script>
</body>
</html>