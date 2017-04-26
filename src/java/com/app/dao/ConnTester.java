/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;

/**
 *
 * @author amruta
 */
public class ConnTester {
   
    public static void main(String[] args) throws Exception{
         
        JdbcPooledConnectionSource c1 = DBUtil.getConnection();
        c1.close();
        JdbcPooledConnectionSource c2 = DBUtil.getConnection();
        
        
    }
}
