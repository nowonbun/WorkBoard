(function(_) {
	_.init();
	$(_.onLoad);
})((function() {
	let maximumImageFileSize = 1024 * 1024;
	let imageBuffer = null;
	let modal = new bootstrap.Modal($('#imageApplyModal')[0]);
	
	var Toast = Swal.mixin({
		toast : true,
		position : 'top-end',
		showConfirmButton : false,
		timer : 3000
	});
	function get(target) {
		return $("[name=" + target + "]");
	}
	
	function getValue(target) {
		return $.trim($(target).val());
	}
	
	function checkPassword() {
		if(getValue("#password") !== "" || getValue("#checkPassword") !== "") {
			if(getValue("#password") !== getValue("#checkPassword")){
				return "Please input same password and Retype password. <br />";
			}
			let pw = getValue("#password");
			let num = pw.search(/[0-9]/g);
			let eng1 = pw.search(/[a-z]/ig);
			let eng2 = pw.search(/[A-Z]/ig);
			if(pw.length < 8 || pw.length > 20) {
				return "Make sure it's at least 8 characters including a number and a lowercase letter. <br />";
			} else if(pw.search(/\s/) != -1) {
				return "Make sure it's at least 8 characters including a number and a lowercase letter. <br />";
			} else if(num < 0 || eng1 < 0 || eng2 < 0 ) {
				return "Make sure it's at least 8 characters including a number and a lowercase letter. <br />";
			}
		}
		return "";
	}

	return {
		init : function() {
			$("#profileSave").on("click", function() {
				let error = "";
				if(getValue("#name") === "") {
					error += "Please input test of Name. <br />";
				}
				error += checkPassword();
				if($.trim(error) !== "") {
					$(".error").html(error);
					$(".error").show();
					return;
				}
				$(".error").hide();
				let imageCode = $("#image").attr("src");
				if(imageCode.indexOf("base64") > 10) {
					if(imageCode.split(",").length == 2) {
						imageCode = imageCode.split(",")[1];
					}
				} else {
					imageCode = "";
				}
				loader.on();
				$.ajax({
					type : "POST",
					url : "setting/profileModify.json",
					dataType : 'json',
					data: {
						username: getValue("#name"),
						password: getValue("#password"),
						image: imageCode,
						isAdmin: $("#isAdmin").is(":checked")
					},
					success : function(data) {
						console.log(data);
						//
					},
					complete : function() {
						loader.off();
					}
				});
			});
			$("#image").on("click", function() {
				modal.show();
			});
			$('#imageApplyModal').on('hide.bs.modal', function (e) {
				imageBuffer = null;
			});
			$("#imageFile").on("change", function() {
				let file = $(this)[0].files[0];
				if (file.size > maximumImageFileSize) {
					Toast.fire({
						icon : 'error',
						title : 'size limit'
					});
					return;
				}
				$("label[for=imageFile]").text(file.name);
				let reader = new FileReader();
				reader.onload = function(e) {
					imageBuffer = reader.result;
				}
				reader.readAsDataURL(file);
			});
			$("#imageChage").on("click", function() {
				if(imageBuffer !== null) {
					$("#image").attr("src", imageBuffer);
				}
				modal.hide();
			});
		},
		onLoad : function() {
			loader.on();
			$.ajax({
				type : "POST",
				url : "setting/profile.json",
				dataType : 'json',
				async : false,
				success : function(data) {
					for ( let key in data) {
						if (data.hasOwnProperty(key)) {
							let ele = get(key);
							if (key === "image") {
								ele.attr("src", "data:image/png;base64," + data[key]);
							} else if (key === "isAdmin") {
								if (data[key] === true) {
									ele.prop("checked", "checked");
								} else {
									ele.prop("checked", "");
								}
							} else {
								ele.val(data[key]);
							}
						}
					}
				},
				complete : function() {
					loader.off();
				}
			});
		}
	}
})());