<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aconex file selector</title>
<script language="JavaScript" type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function parseDocument(){
		
		var filePath = document.getElementById("filePath").value;
		$.ajax({
		    url: 'http://localhost:8080/AconexGedComParser/rest/gedcom/parseDocument',
		    type: 'GET',
		    data: {
		    	filePath : filePath
		    },
		    success: function() { alert('Parsing Complete'); }
		});
	}
</script>
</head>
<body>
<input type="file" id="filePath">
<br>
<br>
<br>
<input type="submit" value="Parse Document" onclick="parseDocument()">
</body>
</html>