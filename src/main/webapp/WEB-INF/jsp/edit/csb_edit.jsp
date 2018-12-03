<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>编辑参数表</title>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body  style="margin-top: 20px;margin-left: 30px;" >
<form class="layui-form" id="form">

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
<script src="static/js/vsconfig/csb_edit.js"></script>
</body>
</html>