<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
	    <!-- Boostrap -->
	    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
	    
	    <!-- font-awesome -->
	    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.css">
	    
	    <title>Log In</title>
	</head>
	<body class="bg-dark">
	    <div class="container">
	        <div class="card mt-5 mx-auto">
	            <div class="card-header">Login</div>
	            <div class="card-body">
	                <form action="/users/login" method="post">
	                    <div class="form-group">
	                        <label for="userId">UserId</label>
	                        <input type="text" class="form-control" name="userId" placeholder="UserId">
	                    </div>
	                    <div class="form-group">
	                        <label for="passowrd">Password</label>
	                        <input type="password" class="form-control" name="password" placeholder="Password">
	                    </div>
	                    <button type="submit" class="btn btn-primary">Login</button>
	                </form>
	            </div>
	        </div>
	    </div>
	    <!-- Boostrap javascript -->
	    <script src="vendor/bootstrap/js/bootstrap.bundle.js"></script>
	</body>
</html>