<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Page</title>
	<link rel = "stylesheet" type = "text/css" href = "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css" />
	<link rel = "stylesheet" type = "text/css" href = "https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css" />
	<script type="text/javascript">
		function loadData()
		{
			var xmlhttp;
			var url = 'InquiryMasterController?flag=display';
			
			if(window.XMLHttpRequest)
			{
				xmlhttp = new XMLHttpRequest();
			}else{
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlhttp.onreadystatechange = function() {
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					location.reload(true);
				}
			}
			xmlhttp.open('POST',url,true);
			xmlhttp.send();
		}
		
		function loadMore(button)
		{
			var xmlhttp;
			var url="<%= request.getContextPath()%>/InquiryMasterController?flag=loadData&id="+button.id;
			
			if(window.XMLHttpRequest)
			{
				xmlhttp = new XMLHttpRequest();
			}else{
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlhttp.onreadystatechange = function() {
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					document.getElementById('nodes').innerHTML = xmlhttp.responseText;
					/*var z=(1*1)-1;
					
					var main='<table align="center" border="1">';
					alert(xmlhttp.responseXML.documentElement)
					var cus = xmlhttp.responseXML.documentElement;
					alert(cus);
					var inqId=inq.getElementsByTagName("inquiryId")[z].childNodes[0].nodeValue;
					while(inqId!=null)
					{
						inqId=inq.getElementsByTagName("inquiryId")[z].childNodes[0].nodeValue;
						var address=inq.getElementsByTagName("address")[z].childNodes[0].nodeValue;
						var name=inq.getElementsByTagName("name")[z].childNodes[0].nodeValue;
						var inquiryDetails=inq.getElementsByTagName("inquiryDetails")[z].childNodes[0].nodeValue;
						var referenceNo=inq.getElementsByTagName("referenceNo")[z].childNodes[0].nodeValue;
						var mobileno=inq.getElementsByTagName("mobileno")[z].childNodes[0].nodeValue;
						var inquiryDate=inq.getElementsByTagName("inquiryDate")[z].childNodes[0].nodeValue;
						var referenceName=inq.getElementsByTagName("referenceName")[z].childNodes[0].nodeValue;
						z++;
						
						main+="<tr>";
						main+="<td>"+inqId+"</td>";
						main+="<td>"+address+"</td>";
						main+="<td>"+name+"</td>";
						main+="<td>"+inquiryDetails+"</td>";
						main+="<td>"+referenceNo+"</td>";
						main+="<td>"+mobileno+"</td>";
						main+="<td>"+inquiryDate+"</td>";
						main+="<td>"+referenceName+"</td>";
						main+="</tr>";
					}
					main+="</table>";
					alert(main);
					document.getElementById('nodes').innerHTML = main;*/
				}
			}
			xmlhttp.open('POST',url,true);
			xmlhttp.send();
		}
		
		function deleteData(button)
		{
			var xmlhttp;
			var url = 'InquiryMasterController?flag=delete&id='+button.id;
			
			if(window.XMLHttpRequest)
			{
				xmlhttp = new XMLHttpRequest();
			}else{
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlhttp.onreadystatechange = function() {
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					loadData();
				}
			}
			xmlhttp.open('POST',url,true);
			xmlhttp.send();
		}
	</script>
</head>
<body>
<input type="button" id="btn1" onclick="loadData()" value="Load Data">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="inquiryList" class="table table-striped table-bordered" style="width:100%">
	<thead>
            <tr>
                <th>Inquiry ID</th>
                <th>Name</th>
                <th>Mobile Number</th>
                <th>Address</th>
                <th>Inquiry date</th>
                <th>Load More</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="data" items="${ sessionScope.allInquiries }">
        		<tr>
        			<td>${ data.referenceNumber }</td>
        			<td>${ data.customerName }</td>
        			<td>${ data.customerMobile }</td>
        			<td>${ data.customerAddress }</td>
        			<td>${ data.inquiryDate }</td>
        			<td><input type="button" id="${ data.inquiryId }" onclick="loadMore(this)" value="Load"></td>
        			<td><input type="button" id="${ data.inquiryId }" onclick="deleteData(this)" value="Delete"></td>
        	</c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <th>Inquiry ID</th>
                <th>Name</th>
                <th>Mobile Number</th>
                <th>Address</th>
                <th>Inquiry date</th>
                <th>Load More</th>
                <th>Delete</th>
            </tr>
        </tfoot>
</table>
<div id="nodes">
</div>
</body>
</html>