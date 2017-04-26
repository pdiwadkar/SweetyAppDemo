/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Prasanna Wrapper class for ORMLite DAO.
 */
  
public class UserDAO {
    
    //future after user registration
    public UserDAO(){
        
    }
    public boolean validUser(String login,String password){
        //check against UserInfo table.
        boolean validUser = false;
        
        try{
        JdbcPooledConnectionSource source = DBUtil.getConnection();
        UserInfo user = new UserInfo();
        Dao<UserInfo,Long> userDao = DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb = userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", login);
        where.and();
        where.eq("password",password);
        UserInfo pq = qb.queryForFirst();
        if(pq != null)
            validUser = true;
        }
        catch(SQLException sql){
            System.out.println("Problem in accessing login details.");
            sql.printStackTrace();
        }
        
        return validUser;
       
        
    }
    public UserInfo getUser(String name){
        //return user for this login name.
        return null;
    }
    public boolean addUser(String login,String pwd){
       return true;
    }
    public List<UserGlucoseReading> getRecordsForToday(String user){
        //return number of remaining entries to be entedred by user.
        
        UserGlucoseDAO ugDAO = new UserGlucoseDAO();
        return ugDAO.getReadingsForToday(user);
    }
        
}
