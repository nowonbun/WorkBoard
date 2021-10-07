(function(_) {
	_.init();
	$(_.onLoad);
})((function() {
	let idx = 0;
	function initText() {
		$("#addGroupName").val("");
		$("#modifyGroupName").val("");
		$("#isActive").prop("checked", "");
		$(".add-group-board").hide();
		$(".modify-group-board").hide();
	}
	return {
		init : function() {

		},
		onLoad : function() {
			let table = $('#groupList').DataTable({
				ajax : {
					url : 'setting/grouplist.json',
					type : "POST",
					dataSrc : ''
				},
				columns : [ {
					data : 'name'
				}, {
					data : 'state'
				} ],
				lengthMenu : [ 5 ],
				lengthChange : false,
				bInfo : false
			});
			$("#groupList tbody").on('click', 'tr', function() {
				let data = table.row(this).data();
				initText();
				$("#modifyGroupName").val(data.name);
				if (data.active) {
					$("#isActive").prop("checked", "checked");
				}
				idx = data.idx;
				$(".modify-group-board").show();
			});
			$("#addGroup").on('click', function() {
				initText();
				$(".add-group-board").show();
			});
			$("#addGroupTeamSave").on("click", function() {
				let name = $("#addGroupName").val();
				if ($.trim(name) === "") {
					message.error("Please input Group name.");
					return;
				}
				loader.on();
				$.ajax({
					type : "POST",
					url : "setting/addGroupName.json",
					dataType : 'json',
					data : {
						name : name
					},
					success : function(data) {
						if (data.success) {
							initText();
							table.ajax.reload();
							message.success(data.message);
						} else {
							message.error(data.message);
						}
					},
					error : function(data, e) {
						message.error(e);
					},
					complete : function() {
						loader.off();
					}
				});
			});
			$("#modifyGroupTeamSave").on("click", function() {
				let name = $("#modifyGroupName").val();
				if (idx <= 0 || $.trim(name) === "") {
					message.error("Please input Group name.");
					return;
				}
				loader.on();
				$.ajax({
					type : "POST",
					url : "setting/modifyGroupName.json",
					dataType : 'json',
					data : {
						idx : idx,
						name : name,
						active : $("#isActive").prop("checked")
					},
					success : function(data) {
						if (data.success) {
							initText();
							table.ajax.reload();
							message.success(data.message);
						} else {
							message.error(data.message);
						}
					},
					error : function(data, e) {
						message.error(e);
					},
					complete : function() {
						loader.off();
					}
				});
			});
		}
	}
})());