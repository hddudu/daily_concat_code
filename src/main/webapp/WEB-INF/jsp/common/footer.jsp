<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="static/jquery/jquery-3.3.1.min.js"></script>
<script src="static/layui/layui.js"></script>
<script>
    var layer,form,element,table;
    layui.use(['layer', 'form', 'element', 'table'], function () {
        layer = layui.layer;
        form = layui.form;
        element = layui.element;
        table = layui.table;
    });
</script>