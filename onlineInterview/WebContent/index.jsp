<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8" />
		<title>南京大学软件学院</title>
		<!-- basic styles -->
		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->


		<!-- ace styles -->

		<link rel="stylesheet" href="assets/css/ace.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand" style="position:relative;left:50px">
						<small>
							<i class="icon-leaf"></i>
							南京大学软件学院
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<!-- <li class="purple">
							<a href="">登录.注册</a>
						</li> -->

						<!-- <li class="green">
							<a href="login.html">登录.注册</a>
						</li> -->

					</ul>
				</div>
			</div>
		</div>

		<div class="main-container" id="main-container">
		    <div class="main-container-inner">
		    	
					<div class="hr-32" ></div>
					<div class="">
						<div class="col-sm-1">
						</div>
						<div class="col-sm-10">
							<div class="row" style="height:410px">
								<div class="col-sm-7">
									<div class="tabbable">
										<ul class="nav nav-tabs" id="myTab">
											<li class="active">
												<a data-toggle="tab" href="#home">
													<i class="green icon-home bigger-110"></i>
														系统首页
												</a>
											</li>
											<li>
												<a data-toggle="tab" href="#profile">
													<i class="green icon-book bigger-110"></i>
														学院介绍
												</a>
											</li>

											<li class="dropdown">
												<a data-toggle="tab" href="#dropdown">
												<i class="green icon-download bigger-110"></i>
													下载专区 &nbsp;												
												</a>
											</li>
										</ul>

													<div class="tab-content">
														<div id="home" class="tab-pane in active" style="height:300px">
															<p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
														</div>

														<div id="profile" class="tab-pane" style="height:300px">
															<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid.</p>
														</div>

														<div id="dropdown" class="tab-pane" style="height:300px">
															<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
														</div>
													</div>
									</div>

								</div>
								<div class="col-sm-1">
								</div>
								<div class="col-sm-4" style="position:relative;left:-10px">
									<div class="row" style="position:relative;top:32px;height:334px;border:5px ridge #ACD6FF;background-color:#f0f0f0">
										<div class="col-sm-2">
										</div>
										<div class="col-sm-8">
											<div class="row">
												<div class="widget-main">
													<h4 class="header blue lighter bigger">
														<i class="icon-coffee green"></i>
														请输入你的信息
													</h4>

													<div class="space-6"></div>

													<form>
														<fieldset>
															<hr class="hr-4"/>
															<label class="block clearfix">
																<span class="block input-icon input-icon-right">
																	<input type="text" class="form-control" placeholder="账号" />
																	<i class="icon-user"></i>
																</span>
															</label>
															<hr class="hr-12"/>
															<label class="block clearfix">
																<span class="block input-icon input-icon-right">
																	<input type="password" class="form-control" placeholder="密码" />
																	<i class="icon-lock"></i>
																</span>
															</label>

															<div class="space"></div>

															<div class="clearfix">
																<label class="inline">
																	<input type="checkbox" class="ace" />
																	<span class="lbl"> 记住我</span>
																</label>

																<button type="button" class="width-35 pull-right btn btn-sm btn-primary">
																	<i class="icon-key"></i>
																	登录
																</button>
															</div>

															<div class="space-4"></div>
														</fieldset>
													</form>
													<div class="toolbar clearfix">
														<hr class="hr-8"/>
														<div class="row">
															
															<div class="col-sm-6">
																<a href="forget.html" class="forgot-password-link pull-left">
																	<i class="icon-arrow-left"></i>
																	忘了密码
																</a>
															</div>

															<div class="col-sm-6">
																<a href="signup.html" class="user-signup-link pull-right">
																	注册
																	<i class="icon-arrow-right"></i>
																</a>
															</div>
															
														</div>
													</div>
													
												</div>
											</div>
										</div>
										<div class="col-sm-2">
										</div>
									</div>
								</div>
							</div>
							<div class="hr-32" ></div>
							
							<hr style="height:1px;border:none;border-top:1px solid #555555;" />
							<div class="row">
								<div class="col-sm-4">
								</div>
								<div class="col-sm-4" style="position:relative;left:50px">
									<a href="">联系我们</a> &nbsp;&nbsp;| &nbsp;&nbsp; <a href="">院务信箱</a> &nbsp;&nbsp;| &nbsp;&nbsp; <a href="">教务信箱</a>
								</div>
								<div class="col-sm-4">
								</div>
							</div>
							<div class="hr-12" ></div>
							<div class="row">
								<div class="col-sm-4">
								</div>
								<div class="col-sm-4" style="position:relative;left:42px">
									Copyright@南京大学软件学院 2002-2015
								</div>
								<div class="col-sm-4">
								</div>
							</div>
						</div>
						
						<div class="col-sm-1">
						</div>
					</div>
				
			</div>		
		</div>
		
		<!-- basic scripts -->


		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/jquery.slimscroll.min.js"></script>
		<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="assets/js/jquery.sparkline.min.js"></script>
		<script src="assets/js/flot/jquery.flot.min.js"></script>
		<script src="assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="assets/js/flot/jquery.flot.resize.min.js"></script>

		<!-- ace scripts -->

		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			
			
		</script>


</body>
</html>