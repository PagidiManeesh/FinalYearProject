<%if(session.getAttribute("name")==null)
  response.sendRedirect("/FinalYear/");
%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%String uname=(String)session.getAttribute("name"); %>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="check.js"></script>
 <link rel="shortcuticon" href="icon.jpg">
 <link rel="shortcut icon" href="icon.jpg">
<title>Chatting Room</title>
<script>
     setInterval(reloaddata, 3000);
    </script>  
</head>
<body>

<p id="result"></p>
      <h2>  Welcome, <%=uname%></h2>
        <div class="logout-div" align="right"><a href="logout.jsp"><img src="log.jpg" width="100" height="100"></a></div>
        <div id="list">
            <div id="content">
            </div>
            <input style="display:none" type="text" id="u" value="<%=uname%>">						
            <textarea placeholder="Please type you message with in 200 Char" id='h' style="width: 100%; height: 15%;"></textarea>
            <input type="button" value="send" onclick="addText()" />
        </div>
</body>
</html>