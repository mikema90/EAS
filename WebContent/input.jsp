<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Input</title>
</head>
<body>
	<form method="post" action="login">
		<select id = "identity" name="identity">
  		<option value="teacher">院系</option>
  		<option value="expert">专家</option>
  		<option value="admin">管理员</option>
		</select>
		<label for="username">用户名:</label>
		<input type="text" id="username" name="username"></input>
		<label for="pwd">密码:</label>
		<input type="text" id="pwd" name="pwd" />
		<label for="cer">验证码:</label>
		<input type="text" id="cer" name="cer" />
		<input type="submit" id="submit" name="submit" value = "Go"/>
	</form>
	<a href="http://localhost:8080/EAS/expert.doc" target="_self">专家</a>
	<a href="http://localhost:8080/EAS/teacher.doc" target="_self">院系</a>
</body>
</html>