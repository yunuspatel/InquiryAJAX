<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Inquiry Page</title>
<script type="text/javascript">
	function checkData()
	{
		var xmlhttp;
		var url = 'InquiryMasterController?flag=displayAll';
		
		if(window.XMLHttpRequest)
		{
			xmlhttp = new XMLHttpRequest();
		}else{
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
		xmlhttp.onreadystatechange = function() {
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var val = new String(xmlhttp.responseText);
				if(val.includes("Empty"))
				{
					document.getElementById("disLink").hidden = true;
				}
			}
		}
		
		xmlhttp.open('POST',url,true);
		xmlhttp.send();
	}
</script>
</head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body onload="checkData()">
<div id="displayLink">
	<a id="disLink" href="display-inquiries,jsp">Click Here to Display All Inquiries</a>
</div>
<div id="nodes">
<table align=center>
<tr>
	<td>Enter Customer Name:-</td>
	<td><input type="text" id="customerName"></td>
</tr>
<tr>
	<td>Enter Customer Mobile Number:-</td>
	<td><input type="number" id="customerMobile"></td>
</tr>
<tr>
	<td>Enter Customer Address:-</td>
	<td><textarea rows="3" cols="20" id="customerAddress"></textarea></td>
</tr>
<tr>
	<td>Select Reference Source:-</td>
	<td><select id="referenceSource">
			<c:forEach var="data" items="${ sessionScope.source }">
				<option value="${ data.sourceId }">${ data.sourceName }</option>
			</c:forEach>
		</select></td>
</tr>
<tr>
	<td>Enter Inquiry Details:-</td>
	<td><textarea rows="5" cols="25" id="inquiryDetails"></textarea></td>
</tr>
<tr>
	<td colspan="2" align=center><input type=submit id=sbmt value=Insert></td>
</tr>
</table>	
</div>
</body>
</html>