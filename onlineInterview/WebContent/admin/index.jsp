<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Index</title>
</head>
<body>
	<p>管理员首页</p>
	<div id="wrapper">
		<div id="tabs">
			<form name="adminOperate" action="" method="post">
				<button name="listStudents" onclick="list()" style="display:block;margin:10px">查看报名</button>
				<div style="display:block;margin:10px">
					<input id="arrange"name="arrange" type="file"></input>
					<button name="uploadArrange" onclick="uploadArrange()">上传面试安排</button>
				</div>
				<button name="teacherManage" onclick="teacherManage()"style="display:block;margin:10px">教师管理</button>
				<button name="downloadRecords" onclick="downloadRecords()"style="display:block;margin:10px">导出面试记录</button>
			</form>
		</div>
		<div id="content" >
			<div id="studentsList" style="display:none">
			</div>
			<div id="uploadArrange" style="display:none">
			</div>
			<div id="teacherManage" style="display:none">
			</div>
			<div id="downloadRecords" style="display:none">
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function list() {
		document.adminOperate.action="listStudents";
		document.adminOperate.submit();
	}
	
	function uploadArrange(){
		document.adminOperate.action="uploadArrange";
		document.adminOperate.submit();
	}
	
	function teacherManage(){
		document.adminOperate.action="teacherManage";
		document.adminOperate.subimt();
	}
	
	function downloadRecords(){
		document.adminOperate.action="downloadRecords";
		document.adminOperate.submit();
		
	}
	
	
</script>
</html>