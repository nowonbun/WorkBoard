"use strict";
(function (_) {
    _.init();
    $(_.onLoad);
})((function () {
    var maximumImageFileSize = 1024 * 1024;
    var imageBuffer = null;
    var modal = new bootstrap.Modal($('#imageApplyModal')[0]);
    function get(target) {
        return $("#" + target);
    }
    function getValue(target) {
        var targetValue = $(target).val();
        if (targetValue !== undefined) {
            return $.trim(targetValue.toString());
        }
        return "";
    }
    function checkPassword() {
        if (getValue("#password") !== "" || getValue("#checkPassword") !== "") {
            if (getValue("#password") !== getValue("#checkPassword")) {
                return "Please input same password and Retype password. <br />";
            }
            var pw = getValue("#password");
            var num = pw.search(/[0-9]/g);
            var eng1 = pw.search(/[a-z]/ig);
            var eng2 = pw.search(/[A-Z]/ig);
            if (pw.length < 8 || pw.length > 20) {
                return "Make sure it's at least 8 characters including a number and a lowercase letter. <br />";
            }
            else if (pw.search(/\s/) != -1) {
                return "Make sure it's at least 8 characters including a number and a lowercase letter. <br />";
            }
            else if (num < 0 || eng1 < 0 || eng2 < 0) {
                return "Make sure it's at least 8 characters including a number and a lowercase letter. <br />";
            }
        }
        return "";
    }
    return {
        init: function () {
            $("#profileSave").on("click", function () {
                var error = "";
                if (getValue("#name") === "") {
                    error += "Please input test of Name. <br />";
                }
                error += checkPassword();
                if ($.trim(error) !== "") {
                    $(".error").html(error);
                    $(".error").show();
                    return;
                }
                $(".error").hide();
                var imageCode = $("#image").attr("src");
                if (imageCode !== undefined && imageCode.indexOf("base64") > 10) {
                    if (imageCode.split(",").length == 2) {
                        imageCode = imageCode.split(",")[1];
                    }
                }
                else {
                    imageCode = "";
                }
                loader.on();
                $.ajax({
                    type: "POST",
                    url: "setting/profileModify.json",
                    dataType: 'json',
                    data: {
                        username: getValue("#name"),
                        password: getValue("#password"),
                        image: imageCode,
                        isAdmin: $("#isAdmin").is(":checked")
                    },
                    success: function (data) {
                        if (data.success) {
                            message.success(data.message);
                            $("#password").val("");
                            $("#checkPassword").val("");
                            var nameValue = $("#name").val();
                            if (nameValue !== undefined) {
                                $("#mainName").html(nameValue.toString());
                            }
                            var imageSrc = $("#image").attr("src");
                            if (imageSrc !== undefined) {
                                $("#mainImage").attr("src", imageSrc);
                            }
                        }
                        else {
                            message.error(data.message);
                        }
                    },
                    error: function (data, e) {
                        message.error(e);
                    },
                    complete: function () {
                        loader.off();
                    }
                });
            });
            $("#image").on("click", function () {
                modal.show();
            });
            $('#imageApplyModal').on('hide.bs.modal', function (e) {
                imageBuffer = null;
            });
            $("#imageFile").on("change", function () {
                var el = $(this)[0];
                if (el.files !== null) {
                    var file = el.files[0];
                    if (file.size > maximumImageFileSize) {
                        message.error('size limit');
                        return;
                    }
                    $("label[for=imageFile]").text(file.name);
                    var reader_1 = new FileReader();
                    reader_1.onload = function (e) {
                        imageBuffer = reader_1.result;
                    };
                    reader_1.readAsDataURL(file);
                }
            });
            $("#imageChage").on("click", function () {
                if (imageBuffer !== null) {
                    $("#image").attr("src", imageBuffer);
                }
                modal.hide();
            });
        },
        onLoad: function () {
            loader.on();
            $.ajax({
                type: "POST",
                url: "setting/profile.json",
                dataType: 'json',
                async: false,
                success: function (data) {
                    for (var key in data) {
                        if (data.hasOwnProperty(key)) {
                            var ele = get(key);
                            if (key === "image") {
                                ele.attr("src", "data:image/png;base64," + data[key]);
                            }
                            else if (key === "isAdmin") {
                                if (data[key] === true) {
                                    ele.prop("checked", "checked");
                                }
                                else {
                                    ele.prop("checked", "");
                                }
                            }
                            else {
                                ele.val(data[key]);
                            }
                        }
                    }
                },
                complete: function () {
                    loader.off();
                }
            });
        }
    };
})());
