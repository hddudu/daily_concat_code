/**
 * 引入jquery
 * createElement 其实: <script></script>
 */
// var script = document.createElement('script');
// script.type = 'text/javascript';
// script.src = '../resources4/tax-js/jquery-1.9.1.min.js';
// document.getElementsByTagName('head')[0].appendChild(script);
// $(document).ready(function () {
//     $(document).on('click','#bt',function () {
//         alert('');
//     });
// });
// window.onload = function () {
//     var script = document.createElement('script');
//     script.type = 'text/javascript';
//     script.src = '../resources4/tax-js/jquery-1.9.1.min.js';
//     document.getElementsByTagName('head')[0].appendChild(script);
//     jQuery(document).on('click','#bt',function () {
//         alert('引入js成功');
//     });
// }
$(function () {
    $(document).on('click','#bt',function () {
        alert('引入js成功');
    });
});
/**
 * ajax请求的二次封装 基于jquery : 抽取参数
 *  type : 默认post 省略
 *  data : 参数一 采用 layui的data : layui会自动将表单的name 抽成 key : value 的形式
 *  url : 参数二
 *
 *  返回成功后的处理 : 返回码 + 返回提示信息 《== 后台返回数据提供
 *       code 0  or  code  1
 *  针对表格数据 + 表单数据进行处理
 *      使用的table or form
 *  以及callback的调用参数
 *
 *
 * Object.prototype.toString.call()方法浅谈 :
 *   Object.prototype.toString.call(null);//”[object Null]”
     Object.prototype.toString.call(undefined);//”[object Undefined]”
     Object.prototype.toString.call(“abc”);//”[object String]”
     Object.prototype.toString.call(123);//”[object Number]”
     Object.prototype.toString.call(true);//”[object Boolean]”

 */
/**
 * 直接通过new对象进行调用
 */
(function () {
    /**
     *
     * @param opts
     */
    function ajaxRequest(opts) {
        this.type = opts.type || "get";
        this.url = opts.url ;
        this.data = opts.data || {};
        this.isShowLoader = opts.isShowLoader || false;
        this.dataType = opts.dataType || "json";
        this.callBack = opts.callBack;
        this.init();
    }
    ajaxRequest.prototype = {
        init : function () {
            this.sendRequest();
        },
        //渲染loader
        showLoader : function () {
            if(this.isShowLoader) {
                var loader = '<div class  = "ajaxLoader"><div class="loader">加载中....</div></div>';
                $("body").append(loader);
            }
        },
        hideLoader : function() {
          if(this.isShowLoader) {
              $(".ajaxLoader").remove();
          }
        },
        sendRequest : function () {
            var self = this;
            $.ajax({
                type : this.type,
                url : this.url,
                data : this.data,
                dataType : this.dataType,
                beforeSend : this.showLoader(),
                success : function (res) {
                    self.hideLoader();
                    if(res != null && res != '') {
                        if(self.callBack && Object.prototype.toString().call(self.callBack) === "[object Function]") {
                            self.callBack(res);
                        } else {
                            console.log("callBack is not a function");
                        }
                    }
                }
            });
        }
    };
    /**
     * 提供一个全局变量给外进行访问
     * @type {ajaxRequest}
     */
    window.$ajax = ajaxRequest;
})();


