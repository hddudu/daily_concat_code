var selectNode = null;
var tableObj;
var action = 'edit';
var dataTran = null;
var syfw;
var nodeArr =['qyroot','grroot','dlqroot','qysycd','grsycd','dlqsycd','grywcd','dlqywcd','qysycd-l','qysycd-r','grsycd-l','grsycd-r','dlqsycd-l','dlqsycd-r'];
$(function () {
    $('#new').hide();
    $('#clearSycdCache').hide();
    $("#treeDiv").css({"overflow-y": "auto", "height": $(window).height() - 110});
    initSelectMenu(1, 'treeDemo');
    initSelectMenu(2, 'treeDemoGr');
    initSelectMenu(0, 'treeDemoDlq');
    $("#new").click(function () {
        if (selectNode == null) {
            layer.msg("请选择菜单！");
            return;
        }
        action = 'new';
        layer.open({
            type: 2,
            title: '新增',
            area: ['708px', '464px'],
            shade: 0.2,
            content: './editBusinessMenu',
            end: function () {
            }
        });
    });
    $("#clearSycdCache").click(function () {
        $.post("./clearSycdCache",function(data){
        	layer.msg(data.msg);
        });
    });
    var form = null;
    layui.use(['form'], function () {
        form = layui.form;
        form.render();
    });
});
/**
 * 获取表格数据
 */
function getSelectBusinessTableData() {
    var cdid = null;
    if (selectNode != null) {
        cdid = selectNode.cdid;
    }

    tableObj = table.render({
        elem: '#table',
        height: $(window).height() - 180,
        url: './getBusinessTableData',
        limit: 100,
        loading: true,
        where: {
            "cdid": cdid
        },
        initSort: {
            field: 'xh',
            type: 'asc'
        },
        cols: [[{
            title: '序号',
            width: '5%',
            type: 'numbers',
            align: 'center'
        }, {
            title: '菜单名称',
            width: '47%',
            field: 'cdmc'
        }, /*{
            title: '父菜单',
            field: 'fcd',
            width: '15%',
            templet: function () {
                if (selectNode != null) {
                    return '<div>' + selectNode.cdmc + '</div>';
                } else {
                    return '<div>根目录</div>';
                }
            }
        },*/ {
            title: '菜单顺序',
            field: 'xh',
            align: 'center',
            width: '12%',
        }, {
            title: '启用标志',
            field: 'tyBz',
            align: 'center',
            width: '12%',
            templet: function (data) {
                if (data.tyBz == 'Y') {
                    return '<div>否</div>';
                } else {
                    return '<div>是</div>';
                }
            }
        }, {
            title: '操作',
            align: "center",
            width: '22%',
            toolbar:"#barDemo"
        }]],
        done: function (res, curr, count) {
            $(".layui-table-box").css("width", "100%");
        }
    });
    table.on('tool(filter)', function (obj) {
        dataTran = obj.data;
        action = 'edit';
        if ('edit' == obj.event) {
            layer.open({
                type: 2,
                title: '修改',
                area: ['708px', '464px'],
                shade: 0.2,
                content: './editBusinessMenu',
                end: function () {
                }
            });
        } else if ('delete' == obj.event) {
            layer.confirm('确定要删除菜单吗？', {icon: 7, title: '温馨提示'}, function (index) {
                layer.close(index);
                $.ajax({
                    type: "GET",
                    url: "./deleteBusinessMenu",
                    dataType: "json",
                    data: {
                        "cdid": dataTran.cdid
                    },
                    success: function (data) {
                        if (data.code == '0') {
                            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                            var nodes = selectNode.children;
                            if (nodes.length > 0) {
                                for (var i = 0; i < nodes.length; i++) {
                                    if (nodes[i].cdid == dataTran.cdid) {
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
 * 菜单树初始化
 */
function initSelectMenu(syfw, treeId) {
    $.ajax({
        type: "GET",
        url: "./getSelectMenuTree",
        dataType: "json",
        data: {
            'syfw': syfw
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
                        enable: true,
                        idKey: "cdid",
                        pIdKey: "fcdid"
                    },
                    key: {
                        name: "cdmc"
                    }
                },
                callback: {
                    beforeClick: beforeClick
                }
            };
            if (syfw == '1') {
                setting.callback.beforeClick = beforeClick1;
            } else if(syfw == '2') {
                setting.callback.beforeClick = beforeClick2;
            }else if(syfw == '0'){
            	setting.callback.beforeClick = beforeClick;
            }
            $.fn.zTree.init($("#" + treeId), setting, data);
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodeList = treeObj.getNodes();
            treeObj.expandNode(nodeList[0], true);
        },
        error: function (data) {
            layer.alert(data);
        }
    });
}
/**
 * 菜单树选择事件
 * @param treeId
 * @param treeNode
 * @returns {boolean}
 */
function beforeClick(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    //zTree.expandNode(treeNode);
    selectNode = treeNode;
    if($.inArray(treeNode.cdid,nodeArr)>-1){
    	$('#new').hide();
    }else{
    	$('#new').show();
    }
    $('#clearSycdCache').show();
    syfw = '0';
    getSelectBusinessTableData();
    return true;
}
function beforeClick1(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    //zTree.expandNode(treeNode);
    selectNode = treeNode;
    if($.inArray(treeNode.cdid,nodeArr)>-1){
    	$('#new').hide();
    }else{
    	$('#new').show();
    }
    $('#clearSycdCache').show();
    syfw = '1';
    getSelectBusinessTableData();
    return true;
}
function beforeClick2(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    //zTree.expandNode(treeNode);
    selectNode = treeNode;
    if($.inArray(treeNode.cdid,nodeArr)>-1){
    	$('#new').hide();
    }else{
    	$('#new').show();
    }
    $('#clearSycdCache').show();
    syfw = '2';
    getSelectBusinessTableData();
    return true;
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
            if (nodes[i].cdid == newNode.cdid) {
                nodes[i].cdmc = newNode.cdmc;
                nodes[i].cdsx = newNode.cdsx;
                nodes[i].gnDm = newNode.gnDm;
                nodes[i].fcdid = newNode.fcdid;
                nodes[i].xh = newNode.xh;
                nodes[i].cdjs = newNode.cdjs;
                nodes[i].tyBz = newNode.tyBz;
                nodes[i].swjgDm = newNode.swjgDm;
                nodes[i].content = newNode.content;
                treeObj.updateNode(nodes[i]);
            }
        }
    }
}