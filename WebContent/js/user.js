(function(_) {
	_.init();
	$(_.onLoad);
})((function() {
	return {
		init : function() {

		},
		onLoad : function() {
			let table = $('#userList').DataTable({
				ajax : {
					url : 'setting/userlist.json',
					type : "POST",
					dataSrc : ''
				},
				columns : [ {
					data : 'id'
				}, {
					data : 'name'
				}, {
					data : 'state'
				}, {
					data : 'isAdmin'
				} ],
				lengthChange : false,
				bInfo : false
			});
			$("#userList tbody").on('click', 'tr', function(){
				let data = table.row(this).data();
				console.log(data);
			});
		}
	}
})());