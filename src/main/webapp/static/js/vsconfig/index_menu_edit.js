var action = parent.action;
var selectNode = parent.selectNode;//父界面选中节点数据
var dataTran = parent.dataTran;//父界面选中表格行数据
$(function () {
    if (action == 'new') {
        $('#fcdmc').val(selectNode.name);
    } else if (action == 'edit') {
        initTableData();
    }
    $('#selectCdtbImg').click(function () {
        parent.layer.open({
            type: 2,
            title: '请选择菜单图标',
            area: ['500px', '550px'],
            shade: 0.2,
            maxmin: true,
            content: './menuIcon',
            end: function () {
            }
        });
    });
    $('#cdid').click(function () {
        parent.layer.open({
            type: 2,
            title: '请选择菜单',
            area: ['500px', '550px'],
            shade: 0.2,
            maxmin: true,
            content: './selectMenuTree',
            end: function () {
            }
        });
    });
    $("#cancel").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    $("#save").click(function () {
        if (action == 'new') {
            addMenuItem();
        } else if (action == 'edit') {
            editById();
        }
    });
});
/**
 * 根据父id新增菜单
 * @param pid
 */
function addMenuItem() {
    $.ajax({
        type: "POST",
        url: "./addIndexMenuItem",
        dataType: "json",
        data: {
            "pId": $("#pid").val(),
            "cdid": $("#cdid").val(),
            "name": $("#menu_name").val(),
            "syfw": parent.syfw,
            "cdtb": $("#selectCdtb").val(),
            "dkfs": $("#dkfs").val(),
            "cdurl": $("#url").val(),
            'xh': $("#cdsx").val()
        },
        success: function (data) {
            if (data.code == '0') {
                //组装更新树结构
                var newNode = {
                    "id": data.msg,//成功了，把生成主键ID塞到了msg。
                    "pId": $("#pid").val(),
                    "cdid": $("#cdid").val(),
                    "name": $("#menu_name").val(),
                    "syfw": parent.syfw,
                    "cdtb": $("#selectCdtb").val(),
                    "dkfs": $("#dkfs").val(),
                    "cdurl": $("#url").val(),
                    'xh': $("#cdsx").val()
                };
                parent.addNode(newNode);
                parent.tableObj.reload();
                layer.alert("新增菜单成功！");
            } else {
                layer.alert(data.msg);//失败
            }
        },
        error: function (data) {
            layer.alert(data);
        }
    });
}
/**
 * 根据id修改菜单选项，
 * @param id
 */
function editById() {
    $.ajax({
        type: "POST",
        url: "./editIndexMenuItem",
        dataType: "json",
        data: {
            "id": dataTran.id,
            "cdid": $("#cdid").val(),
            "name": $("#menu_name").val(),
            "cdtb": $("#selectCdtb").val(),
            "dkfs": $("#dkfs").val(),
            "cdurl": $("#url").val(),
            "xh": $("#cdsx").val(),
            "dkfs": $("#dkfs").val()
        },
        success: function (data) {
            var newNode = {
                "id": dataTran.id,
                "pId": $("#pid").val(),
                "cdid": $("#cdid").val(),
                "name": $("#menu_name").val(),
                "syfw": parent.syfw,
                "cdtb": $("#selectCdtb").val(),
                "dkfs": $("#dkfs").val(),
                "cdurl": $("#url").val(),
                'xh': $("#cdsx").val()
            };
            parent.refreshTree(newNode);
            parent.tableObj.reload();
            layer.alert(data.msg);
        },
        error: function (data) {
            layer.alert(data);
        }
    });
}
/**
 * 子界面选择菜单时调用传值
 * @param cdid
 */
function onCdidChanged(name, cdid) {
    $("#cdid").val(cdid);
}
/**
 * 子界面展开节点时需要
 * @returns {*|jQuery}
 */
function getCdid() {
    return $("#cdid").val();
}
/**
 * 子界面弹框选择图标时调用
 * @param id
 */
function fillCdtb(id) {
    $('#selectCdtb').val(id);
    $('#selectCdtbImg').attr('src', 'static/images/dzswj/' + id + '.png');
}
/**
 * 修改时初始化表单
 */
function initTableData() {
    $('#fcdmc').val(selectNode.name);
    $("#menu_name").val(dataTran.name);
    $("#cdid").val(dataTran.cdid);
    $("#url").val(dataTran.cdurl);
    $("#dkfs").val(dataTran.dkfs);
    $("#cdsx").val(dataTran.xh);
    $('#selectCdtb').val(dataTran.cdtb);
    $('#selectCdtbImg').attr('src', './static/images/dzswj/' + dataTran.cdtb + '.png');

}