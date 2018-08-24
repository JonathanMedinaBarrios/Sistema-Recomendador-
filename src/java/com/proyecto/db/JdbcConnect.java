/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author docente
 */
public class JdbcConnect {
    private static final String URL="jdbc:mysql://localhost:3306/demo";
    private static final String USER="root";
    private static final String PASSWORD="jonakhan312";
    private static transient Connection connection=null;
    
    public static Connection getConnect() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
      return connection;
    }
    
}
