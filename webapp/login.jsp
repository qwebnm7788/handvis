<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width" initial-scale="1">

	    <!-- Boostrap -->
	    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
	    
	    <!-- font-awesome -->
	    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.css">
		
		<!-- Custom Styles -->
		<link rel="stylesheet" href="./css/login.css">

	    <title>Log In</title>
	</head>
	<body class="text-center">
		<form class="form-login" action="/users/login" method="post">
			<h1 class="h3 mb-3 font-weight-normal">Please LogIn</h1>
			<label for="inputId" class="sr-only">User Id</label>
			<input type="text" class="form-control mb-2" id="inputId" name="userId" placeholder="User ID" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password" required>
			<button type="submit" class="btn btn-lg btn-primary btn-block">Login</button>
			<p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
		</form>
	</body>
</html>