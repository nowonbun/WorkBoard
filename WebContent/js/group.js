"use strict";
(function (_) {
    _.init();
    $(_.onLoad);
})((function () {
    var idx = 0;
    function initText() {
        $("#addGroupName").val("");
        $("#modifyGroupName").val("");
        $("#isActive").prop("checked", "");
        $(".add-group-board").hide();
        $(".modify-group-board").hide();
    }
    return {
        init: function () {
        },
        onLoad: function () {
            var table = $('#groupList').DataTable({
                ajax: {
                    url: 'setting/grouplist.json',
                    type: "POST",
                    dataSrc: ''
                },
                columns: [{
                        data: 'name'
                    }, {
                        data: 'state'
                    }],
                lengthMenu: [5],
                lengthChange: false,
                //bInfo : false
            });
            $("#groupList tbody").on('click', 'tr', function () {
                var data = table.row(this).data();
                initText();
                $("#modifyGroupName").val(data.name);
                if (data.active) {
                    $("#isActive").prop("checked", "checked");
                }
                idx = data.idx;
                $(".modify-group-board").show();
            });
            $("#addGroup").on('click', function () {
                initText();
                $(".add-group-board").show();
            });
            $("#addGroupTeamSave").on("click", function () {
                var name = $("#addGroupName").val();
                if (name === undefined || $.trim(name.toString()) === "") {
                    message.error("Please input Group name.");
                    return;
                }
                loader.on();
                $.ajax({
                    type: "POST",
                    url: "setting/addGroupName.json",
                    dataType: 'json',
                    data: {
                        name: name
                    },
                    success: function (data) {
                        if (data.success) {
                            initText();
                            table.ajax.reload();
                            message.success(data.message);
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
            $("#modifyGroupTeamSave").on("click", function () {
                var name = $("#modifyGroupName").val();
                if (idx <= 0 || name === undefined || $.trim(name.toString()) === "") {
                    message.error("Please input Group name.");
                    return;
                }
                loader.on();
                $.ajax({
                    type: "POST",
                    url: "setting/modifyGroupName.json",
                    dataType: 'json',
                    data: {
                        idx: idx,
                        name: name,
                        active: $("#isActive").prop("checked")
                    },
                    success: function (data) {
                        if (data.success) {
                            initText();
                            table.ajax.reload();
                            message.success(data.message);
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
        }
    };
})());
