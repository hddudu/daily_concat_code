$(function () {
    var img = '';
    for (var i = 1; i <= 121; i++) {
        img +=
            '    <div class="layui-col-xs3" style="width: 53px;height: 53px;">' +
            '        <input type="image" name="images" value="' + i + '" src="static/images/dzswj/' + i + '.png"/>' +
            '    </div>';
    }
    $('#mainContent').append(img);
    $("*[name='images']").click(function () {
        parent.window.frames[0].fillCdtb(this.value);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
});