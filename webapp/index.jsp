<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="basic.Device" %>

<%
	
		if(session.getAttribute("userId") == null) {
			pageContext.forward("/users/login");
		}
	
	Device[] devices = (Device[])session.getAttribute("devices");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width initial-scale=1" >

		<!-- Bootstrap -->
		<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
		
		<!-- JQuery -->
		<script src="vendor/jquery/jquery.min.js"></script>
		
		<!-- IP address -->
		<script type="text/javascript" src="http://jsgetip.appspot.com"></script>
		
		<!-- Custom Styles -->
		<link rel="stylesheet" href="./css/index.css">

		<title>Hand vis</title>
	</head>
	<body>
		<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
			<h5 class="my-0 mr-md-auto font-weight-normal">HandVis</h5>
			<nav class="my2 my-md-0 mr-md-3">
				<%
					String userId = (String)session.getAttribute("userId");
					if(userId == null) {
						userId = "Stranger";
					}
				%>
				<span class="p-2">Hello <span class="text-capitalize font-italic text-monospace"><%=userId %> !</span></span>
				<a class="p-2" href="#">User</a>
				<a class="p-2" href="#">Devices</a>
				<a class="p-2" href="#">Support</a>
			</nav>
			<a class="btn btn-sm btn-outline-primary" href="/users/logout">Logout</a>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-8">
					<img id="vid" src="" alt="" style="max-height: 95vh">
					<div style="margin: 0 auto; text-align: center">
						<input type="button" class="btn btn-success" onclick="openpose('START');" value="START">
						<input type="button" class="btn btn-danger" onclick="openpose('END');" value="END">
					</div>
				</div>
				<div class="col-sm-4">
					<div class="row card d-flex flex-grow-1 mr-2 align-items-start mb-4">
						<p class="text-center w-100 m-auto"><span class="font-weight-bold">Gesture Instruction</span></p>
						<table class="table">
							<tbody>
								<tr>
									<td><img src="images/chk.png" alt="" height="100px"></td>
									<td><img src="images/1_finger.png" alt="" height="100px"></td>
									<td><img src="images/2_finger.png" alt="" height="100px"></td>
								</tr>
								<tr>
									<td>
										<a href="#">
											<div style="width:100%; height:100%" class="text-center">
												<% if(devices != null && devices[0] != null) { %>
													<span style="text-decoration: none; color: #000">${devices[0].getName() }</span>
												<%} else {%>
													<span style="text-decoration: none; color: #000">없음</span>
												<%} %>
											</div>
										</a>
									</td>
									<td>
										<a href="#">
											<div style="width:100%; height:100%" class="text-center">
												<% if(devices != null && devices.length > 1 && devices[1] != null) { %>
													<span style="text-decoration: none; color: #000">${devices[1].getName() }</span>
												<%} else {%>
													<span style="text-decoration: none; color: #000">없음</span>
												<%} %>
											</div>
										</a>
									</td>
									<td>
										<a href="#">
											<div style="width:100%; height:100%" class="text-center">
												<% if(devices != null && devices.length > 2 && devices[2] != null) { %>
												<span style="text-decoration: none; color: #000">${devices[2].getName() }</span>
												<%} else {%>
													<span style="text-decoration: none; color: #000">없음</span>
												<%} %>
											</div>
										</a>
									</td>
								</tr>
								<tr>
									<td><img src="images/3_finger.png" alt="" height="100px"></td>
									<td><img src="images/4_finger.png" alt="" height="100px"></td>
									<td><img src="images/5_finger.png" alt="" height="100px"></td>
								</tr>
								<tr>
									<td>
										<a href="#">
											<div style="width:100%; height:100%" class="text-center">
												<% if(devices != null && devices.length > 3 && devices[3] != null) { %>
													<span style="text-decoration: none; color: #000">${devices[3].getName() }</span>
												<%} else {%>
													<span style="text-decoration: none; color: #000">없음</span>
												<%} %>
											</div>
										</a>
									</td>
									
									<td>
										<a href="#">
											<div style="width:100%; height:100%" class="text-center">
												<% if(devices != null && devices.length > 4 && devices[4] != null) { %>
													<span style="text-decoration: none; color: #000">${devices[4].getName() }</span>
												<%} else {%>
													<span style="text-decoration: none; color: #000">없음</span>
												<%} %>
											</div>
										</a>
									</td>
									<td>
										<a href="#">
											<div style="width:100%; height:100%" class="text-center">
												<% if(devices != null && devices.length > 5 && devices[5] != null) { %>
													<span style="text-decoration: none; color: #000">${device[5].getName() }</span>
												<%} else {%>
													<span style="text-decoration: none; color: #000">없음</span>
												<%} %>
											</div>
										</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="row card d-flex flex-grow-1 mr-2 mb-4 text-center">
						<table>
							<thead>
								<tr><span class="font-weight-bold">Current Action</span></tr>
							</thead>
							<tbody>
								<td><span id="current" class="font-weight-light" style="font-size: 3em">No Action!</span></td>
							</tbody>
						</table>
					</div>
					<div class="row card d-flex flex-grow-1 mr-2">
						<label for="IPaddr" class="ml-2 mr-2 mu-2 font-weight-bold">IP Address</label>
						<div>
							<input type="text" id="IPaddr" class="col-sm-8">
							<input type="button" onclick="changeCamera();" value="Change" class="col-sm-2 btn btn-info">
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			var Addr;
			$(document).ready(function() {
				var currentAction = $('#current');
				setInterval(() => {
					$.ajax({
						url: "http://165.246.243.156:9999/api/ajax",
						type: "GET",
						success: function(data) {
							currentAction.text(data['value']);
						}
					})
				}, 1000);
				
				/*
				openpose("START");

				Addr = "http://" + ip() + ":8080/video";
				$("#vid").removeAttr("src").attr("src", Addr);
				*/
			});

			function changeCamera() {
				openpose("END");

				setTimeout(openpose("CHANGE"), 1000);
				
				//openpose("CHANGE");
				
				Addr = "http://" + $("#IPaddr").val() + ":8080/video";
				$("#IPaddr").val("");
				$("#vid").removeAttr("src").attr("src", Addr);
			};
			
			function openpose(st, callbackFunc) {
				if(st === "END") {
					$("#vid").removeAttr("src").attr("src", "");
					$.post("api/openpose", {state: 2});
				}else if(st === "CHANGE") {
					$.post("/api/openpose", {state: 1, addr: $("#IPaddr").val()});
					Addr = "http://" + $("#IPaddr").val() + ":8080/video";
					$("#vid").removeAttr("src").attr("src", Addr);
				}else {
					$.post("/api/openpose", {state: 1, addr: ip()});
					Addr = "http://" + ip() + ":8080/video";
					$("#vid").removeAttr("src").attr("src", Addr);
				}
			}

		</script>
	</body>
</html>