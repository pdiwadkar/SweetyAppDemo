/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.app.demo.DateRangeUtil;
import com.app.demo.Pair;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.PreparedQuery;

import com.j256.ormlite.stmt.Where;

import java.util.List;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserGlucoseDAO{

    public UserGlucoseDAO(){
        
    }
    public int addGlucoseEntry(String userName,int glucoseVal){
        /*
        Check how many entries from start of the day.
        DAO hibernate logic. insert operation.*/
        JdbcPooledConnectionSource source = DBUtil.getConnection();
        int key = -1;
        try{
        Dao<UserInfo,Long> userDao = DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb = userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", userName);
        UserInfo userInfo = qb.queryForFirst();
        
        LocalDateTime current = LocalDateTime.now();
        Timestamp entryTime = Timestamp.valueOf(current);
        Dao<UserGlucoseReading,Long> dao = DaoManager.createDao(source,UserGlucoseReading.class);
        UserGlucoseReading reading = new UserGlucoseReading();
        reading.setEntryTime(entryTime);
        reading.setGlucoseLevel(glucoseVal);
        reading.setUserInfo(userInfo);
         key = dao.create(reading);
        System.out.println("Created "+key);
        
        }
        catch(SQLException  sql){
            sql.printStackTrace();
        }
                return key;
        
          }
    
    public List<UserGlucoseReading> getReadingsForToday(String userName){
        
        List<UserGlucoseReading> list = new ArrayList<>();
        
        try{
        JdbcPooledConnectionSource source = DBUtil.getConnection();
        Dao<UserInfo,Long> userDao =DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb =userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", userName);
        UserInfo userInfo = qb.queryForFirst();
        Dao<UserGlucoseReading,Long> gdao = DaoManager.createDao(source, UserGlucoseReading.class);
        LocalDateTime today =LocalDateTime.now();
        Pair<LocalDateTime,LocalDateTime> pair = DateRangeUtil.getRangeForDate(today);
        Timestamp start = Timestamp.valueOf(pair.getFirst());
        Timestamp end = Timestamp.valueOf(pair.getSecond());
        QueryBuilder<UserGlucoseReading,Long> gb = gdao.queryBuilder();
        Where<UserGlucoseReading,Long> where1 = gb.where();
        where1.eq("loginid",userInfo.getID());
        where1.and();
        where1.ge("recordingtime", start).and().le("recordingtime",end);
        PreparedQuery<UserGlucoseReading> query = gb.prepare();        
        list = gdao.query(query);
        list.forEach((Record) ->Record.setUserInfo(userInfo));
        list.forEach((Record) -> System.out.println(Record.getGlucoseLevel()));
        }
        catch(SQLException sql){
            System.out.println("Error is accessing DB "+sql);
        }
        return list;
    }
    
    public List<UserGlucoseReading> getRecordsForDay(String user,String date){
    //return the list of readings for this specific date.
    List<UserGlucoseReading> list = new ArrayList<>();        
        try{
        JdbcPooledConnectionSource source = DBUtil.getConnection();
        Dao<UserInfo,Long> userDao =DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb =userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", user);
        UserInfo userInfo = qb.queryForFirst();
        Dao<UserGlucoseReading,Long> gdao = DaoManager.createDao(source, UserGlucoseReading.class);
        Pair<LocalDateTime,LocalDateTime> pair = DateRangeUtil.getDateRange(date, 0);
        Timestamp start = Timestamp.valueOf(pair.getFirst());
        Timestamp end = Timestamp.valueOf(pair.getSecond());
        QueryBuilder<UserGlucoseReading,Long> gb = gdao.queryBuilder();
        Where<UserGlucoseReading,Long> where1 = gb.where();
        where1.eq("loginid",userInfo.getID());
        where1.and();
        where1.ge("recordingtime", start).and().le("recordingtime",end);
        PreparedQuery<UserGlucoseReading> query = gb.prepare();        
        list = gdao.query(query);
        list.forEach((Record) ->Record.setUserInfo(userInfo));
        list.forEach((Record) -> System.out.println(Record.getGlucoseLevel()));
        }
        catch(SQLException sql){
            System.out.println("Error is accessing DB "+sql);
        }
        return list;
       
    }
    public List<UserGlucoseReading> getRecordsMonthToDate(String user,String date){
    //return the list of readings for this specific date.
    List<UserGlucoseReading> list = new ArrayList<>();        
        try{
        JdbcPooledConnectionSource source = DBUtil.getConnection();
        Dao<UserInfo,Long> userDao =DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb =userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", user);
        UserInfo userInfo = qb.queryForFirst();
        Dao<UserGlucoseReading,Long> gdao = DaoManager.createDao(source, UserGlucoseReading.class);
        Pair<LocalDateTime,LocalDateTime> pair = DateRangeUtil.getDateRange(date, 1);
        Timestamp start = Timestamp.valueOf(pair.getFirst());
        Timestamp end = Timestamp.valueOf(pair.getSecond());
        QueryBuilder<UserGlucoseReading,Long> gb = gdao.queryBuilder();
        Where<UserGlucoseReading,Long> where1 = gb.where();
        where1.eq("loginid",userInfo.getID());
        where1.and();
        where1.ge("recordingtime", start).and().le("recordingtime",end);
        PreparedQuery<UserGlucoseReading> query = gb.prepare();        
        list = gdao.query(query);
        list.forEach((Record) ->Record.setUserInfo(userInfo));
        list.forEach((Record) -> System.out.println(Record.getGlucoseLevel()));
        }
        catch(SQLException sql){
            System.out.println("Error is accessing DB "+sql);
        }
        return list;
       
    }
    public List<UserGlucoseReading> getRecordsMonthly(String user,String date){
    //return the list of readings for this specific date.
    List<UserGlucoseReading> list = new ArrayList<>();        
        try{
        JdbcPooledConnectionSource source = DBUtil.getConnection();
        Dao<UserInfo,Long> userDao =DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb =userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", user);
        UserInfo userInfo = qb.queryForFirst();
        Dao<UserGlucoseReading,Long> gdao = DaoManager.createDao(source, UserGlucoseReading.class);
        Pair<LocalDateTime,LocalDateTime> pair = DateRangeUtil.getDateRange(date, 2);
        Timestamp start = Timestamp.valueOf(pair.getFirst());
        Timestamp end = Timestamp.valueOf(pair.getSecond());
        QueryBuilder<UserGlucoseReading,Long> gb = gdao.queryBuilder();
        Where<UserGlucoseReading,Long> where1 = gb.where();
        where1.eq("loginid",userInfo.getID());
        where1.and();
        where1.ge("recordingtime", start).and().le("recordingtime",end);
        PreparedQuery<UserGlucoseReading> query = gb.prepare();        
        list = gdao.query(query);
        list.forEach((Record) ->Record.setUserInfo(userInfo));
        list.forEach((Record) -> System.out.println(Record.getGlucoseLevel()));
        }
        catch(SQLException sql){
            System.out.println("Error is accessing DB "+sql);
        }
        return list;       
    }
    
      
}
