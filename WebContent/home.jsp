<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
   	uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>

<h1 style="text-align:center">Instructor Page</h1>
<div class="col-md-4" style="border:solid">
	<h3>Add Student:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="Add Student" readonly><br>
	Student Name: <input type="text" name="studentName" required> (required)<br> 
	<input type="submit" value="Submit">
	</form>
	
	<h3>Add Workitem:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="Add WorkItem" readonly><br>
	Work Item Name: <input type="text" name="workitemname" required> (required)<br>
	Percentage Allocation: <input type="text" name="percentage" required> (required)<br>
	<input type="submit" value="Submit">
	</form>
	
	<h3>Add Student Grade:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="Add Grade" readonly><br>
	Student ID: <input type="text" name="studentID" required> (required)<br>
	Assignment: <input type="text" name="assignID" required> (required)<br>
	Grade: <input type="text" name="Grade" required> (required)<br>
	Feedback: <input type="text" name="feedback" required> (required)<br>
	<input type="submit" value="Submit">
	</form>
	
	<h3>Update Student Grade:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="Update Grade" readonly><br>
	Student ID: <input type="text" name="studentID" required> (required)<br>
	Assignment: <input type="text" name="assignID" required> (required)<br>
	Grade: <input type="text" name="Grade" required> (required)<br>
	Feedback: <input type="text" name="feedback" required> (required)<br>
	<input type="submit" value="Submit">
	</form>
	
	<h3>View Student Grade:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="View Grade" readonly><br>
	Student ID: <input type="text" name="studentID" required> (required)<br>
	Assignment: <input type="text" name="assignID"> (optional)<br>
	<input type="submit" value="Submit">
	</form>
	
	<h3>Delete Student Grade:</h3>
	<form method="post" action="${pageContext.request.contextPath}/submit">
	Task: <input type="text" name="task" value="Delete Grade" readonly><br>
	Student ID: <input type="text" name="studentID" required> (required)<br>
	Assignment: <input type="text" name="assignID"> (optional)<br>
	<input type="submit" value="Submit">
	</form>
</div>
<div class="col-md-2"></div>
<div class="col-md-4" id="ouput" style="margin-top:300px; border:solid">
<h3>Response:</h3> <p>${message}</p>
</div>
</body>
</html>