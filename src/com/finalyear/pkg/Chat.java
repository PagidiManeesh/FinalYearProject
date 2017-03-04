/* this page is used to redirect to the chatbox */
package com.finalyear.pkg;

/**
 * @author Maneesh
 *
 */
//importing packages
import java.io.IOException;
import java.sql.*;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings({ "serial", "unused" })
public class Chat extends HttpServlet {
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        try {
            httpServletResponse.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");/*Attempts to establish a connection to the given database URL. The DriverManager attempts to select an appropriate
             driver from the set of registered JDBC drivers*/
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypractice","root","ROOT");
            Statement statement = connection.createStatement();//The object used for executing a static SQL statement and returning the results it produces. 
            String string = httpServletRequest.getParameter("uname");// copying user name into string
            System.out.println(string);
            String string2 = httpServletRequest.getParameter("pw");// copying password into string
            System.out.println(string2);
            String string3 = "select *from chatting where username='" + string + "' AND password='" + string2 + "'"; //Used to Verify the user details
            ResultSet resultSet = statement.executeQuery(string3);
           /// int resultSet = statement.executeUpdate(string3);
            if (resultSet.next()) {
                String string4 = resultSet.getString("username");//Retrieves the value of the designated column in the current row of this ResultSet object as a String in the Java programming language
                HttpSession httpSession = httpServletRequest.getSession();//Creating session for the user
                httpSession.setAttribute("name", (Object)string4);
                printWriter.println("Welcome, " + string4.toUpperCase()+"<br>");
                printWriter.println("<a href='startchat.jsp'> Let's Enter the Chat Room</a>");
                printWriter.println("<a href='logout.jsp' class='logout-chat'><img src='log.jpg' width='100' height='100'></a>");//print writer is used to write on html page
            } 
            else {
                printWriter.println("Incorrect Username or Password.");
            }
            connection.close();
        }
        catch (Exception va) {//The class Exception and its subclasses are a form of Throwable that indicates conditions that a reasonable application might want to catch. 
            va.printStackTrace();
            System.out.println("Invalid User12345");
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doGet(httpServletRequest, httpServletResponse);
    }
}