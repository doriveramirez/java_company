<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeLeaf/layout"
	layout:decorate="~{fragments/main_layout}">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Register</h1>
	<div layout:fragment="content" class="container mySpace">
		<div class="alert alert-info" th:if="${exist}">
			<p class="text text-center">This username already exist</p>
		</div>
		<form th:action="@{/register}" th:object="${user}" method="post">
			<div class="form-group">
				<label for="username" class="form-control-label">Username</label> <input
					type='text' class="form-control-label" th:field='*{username}'
					id="username">
				<div class="text text-danger"
					th:if="${fields.hasErrors('username')}">Error: empty username</div>
			</div>
			<div class="form-group">
				<label for="password" class="form-control-label">Password</label> <input
					type='password' class="form-control-label" th:field='*{password}'
					id="password">
				<div class="text text-danger"
					th:if="${fields.hasErrors('password')}">Error: empty password</div>
			</div>
			<input type="submit" value="Submit" class="btn btn-primary" />
		</form>
	</div>
</body>
</html>