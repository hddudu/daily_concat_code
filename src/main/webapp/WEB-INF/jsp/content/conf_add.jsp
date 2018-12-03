<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>新增配置项</title>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body style="margin-top: 20px;margin-left: 30px;">
<form class="layui-form">
    <div class="layui-form-item" style="width:90%">
        <label class="layui-form-label" style="width:15%;">模块</label>
        <div class="layui-input-inline" style="width:27.5%;">
            <select id="module" ></select>
        </div>
        <label class="layui-form-label" style="width:15%;">配置项名称</label>
        <div class="layui-input-inline" style="width:27.5%;">
            <input id="key" type="text" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item" style="width:90%">
        <label class="layui-form-label" style="width:15%;">值类型</label>
        <div class="layui-input-inline" style="width:75%;">
            <select id="valueType" lay-filter="changeValueType">
                <option value="1" selected="selected">文本</option>
                <option value="2">数值</option>
                <option value="3">日期</option>
                <option value="4">选项</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" id="constraintDiv" style="width:90%;">
        <label class="layui-form-label" id="constraintName" style="width:15%;">取值约束</label>
        <div class="layui-input-inline" style="width:75%;">
             <input id="constraint" type="text" placeholder="请输入约束" class="layui-input">
             <span style="color:red;" id="wbsm">*若需要限制配置值格式，可自行输入正则表达式对配置值进行校验。</span>
             <span style="color:red;" id="xxsm">*"选项"类型需要配置取值约束。格式为JSON，例如:“[{"是":"true"},{"否":"false"}]”代表规定此配置项有“是”和“否”两个选项，分别对应“true”和“false”两个值。</span>
        </div>
    </div>
    <div class="layui-form-item" id="maxMinDiv" style="display:none;width:90%;">
        <label class="layui-form-label" style="width:15%;">取值范围</label>
        <div class="layui-input-inline" style="width:27.5%;">
             <input id="min" type="text" placeholder="请输入最小值" class="layui-input" oninput="value=value.replace(/[^\d.\d]/g,'')" onBlur="if(value!=''&&!/^[0-9]+.?[0-9]*$/.test(value)){value='';}">
        </div>
        <label class="layui-form-label label-left" style="text-align:center;margin-left:10px;width:15%;">-</label>
        <div class="layui-input-inline" style="width:25.5%;margin-left:10px;">
             <input id="max" type="text" placeholder="请输入最大值" class="layui-input" oninput="value=value.replace(/[^\d.\d]/g,'')" onBlur="if(value!=''&&!/^[0-9]+.?[0-9]*$/.test(value)){value='';}">
        </div>
    </div>
    <div class="layui-form-item" id="maxMinDateDiv" style="display:none;width:90%;">
        <label class="layui-form-label" style="width:15%;">日期范围</label>
        <div class="layui-input-inline" style="width:27.5%;">
             <input id="minDate" type="text" placeholder="请选择最早日期" class="layui-input layui-date-input"  readonly="true">
        </div>
        <label class="layui-form-label label-left" style="text-align:center;margin-left:10px;width:15%;">-</label>
        <div class="layui-input-inline" style="width:25.5%;margin-left:10px;">
             <input id="maxDate" type="text" placeholder="请输入最晚日期" class="layui-input layui-date-input"  readonly="true">
        </div>
    </div>
    <div class="layui-form-item" style="width:90%">
        <label class="layui-form-label"  style="width:15%;">配置值</label>
        <div class="layui-input-inline" id="valueDiv"  style="width:75%;">
            <input id="valueDate" type="text" class="layui-input layui-date-input" readonly="true" style="display:none;">
            <input id="value" type="text" placeholder="请输入值" class="layui-input">
        </div>
        <div class="layui-input-inline" id="valueSelectDiv" style="display:none;width:27.5%;">
            <select id="valueSelect"></select>
        </div>
    </div>
    <div class="layui-form-item" style="width:90%">
        <label class="layui-form-label" style="width:15%;">是否可更改</label>
        <div class="layui-input-inline" style="width:27.5%;">
            <select id="sfkgg" >
                <option value="Y" selected="selected">是</option>
                <option value="N">否</option>
            </select>
        </div>
        <label class="layui-form-label" style="width:15%;">是否可配置</label>
        <div class="layui-input-inline" style="width:27.5%;">
            <select id="sfkpz">
                <option value="Y" selected="selected">是</option>
                <option value="N">否</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item" style="width:90%">
        <label class="layui-form-label"  style="width:15%">是否必填</label>
        <div class="layui-input-inline" style="width:27.5%;">
            <select id="sfbt">
                <option value="Y" selected="selected">是</option>
                <option value="N">否</option>
            </select>
        </div>
        <label class="layui-form-label" style="width:15%;">是否需清缓存</label>
        <div class="layui-input-inline" style="width:27.5%;">
            <select id="sfqhc">
                <option value="Y" selected="selected">是</option>
                <option value="N">否</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text" style="width:90%">
        <label class="layui-form-label" style="width:15%;">描述</label>
        <div class="layui-input-inline" style="width:75%;">
            <textarea id="desc" placeholder="请输入描述信息" class="layui-textarea" style="height:150px;"></textarea>
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
<script src="static/js/vsconfig/conf_add.js"></script>
</body>
</html>