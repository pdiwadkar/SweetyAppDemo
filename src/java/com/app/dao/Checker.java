/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author amruta
 */
public class Checker {
   
    /*public static void checkHowMany(){
        
        try{
        String url = "jdbc:postgresql://localhost:5433/SweetyDB?user=postgres&password=postgres";
        JdbcPooledConnectionSource source = new JdbcPooledConnectionSource(url); 
        //create one user
        UserInfo user = new UserInfo();
        //find the user raj and insert couple of records for him
        Dao<UserInfo,Long> userDao = DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb = userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", "Prasanna");
        UserInfo pq = qb.queryForFirst();
        if(pq == null){
         System.out.println("User not found");   
        }
        else{
        Dao<UserGlucoseReading,Long> gdao = DaoManager.createDao(source, UserGlucoseReading.class);
        LocalDateTime today =LocalDateTime.now();
       Pair<LocalDateTime,LocalDateTime> pair = DateRangeUtil.getRangeForDate(today);
        Timestamp start = Timestamp.valueOf(pair.getFirst());
        Timestamp end = Timestamp.valueOf(pair.getSecond());
        QueryBuilder<UserGlucoseReading,Long> gb = gdao.queryBuilder();
        Where<UserGlucoseReading,Long> where1 = gb.where();
        where1.eq("loginid",pq.getID());
        where1.and();
        where1.ge("recordingtime", start).and().le("recordingtime",end);
        PreparedQuery<UserGlucoseReading> query = gb.prepare();
        List<UserGlucoseReading> list = gdao.query(query);
        list.forEach((Record) -> System.out.println(Record.getGlucoseLevel()));
              
        }
        
        source.close();
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        catch(IOException io){
            io.printStackTrace();
        }
        
    }*/
    public static void checkDate(){
        String input = "04/07/2013";
       // DateTimeFormatter d = DateTimeFormatter.ofPattern("")
       // LocalDateTime ld = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyyThh:mm:ss.uuu"));
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "MM/dd/yyyy" );
        LocalDate ld = LocalDate.parse( input , f );
        LocalDateTime finDate = LocalDateTime.of(ld.getYear(),ld.getMonth(), ld.getDayOfMonth(), 0, 0, 0);
        System.out.println(finDate);
    }
   /* public static void check(){
        try{
        String url = "jdbc:postgresql://localhost:5433/SweetyDB?user=postgres&password=postgres";
        JdbcPooledConnectionSource source = new JdbcPooledConnectionSource(url); 
        //create one user
        UserInfo user = new UserInfo();
        //find the user raj and insert couple of records for him
        Dao<UserInfo,Long> userDao = DaoManager.createDao(source,UserInfo.class);
        QueryBuilder<UserInfo,Long> qb = userDao.queryBuilder();
        Where<UserInfo,Long> where = qb.where();
        where.eq("loginname", "Prasanna");
        UserInfo pq = qb.queryForFirst();
        if(pq == null){
         System.out.println("User not found");   
        }
        else{
        Dao<UserGlucoseReading,Long> gdao = DaoManager.createDao(source, UserGlucoseReading.class);
        /*UserGlucoseReading reading1 = new UserGlucoseReading();
        reading1.setGlucoseLevel(125);
        reading1.setUserInfo(pq);
        LocalDateTime t1 = LocalDateTime.now();
        reading1.setEntryTime(Timestamp.valueOf(t1));
        gdao.create(reading1);
        System.out.println("record added");
        }
        
        source.close();
        
       
        }
        
        catch(SQLException io){
            io.printStackTrace();
        }
       catch(IOException io){
           io.printStackTrace();
       }
    }*/
    public static void insertGlucoseTest(){
        
        
    }
    public static void main(String[] args){
      /*  try{
        String url = "jdbc:postgresql://localhost:5433/SweetyDB?user=postgres&password=postgres";
        ConnectionSource source = new JdbcConnectionSource(url);
        
        //instantiate a DAO
        Dao<UserGlucoseReading,Long> glu = DaoManager.createDao(source,UserGlucoseReading.class);
        Dao<UserInfo,Long> userDao = DaoManager.createDao(source,UserInfo.class);
        UserInfo info = userDao.queryForId(3l);
        System.out.println(info.getLoginName());
        System.out.println(info.getPassword());
        UserGlucoseReading gr = new UserGlucoseReading();
        gr.setGlucoseLevel(132);
        OffsetDateTime t1 = OffsetDateTime.now();
        gr.setEntryTime(new Timestamp(t1.toEpochSecond()));
        gr.setUserInfo(info);
        glu.create(gr);
        System.out.println("Done");
        
        source.close();
        }
        catch(SQLException sql){
            System.out.println(sql);
        }
        catch(IOException io){
            io.printStackTrace();
        }*/
      //checkHowMany();
      checkDate();
        
    }
}
