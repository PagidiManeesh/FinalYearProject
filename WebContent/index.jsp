<!--  /*This is the welcome page which consists Username and Password text boxes*/-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script type="text/javascript" src="check.js"></script>
<title>Chatting Room</title>
<link rel="shortcut icon" href="icon.jpg"><!-- Icon on webpage -->
</head>
<body>
<h1 align="center"><font color="#2c3e50">Welcome to My WebSite !</font></h1>

<div class="middle" id="result-data">
<table> <tr><td><h2>Please Login to Continue</h2></td></tr></table>
            <form autocomplete="off">
                
            <table>
                <tr height="80px">
                    <td><h2>Username:</h2></td>
                    <td><input type="text" id="uname" class="textbox" autocomplete="off" placeholder="Enter Your Username"></td>
                </tr>
                <tr height="20px">
                   <td> <h2>Password: </h2></td>
                    <td><input type="password" id="pw" class="textbox" placeholder="Enter Your Password"></td>
                </tr>
                <tr>
                    <td><input type="button" value="Go" class="circle" onclick="startchating();"></td>
                    <td><div id="loading-icon"></div></td>                               <!-- By submitting startchatting() is invoked  -->
                </tr>
            </table>
            </form>            
        </div>

</body>
</html>