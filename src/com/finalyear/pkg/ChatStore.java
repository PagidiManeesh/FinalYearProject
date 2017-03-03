/**
 * 
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
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mypractice","root","ROOT");
            Statement statement = connection.createStatement();
          
            String string = httpServletRequest.getParameter("uname");
            String string2 = httpServletRequest.getParameter("msg");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm:ss a");
            
            
            
            
            //date class object
            Date date = new Date();
            String string3 = simpleDateFormat.format(date);
            String string4 = simpleDateFormat2.format(date);
            System.out.println("check3 at chatstore");
            String string5 = "insert into chatting_data values('" + string + "','" + string2 + "','" + string3 + "','" + string4+ "'+'')";
              int re = statement.executeUpdate(string5);
            System.out.println("Updated rows  "+re); 
          // ResultSet resultSet = statement.executeQuery(string5);
            //System.out.println("Updated rows  "+resultSet);
            //resultSet.next();  
            connection.close();
        }
        catch (Exception va) {
            va.printStackTrace();
            System.out.println(va.getMessage());
            System.out.println("DataBase Error");
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.doGet(httpServletRequest, httpServletResponse);
    }
}

