<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body>
<div class="layui-form" style="margin: 0px 20px 0px 0px;">
    <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
            <input id="cdmc" type="text" class="layui-input"/>
            <input id="cdid" type="hidden" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">业务分类</label>
        <div class="layui-input-block">
            <select id="ywfldm">
                <option value="1">申报缴税</option>
                <option value="2">事项办理</option>
                <option value="3">办税服务</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">搜索关键字</label>
        <div class="layui-input-block">
            <input id="zdygjz" type="text" placeholder="输入拼音、拼音简写等关键字，多个以分号;（英文分号）分隔" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排序权重</label>
        <div class="layui-input-block">
            <input id="pmdf" type="text" placeholder="权重值越大搜索结果排序越靠前" onkeyup="value=value.replace(/[^\d]/g,'')"
                   maxlength="3" class="layui-input"/>
        </div>
    </div>

	<div class="layui-form-item">
		<label class="layui-form-label">是否推荐</label>
		<div class="layui-input-block">
			<select id="tjbz">
				<option value="N">否</option>
				<option value="Y">是</option>
			</select>
		</div>
	</div>
</div>
<div style="text-align: center;">
    <button id="save" class="layui-btn">保存</button>
    <button id="cancel" class="layui-btn">取消</button>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="static/js/vsconfig/qjss_menu_eidt.js"></script>
</body>
</html>