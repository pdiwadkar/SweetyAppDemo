/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Prasanna
 */
public class AppDBInit implements ServletContextListener {
     
    final String DBURL = "jdbc:postgresql://localhost:5433/SweetyDB?user=postgres&password=postgres";
    JdbcPooledConnectionSource source=  null;
    public void contextInitialized(ServletContextEvent event){
        /*
        Initialize ORMLIte connection. Currently only one DB connection
        */
        
        try{
          source = new JdbcPooledConnectionSource(DBURL); 
          //keep connection open for 30 minutes.
          source.setMaxConnectionAgeMillis(30*60*1000);
          System.out.println("Connection pool with SweetyDB created");
          
        }
        catch(SQLException sql){
            System.out.println("Unable to connect to SweetyDB..");
        }
         ServletContext context  = event.getServletContext();
         context.setAttribute("DBConnection", source);     
        
    }
    
    public void contextDestroyed(ServletContextEvent ctx){
        /*
        close the Database connection.
        */
        if(source != null){
            source.closeQuietly();
            System.out.println("Closed the connection with SweetyDB");
        }
        
    }
}
