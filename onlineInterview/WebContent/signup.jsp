<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
    
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
								<div class="col-sm-4">
								</div>
								<div class="col-sm-4">
									<div class="">
												<div class="widget-main">
													<div class="toolbar left">
														<a href='<s:url action="index"/>' class="back-to-login-link">
															<i class="icon-arrow-left"></i>
															返回登录
														</a>
													</div>
													<h4 class="header green lighter bigger">
														<i class="icon-group blue"></i>
														新用户注册
													</h4>

													<div class="space-6"></div>
													<p> 开始输入你的信息: </p>

													<form name="signupForm" method="post" action="signup">
														<fieldset>
															<label class="block clearfix">
																<span class="block input-icon input-icon-right">
																	<input type="email" name="email" class="form-control" placeholder="邮箱" />
																	<i class="icon-envelope"></i>
																</span>
															</label>

															<label class="block clearfix">
																<span class="block input-icon input-icon-right">
																	<input type="text" name="studentName" class="form-control" placeholder="姓名（请使用真实姓名）" />
																	<i class="icon-user"></i>
																</span>
															</label>

															<label class="block clearfix">
																<span class="block input-icon input-icon-right">
																	<input type="password" name="password" class="form-control" placeholder="密码" />
																	<i class="icon-lock"></i>
																</span>
															</label>

															<label class="block clearfix">
																<span class="block input-icon input-icon-right">
																	<input type="password" name="confirmPassword"class="form-control" placeholder="确认密码" />
																	<i class="icon-retweet"></i>
																</span>
															</label>

															<label class="block">
																<input type="checkbox" class="ace" />
																<span class="lbl">
																	我接受
																	<a href="#">用户协议</a>
																</span>
															</label>

															<div class="space-24"></div>

															<div class="clearfix">
																<button type="reset" class="width-30 pull-left btn btn-sm">
																	<i class="icon-refresh"></i>
																	重置
																</button>

																<button type="submit" class="width-65 pull-right btn btn-sm btn-success" >
																	注册
																	<i class="icon-arrow-right icon-on-right"></i>
																</button>
															</div>
														</fieldset>
													</form>
												</div>

												
									</div><!-- /widget-body -->
								</div>
								
								<div class="col-sm-4">
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