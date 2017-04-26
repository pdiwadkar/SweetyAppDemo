/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;

/**
 *
 * @author diwadkap
 */
@DatabaseTable(tableName="userglucosereading")
public class UserGlucoseReading {
    
    public UserGlucoseReading(){
        
    }
    @DatabaseField(generatedId = true)
    private long id;    
    
    @DatabaseField(columnName="glucoselevel")
    private int glucoselevel;
    
    @DatabaseField(columnName="recordingtime",canBeNull = false)    
    private  Timestamp recordingTime;    
    
    @DatabaseField(columnName="loginid",canBeNull = false,foreign = true)
    private UserInfo userInfo;
    
    public void setID(long id){
        this.id = id;
    }
    public long getID(){
        return id;
    }
    public void setGlucoseLevel(int level){
        this.glucoselevel = level;
    }
    public int getGlucoseLevel(){
        return this.glucoselevel;
    }
    public void setEntryTime(Timestamp entryTime){
        
        this.recordingTime = entryTime;
    }
    public Timestamp getEntryTime(){
        return this.recordingTime;
    }
    
    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }
    public UserInfo getUserInfo(){
        return userInfo;
    }
    public String toString(){
        
        return this.recordingTime+ "  "+this.glucoselevel;
    }
    
    
}