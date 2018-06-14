<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap -->
		<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
		
		<!-- JQuery -->
		<script src="vendor/jquery/jquery.min.js"></script>
		
		<title>Hand vis</title>
		
	</head>
	<body>
		<div class="container-fluid">
		<!--
			<div class="row">
			 
				<%
					String id = (String)session.getAttribute("userId");
					if(id != null) {
				%>
					<p>User : <%=id %>   <p>
					<p><a href="/users/logout">Logout</a></p>
				<%
					} else {
				%>
					<p style="margin-left: 10px">Unknown User</p>
				<%} %>
				
				<a href="/api/update" style="margin-left: 10px">Update</a>
			 
			</div>
			-->
			<div class="row">
				<div class="col-sm-8">
					<div>
						<img id="vd" alt="video" src="http://165.246.222.37:8080/video" width="800" height="600" />
					</div>
				</div>
				<div class="col-sm-4">
					<table class="table">
						<thead>
							<tr>
								<th scope="col" colspan="5">Instruction</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><img src="images/1_finger.png" alt="" width="100px"
									height="100px"></td>
								<td><img src="images/2_finger.png" alt="" width="100px"
									height="100px"></td>
								<td><img src="images/3_finger.png" alt="" width="100px"
									height="100px"></td>
							</tr>
							<tr>
								<td>Action 1</td>
								<td>Action 2</td>
								<td>Action 3</td>
							</tr>
							<tr>
								<td><img src="images/4_finger.png" alt="" width="100px"
									height="100px"></td>
								<td><img src="images/5_finger.png" alt="" width="100px"
									height="100px"></td>
								<td><img src="images/fff.png" alt="" width="100px"
									height="100px"></td>
							</tr>
							<tr>
								<td>Action 4</td>
								<td>Action 5</td>
								<td>Not Available</td>
							</tr>
						</tbody>
					</table>
					<table class="table">
						<thead>
							<tr>
								<th scope="col" colspan="5">Current Action</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<h1 id="go">No Action!</h1> <!-- <img src="images/fff.png" alt="test" width="100%" height="100%"> -->
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function() {
				var video = document.querySelector("video");
				var img = document.querySelector("#test");
				
				var constraints = {
						video: true,
						audio: false
				};
				
				/*
				navigator.mediaDevices.getUserMedia(constraints).then(function(stream) {
					video.srcObject = stream;
					video.play();
				}).catch(function(err) {
					console.log(err);
				});
				*/
				var g = $("#go");
				setInterval(() => {
					$.ajax({
						url: "http://165.246.243.33:9999/api/ajax",
						type: "GET",
						success: function(data) {
							console.log(data);
							g.text(data['value']);
						}
					})
				}, 1000);
			});
		</script>
	</body>
</html>