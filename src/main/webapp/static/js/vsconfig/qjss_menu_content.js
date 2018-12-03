var selectNode = null;
var tableObj;
var action = 'edit';
var dataTran = null;
var syfw;
$(function () {
    $("#treeDiv").css({"overflow-y": "auto", "height": $(window).height() - 110});
    initSelectMenu(1, 'treeDemo');
    initSelectMenu(2, 'treeDemoGr');
    
    //根据菜单名称或者自定义关键字搜索
    $("#search").click(function () {
    	//搜索框内容
    	var cdmc = $("#cdmc").val();
    	var zdygjz = $("#zdygjz").val();
    	getSelectBusinessTableData(cdmc,zdygjz);
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
function getSelectBusinessTableData(cdmcValue,zdygjzValue) {
	var cdmc = "";//菜单名称
	var zdygjz = "";//自定义关键字
	if(typeof cdmcValue != "undefined" && cdmcValue != null && cdmcValue != ""){
        cdmc = cdmcValue;
    }else{
    	$("#cdmc").val("");//如果不是查询进来的，重置搜索输入框
    }
	if(typeof zdygjzValue != "undefined" && zdygjzValue != null && zdygjzValue != ""){
		zdygjz = zdygjzValue;
    }else{
    	$("#zdygjz").val("");//如果不是查询进来的，重置搜索输入框
    }
    var cdid = null;
    if (selectNode != null) {
        cdid = selectNode.cdid;
    }

    tableObj = table.render({
        elem: '#table',
        height: $(window).height() - 180,
        url: './getQjssTableData',
        limit: 100,
        loading: true,
        where: {
            "cdid": cdid
            ,"cdmc": encodeURI(encodeURI(cdmc))
            ,"zdygjz": encodeURI(encodeURI(zdygjz))
        },
        cols: [[{
            title: '序号',
            type: 'numbers',
            width: '5%'
        }, {
            title: '菜单名称',
            field: 'cdmc',
            width: '25%'
        }, {
            title: '搜索关键字',
            field: 'zdygjz',
            align: 'center',
            width: '34%',
            align: 'left',
        }, {
            title: '业务分类',
            field: 'ywfldm',
            align: 'center',
            width: '10%',
            templet: function (d) {
                if (d.ywfldm == '1') {
                    return '申报缴税';
                } else if(d.ywfldm == '2'){
                	return '事项办理';
                } else if(d.ywfldm == '3'){
                	return '办税服务';
                }
            }
        }, {
            title: '排序权重',
            field: 'pmdf',
            align: 'center',
            width: '10%'
        }, {
            title: '推荐',
            field: 'tjbz',
            align: 'center',
            width: '8%',
            templet: function (data) {
                if (data.tjbz == 'Y') {
                    return '<div>是</div>';
                } else {
                    return '<div>否</div>';
                }
            }
        },{
            title: '操作',
            align: "center",
            width: '8%',
            templet: function () {
                var editButton = '<button class="layui-btn layui-btn-sm" lay-event="edit">编辑</button>';
                return '<div>' + editButton + '</div>';
            }
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
                title: '编辑',
                area: ['550px', '450px'],
                shade: 0.2,
                content: './editQjssMenu',
                end: function () {
                }
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
        url: "./getQjssSelectMenuTree",
        dataType: "json",
        async: false,
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
                setting.callback.beforeClick = beforeClick;
            } else {
                setting.callback.beforeClick = beforeClick1;
            }
            $.fn.zTree.init($("#" + treeId), setting, data);
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            //过滤掉所有的叶子结点
            treeObj.expandAll(true);//展开所有节点
            var nodes = treeObj.transformToArray(treeObj.getNodes());
            for(var i=0;i<nodes.length;i++){
            	if(!nodes[i].isParent){
            		treeObj.removeNode(nodes[i]);
            	}
            }
            treeObj.expandAll(false);//关闭所有节点，展示第一层
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
    selectNode = treeNode;
    $("#tool").css("visibility","visible");
    syfw = '1';
    getSelectBusinessTableData();
    return true;
}
function beforeClick1(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId);
    selectNode = treeNode;
    $("#tool").css("visibility","visible");
    syfw = '2';
    getSelectBusinessTableData();
    return true;
}

function searchByCdmcOrZdygjz(){
	 var cdmc = $("#cdmc").val();
	 var zdygjz = $("#zdygjz").val();
}















