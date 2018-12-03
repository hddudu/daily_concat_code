var selectNode = null;
var syfw = '1';//试用范围（1企业2个人）
var tableObj;
var dataTran = null;
var action = 'new';
$(function () {
    $("#new").hide();
    $("#treeDiv").css({"overflow-y": "auto", "height": $(window).height() - 110});
    getMenuTree();
    loadTable("0");//表格默认加载一级菜单
    $("#new").click(function () {
        action = 'new';
        if (selectNode.id == 1 || selectNode.id == 2 || selectNode.id == 3) {//一级菜单不予新增
            layer.msg("一级菜单不予新增");
        }
        if (selectNode == null) {
            layer.msg("请选择菜单！");
            return;
        }
        layer.open({
            type: 2,
            title: '新增',
            area: ['600px', '600px'],
            shade: 0.2,
            maxmin: true,
            content: './indexMenuEdit?pid=' + selectNode.id,
            end: function () {
            }
        });
    });
    form.render();//必须渲染，要不layui radio样式出不来
    //redio button选中事件
    form.on('radio(redioFilter)', function (data) {
        selectNode = null;
        syfw = data.value;
        getMenuTree();
        loadTable("0");
    });
});
/**
 * 菜单树点击事件
 * @param treeId
 * @param treeNode
 * @returns {boolean}
 */
function beforeClick(treeId, treeNode) {
    selectNode = treeNode;
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    // zTree.expandNode(treeNode);
    if (selectNode == null) {
        $("#new").hide();
    } else {
        if (selectNode.id == 1 || selectNode.id == 2 || selectNode.id == 3) {//一级菜单不予新增
            $("#new").hide();
        } else {
            $("#new").show();
        }
    }
    loadTable(selectNode.id);
    return true;
}
/**
 * 获取用户菜单信息并渲染树结构
 */
function getMenuTree() {
    $.ajax({
        type: "POST",
        url: "./getMenuTree",
        dataType: "json",
        data: {
            "syfw": syfw
        },
        success: function (data) {
            var setting = {
                view: {
                    showLine: false,
                    selectedMulti: false,
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    beforeClick: beforeClick
                }
            };
            $.fn.zTree.init($("#treeDemo"), setting, data);
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            treeObj.expandAll(true);
        }
    });
}
/**
 * 加载数据表格
 * @param pid 父id
 */
function loadTable(pid) {
    tableObj = table.render({
        elem: '#table',
        height: $(window).height() - 200,
        url: './getSelectMenuTableData',
        limit: 100,
        loading: true,
        where: {
            "pid": pid,
            "syfw": syfw
        },
        cols: [[{title: '序号', type: 'numbers'},
            {title: '菜单名称', field: 'name', align: 'center'},
            {
                title: '父菜单', field: 'key', align: "center", templet: function () {
                if (selectNode != null) {
                    return '<div>' + selectNode.name + '</div>';
                } else {
                    return "";
                }
            }
            },
            {
                title: '适用范围', field: 'syfw', align: 'center', templet: function () {
                if (syfw == '1') {
                    return '<div>企业</div>';
                } else {
                    return '<div>个人</div>';
                }
            }
            },
            {title: '启用标志', field: 'qybz', align: 'center', templet: '<div>是</div>'},
            {
                title: '操作', align: "center", templet: function () {
                var deleteButton = '<button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">删除</button>';
                var editButton = '<button class="layui-btn layui-btn-sm" lay-event="edit">修改</button>';
                if (selectNode == null) {
                    return '';
                } else {
                    if (selectNode.id == 1 || selectNode.id == 2 || selectNode.id == 3) {
                        return '<div>' + editButton + '</div>';
                    } else {
                        return '<div>' + editButton + deleteButton + '</div>';
                    }
                }
            }
            }]],
        done: function (res, curr, count) {
            $(".layui-table-box").css("width", "100%");
        }
    });
    table.on('tool(filter)', function (obj) {
        action = 'edit';
        dataTran = obj.data;
        if ('edit' == obj.event) {
            layer.open({
                type: 2,
                title: '修改',
                area: ['600px', '600px'],
                shade: 0.2,
                maxmin: true,
                content: './indexMenuEdit',
                end: function () {
                }
            });
        } else if ('delete' == obj.event) {
            layer.confirm('确定要删除菜单吗？', {icon: 7, title: '温馨提示'}, function (index) {
                layer.close(index);
                $.ajax({
                    type: "GET",
                    url: "./deleteIndexMenu",
                    dataType: "json",
                    data: {
                        "id": dataTran.id,
                        "syfw": syfw
                    },
                    success: function (data) {
                        if (data.code == '0') {
                            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                            var nodes = selectNode.children;
                            if (nodes.length > 0) {
                                for (var i = 0; i < nodes.length; i++) {
                                    if (nodes[i].id == dataTran.id) {
                                        treeObj.removeNode(nodes[i]);
                                    }
                                }
                            }
                            obj.del();
                        }
                        layer.msg(data.msg);
                    }
                });

            });
        }
    });
}

/**
 * 新增时调用新增树节点
 * @param newNode
 */
function addNode(newNode) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    treeObj.addNodes(selectNode, -1, newNode, true);
}
/**
 * 更新节点树
 * @param newNode
 */
function refreshTree(newNode) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = selectNode.children;
    if (nodes.length > 0) {
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].id == newNode.id) {
                nodes[i].pId = newNode.pId;
                nodes[i].cdid = newNode.cdid;
                nodes[i].name = newNode.name;
                nodes[i].syfw = newNode.syfw;
                nodes[i].cdtb = newNode.cdtb;
                nodes[i].dkfs = newNode.dkfs;
                nodes[i].cdurl = newNode.cdurl;
                nodes[i].xh = newNode.xh;
                treeObj.updateNode(nodes[i]);
            }
        }
    }
}