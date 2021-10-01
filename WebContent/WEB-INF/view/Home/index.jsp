<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Work Board</title>
<link rel="stylesheet" href="//fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="//code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/css/tempusdominus-bootstrap-4.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/icheck-bootstrap/3.0.1/icheck-bootstrap.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jqvmap/1.5.1/jqvmap.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/css/adminlte.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.1/css/OverlayScrollbars.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-bs4.min.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<!-- datatable -->
<link rel="stylesheet" href="//cdn.datatables.net/1.11.1/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="//cdn.datatables.net/rowreorder/1.2.8/css/rowReorder.dataTables.min.css">
<link rel="stylesheet" href="//cdn.datatables.net/responsive/2.2.9/css/responsive.dataTables.min.css">
<!-- datatable -->

<link rel="stylesheet" href="css/common.css?v=1">
<link rel="stylesheet" href="css/loader.css?v=1">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<div class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
		</div>
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
				</li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown">
					<a class="nav-link" data-toggle="dropdown" href="#">
						<i class="far fa-comments"></i>
						<span class="badge badge-danger navbar-badge">3</span>
					</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<a href="#" class="dropdown-item">
							<div class="media">
								<img src="dist/img/user1-128x128.jpg" alt="User Avatar" class="img-size-50 mr-3 img-circle">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										Brad Diesel <span class="float-right text-sm text-danger"><i class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">Call me whenever you can...</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item">
							<div class="media">
								<img src="dist/img/user8-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										John Pierce <span class="float-right text-sm text-muted"><i class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">I got your message bro</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item">
							<div class="media">
								<img src="dist/img/user3-128x128.jpg" alt="User Avatar" class="img-size-50 img-circle mr-3">
								<div class="media-body">
									<h3 class="dropdown-item-title">
										Nora Silvester <span class="float-right text-sm text-warning"><i class="fas fa-star"></i></span>
									</h3>
									<p class="text-sm">The subject goes here</p>
									<p class="text-sm text-muted">
										<i class="far fa-clock mr-1"></i> 4 Hours Ago
									</p>
								</div>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All Messages</a>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link" data-toggle="dropdown" href="#">
						<i class="far fa-bell"></i>
						<span class="badge badge-warning navbar-badge">15</span>
					</a>
					<div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
						<span class="dropdown-item dropdown-header">15 Notifications</span>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-envelope mr-2"></i> 4 new messages <span class="float-right text-muted text-sm">3 mins</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-users mr-2"></i> 8 friend requests <span class="float-right text-muted text-sm">12 hours</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item"> <i class="fas fa-file mr-2"></i> 3 new reports <span class="float-right text-muted text-sm">2 days</span>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item dropdown-footer">See All Notifications</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" data-widget="fullscreen" href="#" role="button">
						<i class="fas fa-expand-arrows-alt"></i>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#" role="button">
						<i class="fa fa-user"></i>
					</a>
				</li>
			</ul>
		</nav>
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<a href="." class="brand-link">
				<img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light">Work Board</span>
			</a>
			<div class="sidebar">
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="data:image/png;base64,${profileImage}" class="img-circle elevation-2" alt="User Image" id="mainImage">
					</div>
					<div class="info" id="mainName" style="color:#fff;'">
						${name}
					</div>
				</div>
				<nav class="mt-2" id="menu"></nav>
			</div>
		</aside>
		<div class="content-wrapper" id="mainContents">
		
		
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0">Dashboard</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">Dashboard v1</li>
							</ol>
						</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-3 col-6">
							<div class="small-box bg-info">
								<div class="inner">
									<h3>150</h3>
									<p>New Orders</p>
								</div>
								<div class="icon">
									<i class="ion ion-bag"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-6">
							<div class="small-box bg-success">
								<div class="inner">
									<h3>
										53<sup style="font-size: 20px">%</sup>
									</h3>
									<p>Bounce Rate</p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-6">
							<div class="small-box bg-warning">
								<div class="inner">
									<h3>44</h3>
									<p>User Registrations</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<div class="col-lg-3 col-6">
							<div class="small-box bg-danger">
								<div class="inner">
									<h3>65</h3>
									<p>Unique Visitors</p>
								</div>
								<div class="icon">
									<i class="ion ion-pie-graph"></i>
								</div>
								<a href="#" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<div class="row">
						<section class="col-lg-7 connectedSortable">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">
										<i class="fas fa-chart-pie mr-1"></i> Sales
									</h3>
									<div class="card-tools">
										<ul class="nav nav-pills ml-auto">
											<li class="nav-item"><a class="nav-link active" href="#revenue-chart" data-toggle="tab">Area</a></li>
											<li class="nav-item"><a class="nav-link" href="#sales-chart" data-toggle="tab">Donut</a></li>
										</ul>
									</div>
								</div>
								<div class="card-body">
									<div class="tab-content p-0">
										<div class="chart tab-pane active" id="revenue-chart" style="position: relative; height: 300px;">
											<canvas id="revenue-chart-canvas" height="300" style="height: 300px;"></canvas>
										</div>
										<div class="chart tab-pane" id="sales-chart" style="position: relative; height: 300px;">
											<canvas id="sales-chart-canvas" height="300" style="height: 300px;"></canvas>
										</div>
									</div>
								</div>
							</div>
							<div class="card direct-chat direct-chat-primary">
								<div class="card-header">
									<h3 class="card-title">Direct Chat</h3>
									<div class="card-tools">
										<span title="3 New Messages" class="badge badge-primary">3</span>
										<button type="button" class="btn btn-tool" data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-tool" title="Contacts" data-widget="chat-pane-toggle">
											<i class="fas fa-comments"></i>
										</button>
										<button type="button" class="btn btn-tool" data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<div class="direct-chat-messages">
										<div class="direct-chat-msg">
											<div class="direct-chat-infos clearfix">
												<span class="direct-chat-name float-left">Alexander Pierce</span> <span class="direct-chat-timestamp float-right">23 Jan 2:00 pm</span>
											</div>
											<img class="direct-chat-img" src="dist/img/user1-128x128.jpg" alt="message user image">
											<div class="direct-chat-text">Is this template really for free? That's unbelievable!</div>
										</div>
										<div class="direct-chat-msg right">
											<div class="direct-chat-infos clearfix">
												<span class="direct-chat-name float-right">Sarah Bullock</span> <span class="direct-chat-timestamp float-left">23 Jan 2:05 pm</span>
											</div>
											<img class="direct-chat-img" src="dist/img/user3-128x128.jpg" alt="message user image">
											<div class="direct-chat-text">You better believe it!</div>
										</div>
										<div class="direct-chat-msg">
											<div class="direct-chat-infos clearfix">
												<span class="direct-chat-name float-left">Alexander Pierce</span> <span class="direct-chat-timestamp float-right">23 Jan 5:37 pm</span>
											</div>
											<img class="direct-chat-img" src="dist/img/user1-128x128.jpg" alt="message user image">
											<div class="direct-chat-text">Working with AdminLTE on a great new app! Wanna join?</div>
										</div>
										<div class="direct-chat-msg right">
											<div class="direct-chat-infos clearfix">
												<span class="direct-chat-name float-right">Sarah Bullock</span> <span class="direct-chat-timestamp float-left">23 Jan 6:10 pm</span>
											</div>
											<img class="direct-chat-img" src="dist/img/user3-128x128.jpg" alt="message user image">
											<div class="direct-chat-text">I would love to.</div>
										</div>
									</div>
									<div class="direct-chat-contacts">
										<ul class="contacts-list">
											<li><a href="#"> <img class="contacts-list-img" src="dist/img/user1-128x128.jpg" alt="User Avatar">
													<div class="contacts-list-info">
														<span class="contacts-list-name"> Count Dracula <small class="contacts-list-date float-right">2/28/2015</small>
														</span> <span class="contacts-list-msg">How have you been? I was...</span>
													</div>
											</a></li>
											<li><a href="#"> <img class="contacts-list-img" src="dist/img/user7-128x128.jpg" alt="User Avatar">

													<div class="contacts-list-info">
														<span class="contacts-list-name"> Sarah Doe <small class="contacts-list-date float-right">2/23/2015</small>
														</span> <span class="contacts-list-msg">I will be waiting for...</span>
													</div>
											</a></li>
											<li><a href="#"> <img class="contacts-list-img" src="dist/img/user3-128x128.jpg" alt="User Avatar">
													<div class="contacts-list-info">
														<span class="contacts-list-name"> Nadia Jolie <small class="contacts-list-date float-right">2/20/2015</small>
														</span> <span class="contacts-list-msg">I'll call you back at...</span>
													</div>
											</a></li>
											<li><a href="#"> <img class="contacts-list-img" src="dist/img/user5-128x128.jpg" alt="User Avatar">
													<div class="contacts-list-info">
														<span class="contacts-list-name"> Nora S. Vans <small class="contacts-list-date float-right">2/10/2015</small>
														</span> <span class="contacts-list-msg">Where is your new...</span>
													</div>
											</a></li>
											<li><a href="#"> <img class="contacts-list-img" src="dist/img/user6-128x128.jpg" alt="User Avatar">
													<div class="contacts-list-info">
														<span class="contacts-list-name"> John K. <small class="contacts-list-date float-right">1/27/2015</small>
														</span> <span class="contacts-list-msg">Can I take a look at...</span>
													</div>
											</a></li>
											<li><a href="#"> <img class="contacts-list-img" src="dist/img/user8-128x128.jpg" alt="User Avatar">

													<div class="contacts-list-info">
														<span class="contacts-list-name"> Kenneth M. <small class="contacts-list-date float-right">1/4/2015</small>
														</span> <span class="contacts-list-msg">Never mind I found...</span>
													</div>
											</a></li>
										</ul>
									</div>
								</div>
								<div class="card-footer">
									<form action="#" method="post">
										<div class="input-group">
											<input type="text" name="message" placeholder="Type Message ..." class="form-control"> <span class="input-group-append">
												<button type="button" class="btn btn-primary">Send</button>
											</span>
										</div>
									</form>
								</div>
							</div>
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">
										<i class="ion ion-clipboard mr-1"></i> To Do List
									</h3>
									<div class="card-tools">
										<ul class="pagination pagination-sm">
											<li class="page-item"><a href="#" class="page-link">&laquo;</a></li>
											<li class="page-item"><a href="#" class="page-link">1</a></li>
											<li class="page-item"><a href="#" class="page-link">2</a></li>
											<li class="page-item"><a href="#" class="page-link">3</a></li>
											<li class="page-item"><a href="#" class="page-link">&raquo;</a></li>
										</ul>
									</div>
								</div>
								<div class="card-body">
									<ul class="todo-list" data-widget="todo-list">
										<li><span class="handle"> <i class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo1" id="todoCheck1"> <label for="todoCheck1"></label>
											</div> <span class="text">Design a nice theme</span> <small class="badge badge-danger"><i class="far fa-clock"></i> 2 mins</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo2" id="todoCheck2" checked> <label for="todoCheck2"></label>
											</div> <span class="text">Make the theme responsive</span> <small class="badge badge-info"><i class="far fa-clock"></i> 4 hours</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo3" id="todoCheck3"> <label for="todoCheck3"></label>
											</div> <span class="text">Let theme shine like a star</span> <small class="badge badge-warning"><i class="far fa-clock"></i> 1 day</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo4" id="todoCheck4"> <label for="todoCheck4"></label>
											</div> <span class="text">Let theme shine like a star</span> <small class="badge badge-success"><i class="far fa-clock"></i> 3 days</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo5" id="todoCheck5"> <label for="todoCheck5"></label>
											</div> <span class="text">Check your messages and notifications</span> <small class="badge badge-primary"><i class="far fa-clock"></i> 1 week</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
										<li><span class="handle"> <i class="fas fa-ellipsis-v"></i> <i class="fas fa-ellipsis-v"></i>
										</span>
											<div class="icheck-primary d-inline ml-2">
												<input type="checkbox" value="" name="todo6" id="todoCheck6"> <label for="todoCheck6"></label>
											</div> <span class="text">Let theme shine like a star</span> <small class="badge badge-secondary"><i class="far fa-clock"></i> 1 month</small>
											<div class="tools">
												<i class="fas fa-edit"></i> <i class="fas fa-trash-o"></i>
											</div></li>
									</ul>
								</div>
								<div class="card-footer clearfix">
									<button type="button" class="btn btn-primary float-right">
										<i class="fas fa-plus"></i> Add item
									</button>
								</div>
							</div>
						</section>
						<section class="col-lg-5 connectedSortable">
							<div class="card bg-gradient-primary">
								<div class="card-header border-0">
									<h3 class="card-title">
										<i class="fas fa-map-marker-alt mr-1"></i> Visitors
									</h3>
									<div class="card-tools">
										<button type="button" class="btn btn-primary btn-sm daterange" title="Date range">
											<i class="far fa-calendar-alt"></i>
										</button>
										<button type="button" class="btn btn-primary btn-sm" data-card-widget="collapse" title="Collapse">
											<i class="fas fa-minus"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<div id="world-map" style="height: 250px; width: 100%;"></div>
								</div>
								<div class="card-footer bg-transparent">
									<div class="row">
										<div class="col-4 text-center">
											<div id="sparkline-1"></div>
											<div class="text-white">Visitors</div>
										</div>
										<div class="col-4 text-center">
											<div id="sparkline-2"></div>
											<div class="text-white">Online</div>
										</div>
										<div class="col-4 text-center">
											<div id="sparkline-3"></div>
											<div class="text-white">Sales</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card bg-gradient-info">
								<div class="card-header border-0">
									<h3 class="card-title">
										<i class="fas fa-th mr-1"></i> Sales Graph
									</h3>
									<div class="card-tools">
										<button type="button" class="btn bg-info btn-sm" data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn bg-info btn-sm" data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body">
									<canvas class="chart" id="line-chart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
							</div>
							<div class="card bg-gradient-success">
								<div class="card-header border-0">
									<h3 class="card-title">
										<i class="far fa-calendar-alt"></i> Calendar
									</h3>
									<div class="card-tools">
										<div class="btn-group">
											<button type="button" class="btn btn-success btn-sm dropdown-toggle" data-toggle="dropdown" data-offset="-52">
												<i class="fas fa-bars"></i>
											</button>
											<div class="dropdown-menu" role="menu">
												<a href="#" class="dropdown-item">Add new event</a> <a href="#" class="dropdown-item">Clear events</a>
												<div class="dropdown-divider"></div>
												<a href="#" class="dropdown-item">View calendar</a>
											</div>
										</div>
										<button type="button" class="btn btn-success btn-sm" data-card-widget="collapse">
											<i class="fas fa-minus"></i>
										</button>
										<button type="button" class="btn btn-success btn-sm" data-card-widget="remove">
											<i class="fas fa-times"></i>
										</button>
									</div>
								</div>
								<div class="card-body pt-0">
									<div id="calendar" style="width: 100%"></div>
								</div>
							</div>
						</section>
					</div>
				</div>
			</section>
			
			
			
			
		</div>
		<footer class="main-footer">
			<strong>Copyright &copy; 2021- <a href="https://www.nowonbun.com" target="_blank">www.nowonbun.com</a>.
			</strong> All rights reserved.
			<div class="float-right d-none d-sm-inline-block">
				<b>Version</b> 0.0.1
			</div>
		</footer>
		<aside class="control-sidebar control-sidebar-dark"></aside>
	</div>
	<script src="//code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button)
	</script>
	<script src="//cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"></script>
	<script src="//adminlte.io/themes/dev/AdminLTE/plugins/sparklines/sparkline.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jqvmap/1.5.1/jquery.vmap.min.js"></script>
	<script src="//adminlte.io/themes/dev/AdminLTE/plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jQuery-Knob/1.2.13/jquery.knob.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.39.0/js/tempusdominus-bootstrap-4.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/overlayscrollbars/1.13.1/js/OverlayScrollbars.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/admin-lte/3.1.0/js/adminlte.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
	<!-- datatable -->
	<script src="//cdn.datatables.net/1.11.1/js/jquery.dataTables.min.js"></script>
	<script src="//cdn.datatables.net/1.11.1/js/dataTables.bootstrap4.min.js"></script>
	<script src="//cdn.datatables.net/rowreorder/1.2.8/js/dataTables.rowReorder.min.js"></script>
	<script src="//cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"></script>
	<!-- datatable -->
	
	<!-- AdminLTE for demo purposes -->
	<script src="dist/js/demo.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="dist/js/dashboard.js"></script>
	<script src="js/common.js?v=1"></script>
	<script src="js/loader.js?v=1"></script>
</body>
</html>