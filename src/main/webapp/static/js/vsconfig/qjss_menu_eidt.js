var selectNode = parent.selectNode;//选中菜单数据
var dataTran = parent.dataTran;//选中表格中行数据
$(function () {
    $('#cdmc').attr('disabled', true);//菜单名称不允许修改
    $('#ywfldm').attr('disabled', true);//业务分类代码不允许修改
    if (parent.action == 'edit') {
        initFormData(dataTran.cdid);
    }
    $("#save").click(function () {
        insertOrUpdateBusinessMenu();
    });
    $("#cancel").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    layui.use(['form'], function () {
        var form = layui.form;
        form.render();
    });
});
/**
 * 修改时初始化表单
 * @param id
 */
function initFormData(cdid) {
    $.ajax({
        type: "GET",
        url: "./getQjssMenuDetailById",
        dataType: "json",
        data: {
            "id": cdid
        },
        success: function (data) {
            $("#cdid").val(data.cdid);
            $("#cdmc").val(data.cdmc);
            //$("#ywfldm").val(data.ywfldm);
            handleSelect('ywfldm', data.ywfldm, '');
            
            $("#zdygjz").val(data.zdygjz);
            $("#pmdf").val(data.pmdf);
            
            //$("#tjbz").val(data.tjbz);
            handleSelect('tjbz', data.tjbz, 'N');
            layui.use(['form'], function () {
                var form = layui.form;
                form.render();
            });
        }
    });
}
/**
 * 当从数据库中读取的值为非规定值时，处理为给定值
 * @param id
 * @param target
 * @param final
 */
function handleSelect(id, target, final) {
    var isValfinal = true;
    $("#" + id + " option").each(function () {
        if ($(this).attr("value") == target) {
            $('#' + id).val(target);
            isValfinal = false;
            return false;
        }
    });
    if (isValfinal) {
        $('#' + id).val(final);
    }
}
/**
 * 新增时初始化表单
 */
function initNewMenuFormData() {
    $("#fcdid").val(selectNode.cdid);
}
/**
 * 新增修改菜单
 */
function insertOrUpdateBusinessMenu() {
    var param = {
        "cdid": $("#cdid").val(),
        "sslx": $("#sslx").val(),
        "ywfldm": $("#ywfldm").val(),
        "zdygjz": $("#zdygjz").val(),
        "pmdf": $("#pmdf").val(),
        "tjbz": $("#tjbz").val(),
        "sfyjsy": $("#sfyjsy").val()
    };
    $.ajax({
        type: "POST",
        url: './editSelectQjssMenu',
        dataType: "json",
        data: param,
        success: function (data) {
            if (data.code == '0') {
                //parent.refreshTree(param);
                parent.tableObj.reload();
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
            layer.alert(data.msg);
        }
    });
}

