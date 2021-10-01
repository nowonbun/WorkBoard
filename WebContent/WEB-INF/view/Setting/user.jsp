<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.user-list {
	padding: 0.5rem;
}

.user-list #userList_filter {
	display: none;
}

.user-list #userList {
	box-shadow: 1.2px 1.5px 2px;
}

.user-list .paginate_button [aria-controls=userList] {
	border: 0px;
}

.user-list #userList_paginate {
	font-size: 0.85rem;
}

.user-list .page-list {
	padding: .3rem .55rem;
}
.user-list tbody tr {
	cursor: pointer;
}
</style>
<section class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6"></div>
			<div class="col-sm-6">
				<ol class="breadcrumb float-sm-right">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item active">User</li>
				</ol>
			</div>
		</div>
	</div>
</section>
<section class="content max-1200">
	<div class="row">
		<div class="col-md-12">
			<div class="card card-primary">
				<div class="card-header">
					<h3 class="card-title">User List</h3>
					<div class="card-tools"></div>
				</div>
				<div class="card-body table-responsive user-list">
					<table id="userList" class="table table-hover text-nowrap" style="width: 100%;">
						<thead style="background-color: #f0f8ff;">
							<tr>
								<td>Id</td>
								<td>Name</td>
								<td style="width: 50px;">State</td>
								<td style="width: 50px;">Admin</td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-12">
			<input type="submit" value="Add User..." class="btn btn-success float-right">
		</div>
	</div>
</section>
<script src="js/user.js?v=1"></script>