/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author diwadkap
 */
@DatabaseTable(tableName ="userinfo")
public class UserInfo implements Serializable{
     
    @DatabaseField(generatedId = true)
    private long id;   

     @DatabaseField
    private String loginname;
    
     @DatabaseField
    private String password;
    
    
    private Set<UserGlucoseReading> readings;
    
    public UserInfo(){
        
    }
    public void setLoginName(String name){
        this.loginname = name;
    }
    public String getLoginName(){
        
        return loginname;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public Set<UserGlucoseReading> getReadings(){
        return readings;
    }
    public void setReadings(Set<UserGlucoseReading> readings){
        this.readings = readings;
    }
    public void setID(long id){
        this.id = id;
    }
    public long getID(){
        return id;
    }
    
}