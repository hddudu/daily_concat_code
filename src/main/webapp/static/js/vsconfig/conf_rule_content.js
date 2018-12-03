var dataTran;
var tableIns;
$(function () {
    $("#search").click(function () {
        fillTable();
    });
    $("#reload").click(function () {
        reloadTable();
    });
    $("#addRule").click(function () {
        layer.alert("fdgfd");
    });
});
function fillTable() {
    tableIns = table.render({
        elem: '#table',
        height: $(window).height() - 162,
        url: './data',
        where: {
            tableName: "conf_rule",
            module: $("#module").val(),
            key: $("#key").val()
        },
        limit: 100000000,
        loading: true,
        cols: [[{
            title: '序号',
            type: 'numbers',
        }, {
            title: '模块名',
            field: 'module',
        }, {
            field: 'key',
            title: '配置项配名称',
        }, {
            title: '操作',
            align: "center",
            templet: '<div><button class="layui-btn layui-btn-sm" lay-event="edit">修改</button></div>'
        }]],
        done: function (res, curr, count) {
            $(".layui-table-box").css("width","100%");
        }
    });
    table.on('tool(filter)', function (obj) {
        dataTran = obj.data;
        if ('edit' == obj.event) {
            layer.open({
                type: 2,
                title: '编辑配置项',
                area: ['700px', '458px'],
                shade: 0.2,
                maxmin: true,
                content: './conf_edit'
            });
        }
        /*
         * var data = obj.data; if (obj.event === 'setValue') {
         * layer.prompt({ formType : 2, title : '修改', value : data.value },
         * function(value, index) { layer.close(index); // 这里一般是发送修改的Ajax请求 //
         * 同步更新表格和缓存对应的值 obj.update({ value : value }); }); }
         */
    });
}
function reloadTable() {
    tableIns.reload();
}