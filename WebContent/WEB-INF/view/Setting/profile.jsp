<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.error {
		border: 1px dotted red;
    	border-radius: 5px;
    	padding: 10px;
    	margin-bottom: 20px;
    	color: red;
	}
</style>
<div class="row" style="margin:0px!important;">
	<section class="content-header col-12">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-6"></div>
				<div class="col-6">
					<ol class="breadcrumb float-right">
						<li class="breadcrumb-item">Home</li>
						<li class="breadcrumb-item active">Profile</li>
					</ol>
				</div>
			</div>
		</div>
	</section>
	<section class="content max-1200 col-12">
		<div class="row">
			<div class="col-12">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">Profile</h3>
						<div class="card-tools"></div>
					</div>
					<div class="card-body">
						<div class="error" style="display:none;"></div>
						<form id="profileForm">
							<div class="form-group">
								<label for="company">Company</label> 
								<input type="text" id="company" class="form-control" readonly="readonly" disabled="disabled">
							</div>
							<div class="form-group">
								<label for="company">Group</label> 
								<input type="text" id="group" class="form-control" readonly="readonly" disabled="disabled">
							</div>
							<div class="form-group">
								<label for="uid">ID</label> 
								<input type="text" id="uid" class="form-control" readonly="readonly" disabled="disabled">
							</div>
							<div class="form-group">
								<label for="name">Name</label>
								<input type="text" id="name" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">Password</label>
								<input type="password" id="password" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">Check password</label>
								<input type="password" id="checkPassword" class="form-control">
							</div>
							<div class="form-group">
								<label for="image">Image</label> 
								<img class="form-control img-apply" id="image" style="width:120px;height:120px;cursor: pointer;">
							</div>
							<div class="icheck-primary">
		              			<input type="checkbox" id="isAdmin">
		              			<label for="isAdmin">Permission Admin</label>
		              		</div>
	              		</form>
					</div>
					<div class="row mb-2" style="width:100%;">
						<div class="col-12 text-right">
							<button class="btn btn-success float-right" id="profileSave">Save</button> 
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="modal fade" id="imageApplyModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Profile Image</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
	            </div>
	            <div class="modal-body">
	            	<div class="custom-file">
	            		<label>Picture size 100 * 100, File size: 1MB limit.</label>
	            		<input type="file" class="custom-file-input" id="imageFile" accept="image/*">
	            		<label class="custom-file-label" for="imageFile">Choose Image file</label>
	            	</div>
	            </div>
	            <div class="modal-footer justify-content-between">
	            	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	            	<button type="button" class="btn btn-primary" id="imageChage">Image Changes</button>
	            </div>
			</div>
		</div>
	</div>
</div>
<script src="js/profile.js?v=1"></script>