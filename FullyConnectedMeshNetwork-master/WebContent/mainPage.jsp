<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>

</head>

<div id="Layer1"
	style="position: absolute; width: 100%; height: 100%; z-index: -1">
	<img src="image/2.jpg" height="100%" width="100%" />
</div>
<body>
<center>
			<form action="AddANode" method="post">
	<br />
	<div align="right">
		<a href="logout.jsp?close=close">Logout</a> <br>
	</div>
	<div>
		<center>
			<b><font face="Comic Sans MS" color=#8B2500 size="5"><br>Add
					a new Node to the network</font> </b>
	</div>
	<div>
		<td>
			<center>
				<b><font face="Comic Sans MS" color=#8B2500 size="2"><br>Group
						: </font> </b>
						 <select name="group">
					<option value="0" selected></option>
					<option value="group1">Group 1</option>
					<option value="group1">Group 2</option>
					<option value="group1">Group 3</option>
					<option value="group1">Group4</option>
				</select> <br />
		</td>

		<td>
			<center>
				<b><font face="Comic Sans MS" color=#8B2500 size="2"><br>Active
						: </font> </b>
						<input type="radio" name="active" value="active" > Active</input>
						<input type="radio" name="active" value="inactive">Inactive</input>
		</td>
		<td>
			<center>
				<b><font face="Comic Sans MS" color=#8B2500 size="2"><br>Connector
						: </font> </b>
						<input type="radio" name="connector" value="yes" > Yes</input>
						<input type="radio" name="connector" value="no">
						No</input>
		</td>
		<br />
<td>
<br />
			<center>
				<input type="submit" value="submit">
		</td>

		<div>
			<div>
				<center>
			</div>
</body>
</html>
