<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layui-fluid" style="margin-top: 10px;">
    <div class="layui-row layui-col-space3">
        <div class="layui-col-xs4" style="width:25%;">
            <form class="layui-form">
                <select id="t_name" lay-verify="required" lay-filter="changeTable"></select>
            </form>
        </div>
        <div class="layui-col-xs2" style="width:19%;">
            <input id="key" type="text" placeholder="请输入搜索关键字" class="layui-input"/>
        </div>
        <div class="layui-col-xs2"  style="width:6%">
            <button id="search" class="layui-btn layui-btn-primary" style="width:100%">查询</button>
        </div>
        <div class="layui-col-xs2"  style="width:6%">
        	<button id="add" class="layui-btn" style="width:100%">新增</button>
        </div>
        <div class="layui-col-xs2"  style="width:6%;" id="tbDiv">
        	<button id="tb" class="layui-btn" style="width:100%">同步</button>
        </div>
        <div class="layui-col-xs2"  style="width:10%">
         	<button id="downloadExcel" class="layui-btn"  style="width:100%">下载导入模板</button>
        </div>
        <div class="layui-col-xs2"  style="width:6%">
       		<button type="button" class="layui-btn" id="uploadExcel"  style="width:100%"><i class="layui-icon"></i>导入</button>
        </div> 
    </div>
    <div class="layui-row">
        <div class="layui-col-xs12" style="margin-top: 8px;">
            <table id="table" lay-filter="filter"></table>
        </div>
    </div>
    <div id="sjbj" style="display:none">
	    <div name="banner" wei-yi  class="reveal-modal1" style="width:1000px;text-align:center">			
			<table id="process-demo-1" class="layui-table marginB16">
			    <colgroup>
			      <col width="180px">
				  <col>
				  <col width="180px">
				  <col>
			    </colgroup>
			    <tbody id="sjbjTbody">
			      
			    </tbody>
			  </table>
			  <div class="layui-col-xs2" style="margin-top:5px;width:100%">
		         <button id="fg" class="layui-btn" onclick="fg()">覆盖</button>
		         <button id="qx" class="layui-btn" onclick="qx()">取消</button>
		      </div> 
		</div>
	</div>
    <script src="static/js/vsconfig/dmb_content.js"></script>
</div>