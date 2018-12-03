var action = 'new';
var tableObj;
$(function () {
    fillTable();
    $('#insert').click(function () {
        action = 'new';
        layer.open({
            type: 2,
            title: '新增功能',
            area: ['600px', '430px'],
            shade: 0.2,
            maxmin: true,
            content: './gnpzEdit',
            end: function () {
            }
        });
    });
    $('#select').click(function () {
        fillTable($('#gnDm').val(), $('#gnmc').val());
    });

});
/**
 * 表格填充
 */
function fillTable(a, b) {
    tableObj = table.render({
        elem: '#table',
        height: $(window).height() - 176,
        url: './selectGnByConditon',
        where: {
            "gnDm": a,
            "gnmc": b
        },
        loading: true,
        page: true,
        method: "post",
        limit: 20,
        limits: [10, 20, 30],
        cols: [[{
            title: '序号',
            type: 'numbers',
            align: 'center',
            width: '5%'
        }, {
            title: '功能代码',
            field: 'gnDm',
            width: '16%'
        }, {
            title: '功能名称',
            field: 'gnmc',
            width: '23%'
        }, {
            title: '功能URL',
            field: 'gnurl',
            width: '30%'
        }, {
            title: '启用标志',
            align: 'center',
            field: 'tyBz',
            width: '8%',
            templet: function (e) {
                if (e.tyBz == 'Y') {
                    return '<div>否<div>';
                } else if (e.tyBz == 'N') {
                    return '<div>是<div>';
                }
            }
        }, {
            title: '操作',
            field: 'module',
            align: 'center',
            width: '16%',
            toolbar:"#barDemo"
        }]],
        done: function (res, curr, count) {
            $(".layui-table-box").css("width", "100%");
        }
    });
    table.on('tool(filter)', function (obj) {
        dataTran = obj.data;
        if ('edit' == obj.event) {
            action = 'edit';
            layer.open({
                type: 2,
                title: '修改功能',
                area: ['600px', '430px'],
                shade: 0.2,
                maxmin: true,
                content: './gnpzEdit',
                end: function () {
                }
            });
        } else if ('delete' == obj.event) {
            layer.confirm('确定要删除功能吗？', {icon: 7, title: '温馨提示'}, function (index) {
                layer.close(index);
                $.ajax({
                    type: "POST",
                    url: "./deleteGN",
                    dataType: "json",
                    data: {
                        'gnDm': dataTran.gnDm
                    },
                    success: function (data) {
                        if (data.code == '1') {
                            layer.alert(data.msg);
                        } else {
                            obj.del();
                        }
                        layer.msg(data.msg);
                    },
                    error: function (data) {
                        layer.alert(data);
                    }
                });

            });
        }
    });
}
