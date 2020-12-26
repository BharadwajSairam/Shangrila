<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="le.ac.*"%>
<%
	HttpSession se = request.getSession();
User u = null;
if (se.getAttribute("User") != null) {
	u = (User) se.getAttribute("User");
} else {
	response.sendRedirect("error.jsp?errorid=2");
}
if (u != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report Submission</title>
</head>
<body>
	<div>
		Welcome,
		<%=u.getName()%>! <br />
	</div>
	<a href="./Logout">Logout</a>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<script type="text/javascript">
		window.onload = function() {

			var data1;
			var data2, data3;
			console.log("Hellooooo");
			$.ajax({
				type : "GET",
				url : "/CW2Beta/Chart?ID=1",
				async : false,
				dataType : "json",
				success : function(result) {
					data1 = result;
					console.log("result :" + result + ":::");
				}
			});
			$.ajax({
				type : "GET",
				url : "/CW2Beta/Chart?ID=2",
				async : false,
				dataType : "json",
				success : function(result) {
					data2 = result;
					console.log("result :" + result + ":::");
				}
			});
			$.ajax({
				type : "GET",
				url : "/CW2Beta/Chart?ID=3",
				async : false,
				dataType : "json",
				success : function(result) {
					data3 = result;
					console.log("result :" + result + ":::");
				}
			});
			console.log("result3 :" + data1 + ":::");

			console.log("result2 :" + data2 + ":::");
			var datapie = JSON.parse(data1.data);
			var datas = JSON.parse(data2.data);
			var dataage = JSON.parse(data3.data);
			console.log(datas);
			var chart1 = new CanvasJS.Chart("chartContainer", {
				theme : "light2",
				animationEnabled : true,
				exportFileName : "New Year Resolutions",
				exportEnabled : true,
				title : {
					text : "Positive/Negative Cases"
				},
				data : [ {
					type : "pie",
					showInLegend : true,
					legendText : "{label}",
					toolTipContent : "{label}: <strong>{y}</strong>",
					indexLabel : "{label}",
					dataPoints : datapie
				} ]
			});
			var chart2 = new CanvasJS.Chart("chartContainer2", {
				theme : "light2",
				animationEnabled : true,
				exportFileName : "New Year Resolutions",
				exportEnabled : true,
				title : {
					text : "Positive cases per post code"
				},
				data : [ {
					type : "doughnut",
					showInLegend : true,
					legendText : "{label}",
					toolTipContent : "{label}: <strong>{y}</strong>",
					indexLabel : "{label}",
					dataPoints : datas
				} ]
			});

			var chart3 = new CanvasJS.Chart("chartContainer1", {
				theme : "light2",
				animationEnabled : true,
				exportFileName : "New Year Resolutions",
				exportEnabled : true,
				title : {
					text : "Positive Cases per age group"
				},
				data : [ {
					type : "column",
					showInLegend : true,
					legendText : "{label}",
					toolTipContent : "{label}: <strong>{y}</strong>",
					indexLabel : "{label}",
					dataPoints : dataage
				} ]
			});
			chart1.render();

			chart2.render();

			chart3.render();

		}
		$(document).ready(function() {

			var data4;
			$("#age").mouseup(function() {
				$.ajax({
					type : "GET",
					url : "/CW2Beta/Chart?ID=" + $("#age").val(),
					async : false,
					dataType : "json",
					success : function(result) {
						data4 = result;
						console.log("result :" + result + ":::");
					}
				})
				var datan = JSON.parse(data4.data);
				var chart4 = new CanvasJS.Chart("chartContainer3", {
					theme : "light2",
					animationEnabled : true,
					exportFileName : "New Year Resolutions",
					exportEnabled : true,
					title : {
						text : "Positive Cases per postcode per ageGroup"
					},
					data : [ {
						type : "column",
						showInLegend : true,
						legendText : "{label}",
						toolTipContent : "{label}: <strong>{y}</strong>",
						indexLabel : "{label}",
						dataPoints : datan
					} ]
				});
				chart4.render();
			})
		})
	</script>

	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<div id="chartContainer2" style="height: 370px; width: 100%;"></div>
	<div id="chartContainer1" style="height: 370px; width: 100%;"></div>

	<label for="Age_Group">Select the age group : </label>

	<select id="age" name="ages">
		<option value="0-20">0-20</option>
		<option value="21-40">21-40</option>
		<option value="41-60">41-60</option>
		<option value=">60">>60</option>
	</select>
	<br />
	<div id="chartContainer3" style="height: 370px; width: 100%;"></div>
</body>
</html>
<%
	}
%>