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
		<label for="seller_id">seller id:</label>
		<input type="text" name="seller_id"></input>
		<label for="start_date">start date:</label>
		<input type="text" id="start_date" name="start_date" />
		<label for="end_date">end date:</label>
		<input type="text" id="end_date" name="end_date" />
		<input type="submit" id="submit" name="submit" value = "Go"/>
	</form>
</body>
</html>