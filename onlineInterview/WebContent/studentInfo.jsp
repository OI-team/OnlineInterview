<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理</title>
</head>
<body>
<center>  
  <h1>个人信息中心</h1>  
  <form name="studentInfoForm" action="studentInfo" method="post">  
  <table border="1" bgcolor="#0099CC">  
    <tr>  
      <td>        姓名:  
        <input type="text" name="stuname">  
      </td>  
    </tr>  
   <tr>
   <td>           性别:  
    <input type="radio" name="stusex" value="男" checked="checked" /> 男
    <input type="radio" name="stusex" value="女" /> 女
   </td>
  </tr>
     <tr>  
      <td>        学校:  
        <input type="text" name="stuschool">  
      </td>  
    </tr>  
    <tr>  
      <td>        专业:  
        <input type="text" name="stumajor">  
      </td>  
    </tr>  
    <tr>  
      <td>        学号:  
        <input type="text" name="stunum">  
      </td>  
    </tr>
    <tr>  
      <td>        排名:  
        <input type="text" name="sturank">  
      </td>  
    </tr>    
    <tr>  
      <td>        电话:  
        <input type="text" name="stuphone">  
      </td>  
    </tr>  
    <tr>  
      <td>  
        <input type=reset value=重置> <input type=submit value=提交>  
      </td>  
    </tr>  
  </table>  
  </form>  
</center> 
</body>
</html>