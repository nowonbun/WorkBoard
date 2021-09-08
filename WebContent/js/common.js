(function(_) {
	_.init();
	$(_.onLoad);
})((function() {
	function setUrlLocation(param) {
		if ($.trim(param) !== "") {
			param = "?" + param;
		}
		if (param === undefined || $.trim(param) === "") {
			history.pushState(null, '', location.pathname);
		} else {
			history.pushState(null, '', location.pathname + param);
		}
	}
	function getUrlLocation() {
		let param = decodeURIComponent(location.search.substring(1));
		let datas = param.split('&');
		let ret = [];
		for (let i = 0; i < datas.length; i++) {
			let data = datas[i];
			let s = data.split("=");
			if ($.trim(s[0]) === "") {
				continue;
			}
			ret[$.trim(s[0])] = $.trim(s[1]);
		}
		return ret;
	}
	function createQueryStringParameter(params) {
		let ret = "";
		for ( let key in params) {
			if (params.hasOwnProperty(key)) {
				ret += key + "=" + params[key];
			}
		}
		return ret;
	}
	function getAjax(data, done, fail, always) {
		if (done !== undefined && typeof done === 'function') {
			data.success = done;
		}
		if (fail !== undefined && typeof fail === 'function') {
			data.error = fail;
		}
		if (always !== undefined && typeof always === 'function') {
			data.complete = always;
		}
		let ajax = $.ajax(data);
		return ajax;
	}
	function setMenu() {
		function createLi(node) {
			let li = $('<li class="nav-item"></li>');
			let a = $('<a class="nav-link main-menu-tab"></a>');
			if (node.url === undefined || node.url === null) {
				a.prop('href', "#");
			} else {
				a.prop('href', node.url);
			}
			if (node.code !== undefined && node.code !== null) {
				a.attr("data-code", node.code);
			}
			if (node.icon !== undefined && node.url !== null) {
				a.append($('<i class="nav-icon"></i>').addClass(node.icon));
			}
			let p = $('<p></p>').append(node.title + " ");
			if (node.list !== undefined && node.list.length > 0) {
				p.append($('<i class="right fas fa-angle-left"></i>'));
			}
			a.append(p);
			li.append(a);
			if (node.list !== undefined && node.list.length > 0) {
				let ul = $('<ul class="nav nav-treeview nav-sub"></ul>')
				for (let i = 0; i < node.list.length; i++) {
					ul.append(createLi(node.list[i]));
				}
				li.append(ul);
			}
			return li;
		}
		getAjax({
			type : "POST",
			url : "menu.json",
			dataType : 'json',
			async : false
		}, function(data) {
			let ul = $('<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false"></ul>');
			for (let i = 0; i < data.length; i++) {
				ul.append(createLi(data[i]));
			}
			$("#menu").html(ul);
		});
	}
	function attachEvent() {
		$(document).on("click", ".main-menu-tab", function() {
			event.preventDefault();
			event.stopPropagation();
			$(".main-menu-tab").removeClass("active");
			let href = $(this).attr("href");
			let code = $(this).data("code");
			setUrlLocation("path=" + code);
			$(this).addClass("active");
			let parent = $(this).closest(".menu-open")
			$(parent).children("a").addClass("active");
			if (href === "#") {
				return;
			}
			loader.on();
			getAjax({
				type : "GET",
				url : href,
				dataType : 'html',
				async : false
			}, function(html) {
				$("#mainContents").html(html);
			}, function() {
				// error
			}, function() {
				loader.off();
			});
		});
	}
	function movePage(code) {
		if(code === undefined || code === null) {
			return;
		}
		$(".main-menu-tab").removeClass("active");
		let a = $("[data-code=" + code + "]");
		$(a).addClass("active");
		let ul = $(a).closest("ul");
		if ($(ul).hasClass("nav-sub")) {
			$(ul).show();
			let parent = $(ul).closest(".nav-item");
			$(parent).addClass("menu-is-opening menu-open");
			$(parent).children("a").addClass("active");
		}
		let href =  $(a).attr("href");
		if (href === "#") {
			return;
		}
		loader.on();
		getAjax({
			type : "GET",
			url : href,
			dataType : 'html',
			async : false
		}, function(html) {
			$("#mainContents").html(html);
		}, function() {
			// error
		}, function() {
			loader.off();
		});
	}
	return {
		init : function() {
			setMenu();
		},
		onLoad : function() {
			loader.setStyle("color-4");
			attachEvent();
			let param = getUrlLocation();
			movePage(param["path"]);
		}
	};
})());