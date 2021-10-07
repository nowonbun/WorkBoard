<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.user-list {
	padding: 0.5rem;
}

.user-list #userList_filter {
	/*display: none;*/
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
<div class="row" style="margin:0px!important;">
	<section class="content-header col-12">
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
	<section class="content max-1200 col-12">
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
				<button class="btn btn-success float-right" id="addUser">Add User...</button>
			</div>
		</div>
		<div class="card card-primary mt-2 add-user-board" style="display:none;">
			<div class="card-body">
				<div class="error" style="display: none;"></div>
				<form id="profileForm">
					<div class="form-group">
						<label for="company">Company</label> 
						<input type="text" id="company" name="company" class="form-control" readonly="readonly" disabled="disabled">
					</div>
					<div class="form-group">
						<label for="company">Group</label> 
						<input type="text" id="group" name="group" class="form-control" readonly="readonly" disabled="disabled">
					</div>
					<div class="form-group">
						<label for="uid">ID</label> 
						<input type="email" class="form-control" placeholder="Email" name="emailAddress">
					</div>
					<div class="form-group">
						<label for="image">Image</label> 
						<img class="form-control img-apply" id="image" name="image" style="width: 120px; height: 120px; cursor: pointer;">
					</div>
					<div class="icheck-primary">
						<input type="checkbox" id="isAdmin" name="isAdmin"> <label for="isAdmin">Permission Admin</label>
					</div>
				</form>
			</div>
			<div class="row mb-2" style="width: 100%;">
				<div class="col-12 text-right">
					<button class="btn btn-success float-right" id="profileSave">Save</button>
				</div>
			</div>
		</div>
	</section>
</div>
<script src="js/user.js?v=1"></script>