<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>教师视频页面</title>
</head>
<body>
	<form method="post" name="commentForm" action="addRecord">
		学生id<input type="text" name="record.s_id"><br/>
		分数<input type="text" name="record.score"><br/>
		评语<input type="text" name="record.comment"><br/>
		照片路径<input type="text" name="record.snap"><br/>
		视频路径<input type="text" name="record.video"><br/>
		<input type="submit" value="提交"><input type="reset" value="重置">
	</form>

</body>
</html>