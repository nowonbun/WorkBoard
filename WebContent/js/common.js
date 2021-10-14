"use strict";
var message = (function () {
    toastr.options.timeOut = 2000;
    return {
        info: function (msg) {
            toastr.info(msg);
        },
        success: function (msg) {
            toastr.success(msg);
        },
        error: function (msg) {
            toastr.error(msg);
        },
        warming: function (msg) {
            toastr.warning(msg);
        }
    };
})();
(function (_) {
    _.init();
    $(_.onLoad);
})((function () {
    function setUrlLocation(param) {
        if ($.trim(param) !== "") {
            param = "?" + param;
        }
        if (param === undefined || $.trim(param) === "") {
            history.pushState(null, '', location.pathname);
        }
        else {
            history.pushState(null, '', location.pathname + param);
        }
    }
    function getUrlLocation() {
        var param = decodeURIComponent(location.search.substring(1));
        var datas = param.split('&');
        var ret = [];
        for (var i = 0; i < datas.length; i++) {
            var data = datas[i];
            var s = data.split("=");
            if ($.trim(s[0]) === "") {
                continue;
            }
            ret[$.trim(s[0])] = $.trim(s[1]);
        }
        return ret;
    }
    function createQueryStringParameter(params) {
        var ret = "";
        for (var key in params) {
            if (params.hasOwnProperty(key)) {
                ret += "&" + key + "=" + params[key];
            }
        }
        return ret.substring(1);
    }
    function getAjax(data, done, fail, always) {
        if (done === void 0) { done = undefined; }
        if (fail === void 0) { fail = undefined; }
        if (always === void 0) { always = undefined; }
        if (done !== undefined && typeof done === 'function') {
            data.success = done;
        }
        if (fail !== undefined && typeof fail === 'function') {
            data.error = fail;
        }
        if (always !== undefined && typeof always === 'function') {
            data.complete = always;
        }
        var ajax = $.ajax(data);
        return ajax;
    }
    function setMenu() {
        function createLi(node) {
            var li = $('<li class="nav-item"></li>');
            var a = $('<a class="nav-link main-menu-tab"></a>');
            if (node.url === undefined || node.url === null) {
                a.prop('href', "#");
            }
            else {
                a.prop('href', node.url);
            }
            if (node.code !== undefined && node.code !== null) {
                a.attr("data-code", node.code);
            }
            if (node.icon !== undefined && node.url !== null) {
                a.append($('<i class="nav-icon"></i>').addClass(node.icon));
            }
            var p = $('<p></p>').append(node.title + " ");
            if (node.list !== undefined && node.list.length > 0) {
                p.append($('<i class="right fas fa-angle-left"></i>'));
            }
            a.append(p);
            li.append(a);
            if (node.list !== undefined && node.list.length > 0) {
                var ul = $('<ul class="nav nav-treeview nav-sub"></ul>');
                for (var i = 0; i < node.list.length; i++) {
                    ul.append(createLi(node.list[i]));
                }
                li.append(ul);
            }
            return li;
        }
        getAjax({
            type: "POST",
            url: "menu.json",
            dataType: 'json',
            async: false
        }, function (data) {
            var ul = $('<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false"></ul>');
            for (var i = 0; i < data.length; i++) {
                ul.append(createLi(data[i]));
            }
            $("#menu").html(ul[0]);
        });
    }
    function attachEvent() {
        $(document).on("click", ".main-menu-tab", function (event) {
            event.preventDefault();
            event.stopPropagation();
            $(".main-menu-tab").removeClass("active");
            var href = $(this).attr("href");
            var code = $(this).data("code");
            setUrlLocation("path=" + code);
            $(this).addClass("active");
            var parent = $(this).closest(".menu-open");
            $(parent).children("a").addClass("active");
            if (href === "#") {
                return;
            }
            loader.on();
            getAjax({
                type: "GET",
                url: href,
                dataType: 'html',
                async: false
            }, function (html) {
                $("#mainContents").html(html);
            }, function () {
                // error
            }, function () {
                loader.off();
            });
        });
        $("#logout").on("click", function () {
            location.href = "logout.html";
        });
    }
    function movePage(code) {
        if (code === undefined || code === null) {
            return;
        }
        $(".main-menu-tab").removeClass("active");
        var a = $("[data-code=" + code + "]");
        $(a).addClass("active");
        var ul = $(a).closest("ul");
        if ($(ul).hasClass("nav-sub")) {
            $(ul).show();
            var parent_1 = $(ul).closest(".nav-item");
            $(parent_1).addClass("menu-is-opening menu-open");
            $(parent_1).children("a").addClass("active");
        }
        var href = $(a).attr("href");
        if (href === "#") {
            return;
        }
        loader.on();
        getAjax({
            type: "GET",
            url: href,
            dataType: 'html',
            async: false
        }, function (html) {
            $("#mainContents").html(html);
        }, function () {
            // error
        }, function () {
            loader.off();
        });
    }
    return {
        init: function () {
            setMenu();
        },
        onLoad: function () {
            loader.setStyle("color-4");
            attachEvent();
            var param = getUrlLocation();
            movePage(param["path"]);
        }
    };
})());
