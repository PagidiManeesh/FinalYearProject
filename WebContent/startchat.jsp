<!-- Chatting is done on this page -->


<%if(session.getAttribute("name")==null)/*If session is not created or copleted then it is redirected to main page */
  response.sendRedirect("/FinalYear/");
%>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%String uname=(String)session.getAttribute("name"); %><!-- Retrieves the value of the designated column in the current row -->
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="check.js"></script>
 <link rel="shortcuticon" href="icon.jpg">
 <link rel="shortcut icon" href="icon.jpg">
<title>Chatting Room</title>
<script>
     setInterval(reloaddata, 3000);//invoke reload data function every 3000 ms
    </script>  
</head>
<body>
<!-- //Defining text box -->

<p id="result"></p>
      <h2>  Welcome, <%=uname%></h2>
        <div class="logout-div" align="right"><a href="logout.jsp"><img src="log.jpg" width="100" height="100"></a></div>
        <div id="list">
            <div id="content">
            </div>
            <input style="display:none" type="text" id="u" value="<%=uname%>">						
            <textarea placeholder="Please type you message with in 200 Char" id='h' style="width: 100%; height: 15%;"></textarea>
            <input type="button" value="send" onclick="addText()" /><!-- used to add the text into the text box as soon as user clicks send -->
        </div>
</body>
</html>