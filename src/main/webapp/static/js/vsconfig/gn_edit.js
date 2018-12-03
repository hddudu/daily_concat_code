$(function () {
    if ('edit' == parent.action) {
        initForm();
    }
    $('#save').click(function () {
        if ('edit' == parent.action) {
            doGn('editGN');
        } else if ('new' == parent.action) {
            doGn('insertGN');
        }
    });
    $('#cancel').click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
});
/**
 * 新增修改功能
 */
function doGn(url) {
    $.ajax({
        type: "POST",
        url: "./" + url,
        dataType: "json",
        data: {
            'gnDm': $('#gnDm').val(),
            'gnmc': $('#gnmc').val(),
            'gnurl': $('#gnurl').val(),
            'gdsywsx': $('#gdsywsx').val(),
            'xh': $('#xh').val(),
            'smbsBz': $('#smbsBz').val(),
            'sycaBz': $('#sycaBz').val(),
            'tyBz': $('#tyBz').val(),
            'dkfs': $('#dkfs').val(),
            'cdtb': $('#cdtb').val(),
            'qxkzsx': $('#qxkzsx').val(),
            'gnjlx': $('#gnjlx').val()
        },
        success: function (data) {
            if ('new' == parent.action) {
                parent.fillTable($('#gnDm').val(), $('#gnmc').val());
            } else if ('edit' == parent.action) {
                parent.tableObj.reload();
            }
            layer.alert(data.msg);
        },
        error: function (data) {
            layer.alert(data);
        }
    });
}
/**
 * 修改时表单初始化
 */
function initForm() {
    var data = parent.dataTran;
    $('#gnDm').val(data.gnDm);
    $('#gnmc').val(data.gnmc);
    $('#gnurl').val(data.gnurl);
    $('#xh').val(data.xh);
    $('#sycaBz').val(data.sycaBz);
    $('#tyBz').val(data.tyBz);
    $('#cdtb').val(data.cdtb);
    handleSelect('gdsywsx', data.gdsywsx);
    handleSelect('smbsBz', data.smbsBz);
    handleSelect('qxkzsx', data.qxkzsx);
    handleSelect('gnjlx', data.gnjlx);
    handleSelect('dkfs', data.dkfs);

}
/**
 * 当从数据库中读取的值为非规定值时，处理为空
 * @param id
 * @param target
 */
function handleSelect(id, target) {
    var isValNull = true;
    $("#" + id + " option").each(function () {
        if ($(this).attr("value") == target) {
            $('#' + id).val(target);
            isValNull = false;
            return false;
        }
    });
    if (isValNull) {
        $('#' + id).val('');
    }
}