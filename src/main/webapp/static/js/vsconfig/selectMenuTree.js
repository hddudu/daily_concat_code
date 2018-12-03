$(function () {
    initSelectMenu();
    $("#sure").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
});
/**
 * 菜单树初始化
 */
function initSelectMenu() {
    $.ajax({
        type: "POST",
        url: "./getSelectMenuTree",
        dataType: "json",
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
                        pIdKey: "fcdid",
                        rootPId: "0"
                    },
                    key: {
                        name: "cdmc"
                    }
                },
                callback: {
                    beforeClick: beforeClick
                }
            };
            $.fn.zTree.init($("#treeDemo"), setting, data);
            /**
             * 展开对应节点
             */
            var zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
            var node = zTree_Menu.getNodeByParam("cdid", parent.window.frames[0].getCdid());
            zTree_Menu.selectNode(node, true);
            zTree_Menu.expandNode(node, true, false)
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
    zTree.expandNode(treeNode);
    parent.window.frames[0].onCdidChanged(treeNode.cdmc, treeNode.cdid);
    layer.msg('您选择了 “' + treeNode.cdmc + '”');
    return true;
}