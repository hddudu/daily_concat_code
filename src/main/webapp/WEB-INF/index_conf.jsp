<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>可视化配置中心</title>
<head>
    <%@include file="/WEB-INF/jsp/common/header.jsp" %>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <a href="./"><strong style="font-size: 30px;color: #0c9368">配置中心</strong></a>
        </div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a>文档</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="http://www.treejs.cn/v3/main.php#_zTreeInfo" target="zTree">zTree</a>
                    </dd>
                    <dd>
                        <a href="http://www.layui.com/doc/" target="LayUI doc">LayUI doc</a>
                    </dd>
                    <dd>
                        <a href="http://www.layui.com/demo/" target="LayUI demo">LayUI demo</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a>工具</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="https://www.json.cn/" target="JSON1">JSON格式化工具一</a>
                    </dd>
                    <dd>
                        <a href="https://www.sojson.com/" target="JSON2">JSON格式化工具二</a>
                    </dd>
                    <dd>
                        <a href="http://www.css88.com/tool/html2js/" target="html2js">字符串转换工具</a>
                    </dd>
                </dl>
            </li>
        </ul>
    </div>

    <%--main content--%>
    <div id="main_content">
    </div>


    <div class="layui-footer">
        <div class="layui-fluid">
            <div class="layui-row">
                <div class="layui-col-md4 layui-col-md-offset5">
                    <label>© foresee 1998-2018</label>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
<script src="static/js/vsconfig/index_conf.js"></script>
</body>
</html>
