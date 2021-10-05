(function(_) {
	_.init();
	$(_.onLoad);
})((function() {
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
				lengthChange : false,
				bInfo : false
			});
			$("#groupList tbody").on('click', 'tr', function() {
				let data = table.row(this).data();
				console.log(data);
			});
			$("#addGroup").on('click', function() {
				$(".add-board").show();
			});
			$("#groupTeamSave").on("click", function(){
				console.log("add group");
			});
		}
	}
})());