<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>编辑配置项</title>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body  style="margin-top: 20px;margin-left: 30px;" >
<form class="layui-form" id="form">
    <div class="layui-form-item" style="width:600px">
        <label class="layui-form-label">模块</label>
        <div class="layui-input-block">
            <input id="module" type="text" class="layui-input" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item" style="width:600px">
        <label class="layui-form-label">配置项名称</label>
        <div class="layui-input-block">
            <input id="key" type="text" class="layui-input" disabled="disabled">
        </div>
    </div>
     <div class="layui-form-item" style="width:600px">
        <label class="layui-form-label">值类型</label>
        <div class="layui-input-block">
            <input id=valueType type="text" class="layui-input" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item" style="width:600px">
        <label class="layui-form-label">配置值</label>
        <div class="layui-input-block" id="valueDiv">
            <input id="value" type="text" placeholder="请输入值" class="layui-input">
        </div>
        <div class="layui-input-block" id="valueSelectDiv">
            <select id="valueSelect"></select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text" style="width:600px">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea id="desc" placeholder="请输入描述信息" class="layui-textarea" disabled="disabled"></textarea>
        </div>
    </div>
</form>
 <div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-xs4 layui-col-xs-offset5">
            <button class="layui-btn" id="save">保存</button>
            <button class="layui-btn" id="cancel">取消</button>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="static/js/vsconfig/conf_edit.js"></script>
</body>
</html>