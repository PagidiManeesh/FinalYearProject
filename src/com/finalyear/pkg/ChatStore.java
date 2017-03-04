
/**
 * This file is used for user messages into the database
 */
package com.finalyear.pkg;

/**
 * @author Maneesh
 *
 */
import java.io.IOException;

import java.sql.*;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings({ "serial", "unused" })
public class ChatStore
extends HttpServlet {
	
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        PrintWriter printWriter = httpServletResponse.getWriter();
        try {
        	 
            httpServletResponse.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");/*Attempts to establish a connection to the given database URL. The DriverManager attempts to select an appropriate
            driver from the set of registered JDBC drivers*/
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypractice","root","ROOT");
            Statement statement = connection.createStatement();
          
            String string = httpServletRequest.getParameter("uname");//Retrieves the value of the designated column in the current row of this ResultSet object as a String in the Java programming language
            String string2 = httpServletRequest.getParameter("msg");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");/*SimpleDateFormat is a concrete class for formatting and parsing dates in a locale-sensitive manner. It allows for formatting (date → text),
             parsing (text → date), and normalization. */
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
            
            
            
            
            //date class object
            Date date = new Date();//The class Date represents a specific instant in time, with millisecond precision. 
            String string3 = simpleDateFormat.format(date);/*Formats a Date into a date/time string.
            Parameters:date - the time value to be formatted into a time string.Returns:the formatted time string.*/
            String string4 = simpleDateFormat2.format(date);
            System.out.println("check3 at chatstore");
            String string5 = "insert into chatting_data values('" + string + "','" + string2 + "','" + string3 + "','" + string4+ "'+'')";/*Inserting user details and
            message into database*/
              int re = statement.executeUpdate(string5);/*Executes the given SQL statement, which may be an INSERT, UPDATE, or DELETE statement or 
              an SQL statement that returns nothing, such as an SQL DDL statement. */
            System.out.println("Updated rows  "+re); 
          // ResultSet resultSet = statement.executeQuery(string5);
            //System.out.println("Updated rows  "+resultSet);
            //resultSet.next();  
            connection.close();
        }
        catch (Exception va) 
        /*//The class Exception and its subclasses are a form of Throwable that indicates conditions that a reasonable application might want to catch. */
        {
            va.printStackTrace();
            System.out.println(va.getMessage());
            System.out.println("DataBase Error");
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doGet(httpServletRequest, httpServletResponse);
    }
}

