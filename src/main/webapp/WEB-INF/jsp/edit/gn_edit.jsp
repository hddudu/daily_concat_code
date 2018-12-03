<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>编辑配置项</title>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
    <%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</head>
<body style="margin: 2px;">
<form class="layui-form">
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs12">
            <label class="layui-form-label">功能代码</label>
            <div class="layui-input-block">
                <input id="gnDm" type="text" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs12">
            <label class="layui-form-label">功能名称</label>
            <div class="layui-input-block">
                <input id="gnmc" type="text" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs12">
            <label class="layui-form-label">功能URL</label>
            <div class="layui-input-block">
                <input id="gnurl" type="text" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">业务属性</label>
            <div class="layui-input-block">
                <select id="gdsywsx">
                    <option value="0">联合办税户专用菜单(单边户不适用)(0)</option>
                    <option value="1">国税专用(1)</option>
                    <option value="2">地税专用(2)</option>
                    <option value="3" selected = "selected">通用菜单(3)</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">权限控制</label>
            <div class="layui-input-block">
                <select id="qxkzsx">
                    <option value=""></option>
                    <option value="0">不需要登录</option>
                    <option value="1">不需要权限控制</option>
                    <option value="2">需要权限控制</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">功能集类型</label>
            <div class="layui-input-block">
                <select id="gnjlx">
                    <option value=""></option>
                    <option value="0">系统类</option>
                    <option value="1">业务类</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">实名标志</label>
            <div class="layui-input-block">
                <select id="smbsBz">
                    <option value=""></option>
                    <option value="N">不实名</option>
                    <option value="Y">实名</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">打开方式</label>
            <div class="layui-input-block">
                <select id="dkfs">
                    <option value=""></option>
                    <option value="_blank">新窗口</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">是否启用</label>
            <div class="layui-input-block">
                <select id="tyBz">
                    <option value="N">启用</option>
                    <option value="Y">不启用</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs12">
            <label class="layui-form-label">菜单图标</label>
            <div class="layui-input-block" style="text-align: center;">
                <input id="cdtb" type="text" class="layui-input">
            </div>
        </div>
    </div>
</form>

<div class="layui-row">
    <div class="layui-col-xs4 layui-col-xs-offset5">
        <button class="layui-btn" id="save">保存</button>
        <button class="layui-btn" id="cancel">取消</button>
    </div>
</div>
<script src="static/js/vsconfig/gn_edit.js"></script>
</body>
</html>