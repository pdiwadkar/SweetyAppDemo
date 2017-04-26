/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author amruta
 */
public class DBUtil {
    
    static final String DBURL = "jdbc:postgresql://localhost:5433/SweetyDB?user=postgres&password=postgres";
    static JdbcPooledConnectionSource source=  null;
  
    public static JdbcPooledConnectionSource getConnection(){
        try{
        if(source == null){
            source = new JdbcPooledConnectionSource(DBURL); 
          //keep connection open for 30 minutes.
          source.setMaxConnectionAgeMillis(30*60*1000);
          System.out.println("connected done");
        }
        
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        return source;
    }
}
