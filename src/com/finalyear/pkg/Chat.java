
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypractice","root","ROOT");
            Statement statement = connection.createStatement();
            String string = httpServletRequest.getParameter("uname");
            System.out.println(string);
            String string2 = httpServletRequest.getParameter("pw");
            System.out.println(string2);
            String string3 = "select *from chatting where username='" + string + "' AND password='" + string2 + "'";
            ResultSet resultSet = statement.executeQuery(string3);
           // int resultSet = statement.executeUpdate(string3);
            if (resultSet.next()) {
                String string4 = resultSet.getString("username");
                HttpSession httpSession = httpServletRequest.getSession();
                httpSession.setAttribute("name", (Object)string4);
                printWriter.println("Welcome, " + string4.toUpperCase()+"<br>");
                printWriter.println("<a href='startchat.jsp'> Let's Enter the Chat Room</a>");
                printWriter.println("<a href='logout.jsp' class='logout-chat'><img src='log.jpg' width='100' height='100'></a>");
            } 
            else {
                printWriter.println("Incorrect Username or Password.");
            }
            connection.close();
        }
        catch (Exception va) {//
            va.printStackTrace();
            System.out.println("Invalid User12345");
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doGet(httpServletRequest, httpServletResponse);
    }
}