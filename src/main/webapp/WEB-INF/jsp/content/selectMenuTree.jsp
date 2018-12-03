<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
    <link rel="stylesheet" href="static/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
</head>
<body>
<div class="layui-container">
<%--    <div class="layui-col-xs11">
        <button id="sure" class="layui-btn">确认</button>
    </div>--%>
    <div class="layui-col-xs11">
        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree showIcon"></ul>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script type="text/javascript" src="static/zTree_v3/js/jquery.ztree.core.js"></script>
<script src="static/js/vsconfig/selectMenuTree.js"></script>
</body>
</html>