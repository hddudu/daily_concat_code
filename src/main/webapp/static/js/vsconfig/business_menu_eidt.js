var selectNode = parent.selectNode;//选中菜单数据
var dataTran = parent.dataTran;//选中表格中行数据
$(function () {
    initSelectGndm();
    $('#fcdmc').attr('disabled', true);
    if (selectNode == null) {
        $('#fcdmc').val('根目录');
    } else {
        $('#fcdmc').val(selectNode.cdmc);
    }
    if (parent.action == 'edit') {
        initFormData(dataTran.cdid);
        $('#cdid').attr('disabled', true);
    } else if (parent.action == 'new') {
        initNewMenuFormData();
    }
    $("#save").click(function () {
        insertOrUpdateBusinessMenu(parent.action);
    });
    $("#cancel").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    $("#uuid").click(function () {
        if (parent.action == 'edit') {
            return;
        }
        $.ajax({
            type: "GET",
            url: "./getUUID",
            dataType: "text",
            success: function (data) {
                $('#cdid').val(data);
            }
        });

    });
    $('.selectCdtbImg').click(function () {
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
        url: "./getBusinessMenuDetailById",
        dataType: "json",
        data: {
            "id": cdid
        },
        success: function (data) {
            $("#cdid").val(data.cdid);
            $("#cdmc").val(data.cdmc);
            handleSelect('cdsx', data.cdsx, '1');
            $("#gndm").val(data.gnDm);
            $("#fcdid").val(data.fcdid);
            $("#xh").val(data.xh);
            $("#tybz").val(data.tyBz);
            $("#sycddkfs").val(data.sycddkfs);
            if(data.cdtb!=null){
            	$('#selectCdtb').val(data.cdtb);
            	$('#selectCdtbImg').attr('src', './static/images/dzswj/' + data.cdtb + '.png');
            }
            layui.use(['form'], function () {
                var form = layui.form;
                form.render();
            });
        }
    });
}
/**
 * 子界面弹框选择图标时调用
 * @param id
 */
function fillCdtb(id) {
	if(id != null){
		$('#selectCdtb').val(id);
		$('#selectCdtbImg').attr('src', 'static/images/dzswj/' + id + '.png');
	}
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
function insertOrUpdateBusinessMenu(action) {
    var url = './newBusinessMenu';
    if (action == 'edit') {
        url = './editSelectBusinessMenu';
    }
    var param = {
        "cdid": $("#cdid").val(),
        "cdmc": $("#cdmc").val(),
        "cdsx": $("#cdsx").val(),
        "gnDm": $("#gndm").val(),
        "fcdid": $("#fcdid").val(),
        "xh": $("#xh").val(),
        "tyBz": $("#tybz").val(),
        "sycddkfs":$("#sycddkfs").val(),
        "cdtb": $("#selectCdtb").val(),
        "syfw": parent.syfw//适用范围
    };
    $.ajax({
        type: "POST",
        url: url,
        dataType: "json",
        data: param,
        success: function (data) {
            if (data.code == '0') {
                if (action == 'new') {
                    parent.addNode(param);
                    parent.tableObj.reload();
                } else if (action == 'edit') {
                    parent.refreshTree(param);
                    parent.tableObj.reload();
                }
            }
            layer.alert(data.msg);
        }
    });
}
/**
 * 功能代码下拉选项
 */
function initSelectGndm() {
    $.ajax({
        type: "POST",
        url: "./selectGnByConditon",
        dataType: "json",
        async:false,
        data: {
            "page": 1,
            "limit": 100000000
        },
        success: function (data) {
            var arr = data.data;

            var html = '<option value=""></option>';
            for (var i = 0; i < arr.length; i++) {
                html += '<option value="' + arr[i].gnDm + '">' + arr[i].gnmc + '(' + arr[i].gnDm + ')</option>';
            }
            $('#gndm').append(html);
            layui.use(['form'], function () {
                var form = layui.form;
                form.render();
            });
        }
    });
}