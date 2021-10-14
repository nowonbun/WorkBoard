<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.group-list {
	padding: 0.5rem;
}

.group-list #groupList_filter {
	/*display: none;*/
	
}

.group-list #groupList {
	box-shadow: 1.2px 1.5px 2px;
}

.group-list .paginate_button [aria-controls=groupList] {
	border: 0px;
}

.group-list #groupList_paginate {
	font-size: 0.85rem;
}

.group-list .page-list {
	padding: .3rem .55rem;
}

.group-list tbody tr {
	cursor: pointer;
}

.group-list .dataTables_info {
	display:none;	
}
</style>
<div class="row" style="margin: 0px !important;">
	<section class="content-header col-12">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">Group</li>
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
						<h3 class="card-title">Group List</h3>
						<div class="card-tools"></div>
					</div>
					<div class="card-body table-responsive group-list">
						<table id="groupList" class="table table-hover text-nowrap" style="width: 100%;">
							<thead style="background-color: #f0f8ff;">
								<tr>
									<td>Name</td>
									<td style="width: 50px;">State</td>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<button class="btn btn-success float-right" id="addGroup">Add Group...</button>
			</div>
		</div>
		<div class="card card-primary mt-2 add-group-board" style="display: none;">
			<div class="card-body">
				<div class="error" style="display: none;"></div>
				<form id="profileForm">
					<div class="form-group">
						<label for="group">Group</label> <input type="text" id="addGroupName" class="form-control">
					</div>
				</form>
			</div>
			<div class="row mb-2" style="width: 100%;">
				<div class="col-12 text-right">
					<button class="btn btn-success float-right" id="addGroupTeamSave">Save</button>
				</div>
			</div>
		</div>
		<div class="card card-primary mt-2 modify-group-board" style="display: none;">
			<div class="card-body">
				<div class="error" style="display: none;"></div>
				<form id="profileForm">
					<div class="form-group">
						<label for="group">Group</label> <input type="text" id="modifyGroupName" class="form-control">
					</div>
					<div class="icheck-primary">
						<input type="checkbox" id="isActive"> <label for="isActive">Active</label>
					</div>
				</form>
			</div>
			<div class="row mb-2" style="width: 100%;">
				<div class="col-12 text-right">
					<button class="btn btn-success float-right" id="modifyGroupTeamSave">Save</button>
				</div>
			</div>
		</div>
	</section>
</div>
<script src="js/group.js?v=1"></script>