"use strict";
(function (_) {
    _.init();
    $(_.onLoad);
})((function () {
    function initText() {
        $(".add-user-board").hide();
        $(".modify-user-board").hide();
    }
    return {
        init: function () {
        },
        onLoad: function () {
            var table = $('#userList').DataTable({
                ajax: {
                    url: 'setting/userlist.json',
                    type: "POST",
                    dataSrc: ''
                },
                columns: [{
                        data: 'id'
                    }, {
                        data: 'name'
                    }, {
                        data: 'state'
                    }, {
                        data: 'isAdmin'
                    }],
                lengthMenu: [5],
                lengthChange: false,
                //bInfo : false
            });
            $("#userList tbody").on('click', 'tr', function () {
                var data = table.row(this).data();
                console.log(data);
            });
            $("#addUser").on('click', function () {
                initText();
                $(".add-user-board").show();
                $("#group").select2();
            });
        }
    };
})());
