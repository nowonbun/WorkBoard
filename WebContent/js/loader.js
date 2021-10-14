"use strict";
/*
    creater: nowonbun@gmail.com
    date: 2019/10/16
*/
var loader = (function ($clz) {
    $clz.init();
    $($clz.onLoad);
    return $clz;
})((function () {
    var preventReload = true;
    var isLoading = false;
    return {
        init: function () {
            window.onbeforeunload = function () {
                if (preventReload && isLoading) {
                    return true;
                }
            };
            document.onkeydown = function (event) {
                var code = event.key;
                if (preventReload && isLoading) {
                    console.log(code);
                    if ((event.ctrlKey == true && (code === 'KeyN' || code === 'KeyR')) || (code === 'F5')) {
                        //event.keyCode = 0;
                        event.cancelBubble = true;
                        //event.returnValue = false;
                    }
                }
            };
        },
        onLoad: function () {
            $("body").prepend('<div class="loader"></div><section class="loader-layout"></section>');
        },
        setStyle: function (style) {
            $(".loader").addClass(style);
        },
        setReload: function (is) {
            if (is === false) {
                preventReload = false;
            }
            else {
                preventReload = true;
            }
        },
        on: function (cb) {
            if (cb === void 0) { cb = null; }
            isLoading = true;
            $(".loader").addClass("on");
            $(".loader-layout").addClass("on");
            if (cb !== null && typeof cb === "function") {
                cb.call(this);
            }
        },
        off: function (cb) {
            if (cb === void 0) { cb = null; }
            isLoading = false;
            $(".loader").removeClass("on");
            $(".loader-layout").removeClass("on");
            if (cb !== null && typeof cb === "function") {
                cb.call(this);
            }
        }
    };
})());
