<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		
		<!-- Bootstrap core CSS-->
		<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Custom fonts for this template-->
		<link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<!-- Custom styles for this template-->
		<link href="css/sb-admin.css" rel="stylesheet">
		
		<title>Sign up</title>
	</head>
	<body class="bg-dark">
	  <div class="container">
	    <div class="card card-register mx-auto mt-5">
	      <div class="card-header">Register an Account</div>
	      <div class="card-body">
	        <form action="/users/save" method="post">
	          <div class="form-group">
	            <div class="form-row">
	              <div class="col-md-12">
	                <label for="userId">UserId</label>
	                <input class="form-control" id="userId" name="userId" type="text" placeholder="Enter User ID">
	              </div>
	            </div>
	          </div>
	          <div class="form-group">
	            <div class="form-row">
	              <div class="col-md-12">
	                <label for="password">Password</label>
	                <input class="form-control" id="password" name="password" type="password" placeholder="Password">
	              </div>
	            </div>
	          </div>
	          <button class="btn btn-primary btn-block" type="submit">Register</button>
	        </form>
	        <div class="text-center">
	          <a class="d-block small mt-3" href="login.jsp">Login Page</a>
	          <a class="d-block small" href="forgot-password.jsp">Forgot Password?</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  <!-- Bootstrap core JavaScript-->
	  <script src="vendor/jquery/jquery.min.js"></script>
	  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	  <!-- Core plugin JavaScript-->
	  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
	</body>
</html>