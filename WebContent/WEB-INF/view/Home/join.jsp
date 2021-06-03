<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Work Board Sign uP</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/icheck-bootstrap/3.0.1/icheck-bootstrap.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/css/adminlte.min.css">
</head>
<body class="hold-transition register-page">
	<div class="register-box">
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<a href="/login.html" class="h1"><b>Sign up</b></a>
			</div>
			<div class="card-body">
				<p class="login-box-msg">Register a new membership</p>
				<span class="error-message" style="color: red;"></span>
				<form method="post">
					<div class="input-group mb-3">
						<input type="hidden" name="email" value="${email}"> 
						<input type="hidden" name="key" value="${key}"> 
						<input type="hidden" name="type" value="${type}"> 
						<input type="email" class="form-control" placeholder="Email" readonly="readonly" disabled="disabled" value="${email}">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="companyName" placeholder="Company name">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-building"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" name="password" placeholder="Password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="Retype password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" id="agreeTerms" name="terms" value="agree"> 
								<label for="agreeTerms"> I agree to the <a href="#">terms</a></label>
							</div>
						</div>
						<div class="col-4">
							<button type="submit" class="btn btn-primary btn-block">Register</button>
						</div>
					</div>
				</form>
				<a href="login.html" class="text-center">I already have a membership</a>
			</div>
		</div>
	</div>
	<script src="//code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/js/adminlte.min.js"></script>
	<script>
		$(function() {
			(function() {
				function isNullAndWhitespace(val) {
					if (val === null || $.trim(val) === "") {
						return true;
					}
					return false;
				}
				function addError(message) {
					if (!isNullAndWhitespace($(".error-message").html())) {
						$(".error-message").html($(".error-message").html() + "<br />");
					}
					$(".error-message").html($(".error-message").html() + message);
				}
				$("form").on("submit", function() {
					$(".error-message").html("");
					let email = $("[name=email]").val();
					let key = $("[name=key]").val();
					let type = $("[name=type]").val();
					if (isNullAndWhitespace(email) || isNullAndWhitespace(key) || isNullAndWhitespace(type)) {
						location.href = "register.html";
						return false;
					}
					let submit = true;
					if (isNullAndWhitespace($("[name=companyName]").val())) {
						addError("Please input company name.");
						submit = false;
					}
					let check = true;
					let password = [];
					$("[type=password]").each(function() {
						if (isNullAndWhitespace($(this).val())) {
							if(check){
								addError("Please input password or Retype password.");
								check = false;
							}
							submit = false;
							return;
						}
						password.push($(this).val());
					});
					if (!$("#agreeTerms").prop("checked")) {
						addError("Please check terms.");
						submit = false;
					}
					if (!submit) {
						return false;
					}
					if (password[0] !== password[1]) {
						addError("Please input same password and Retype password.");
						return false;
					}
				});

			})();
		})
	</script>
</body>
</html>
