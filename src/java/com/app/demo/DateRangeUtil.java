/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Prasanna
 * utility class for getting date range based on input dates.
 */
public class DateRangeUtil {
    
    private static int  LAST_HOUR = 23;
    private static int LAST_MINUTE = 59;
    private static int LAST_SECOND = 59;
    
    public static Pair<LocalDateTime,LocalDateTime> getDateRange(String inputDate,int reportType){
        //input date will be of mm/dd/yyyy format.
        
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "MM/dd/yyyy" );
        LocalDate ld = LocalDate.parse( inputDate , f );
        Pair<LocalDateTime,LocalDateTime> pair;
        LocalDateTime finDate = LocalDateTime.of(ld.getYear(),ld.getMonth(), ld.getDayOfMonth(), 0, 0, 0);
        switch(reportType){
            case 0:
                pair = getRangeForDate(finDate);
                break;
            case 1:
                pair = getRangeForMonthToDate(finDate);
                break;
            case 2:
                pair = getRangeFullMonth(finDate);
                break;
            default:
                pair = getRangeForDate(finDate);
                break;
        }            
            return pair;
             
    }
    public static Pair<LocalDateTime ,LocalDateTime> getRangeForDate(LocalDateTime ld){
        //return start and end of the day.
        LocalDateTime start = LocalDateTime.of(ld.getYear(),ld.getMonth(),ld.getDayOfMonth(),0,0,0);
        LocalDateTime end = start.plusHours(LAST_HOUR).plusMinutes(LAST_MINUTE).plusSeconds(LAST_SECOND);
        Pair<LocalDateTime ,LocalDateTime> pair = new Pair<>(start,end);
        return pair;
    }
    private static Pair<LocalDateTime,LocalDateTime> getRangeForMonthToDate(LocalDateTime ld){
        //Beginning of month to this date.
        LocalDateTime start = LocalDateTime.of(ld.getYear(),ld.getMonth(),1,0,0,0);
        LocalDateTime end = LocalDateTime.of(ld.getYear(),ld.getMonth(),ld.getDayOfMonth(),LAST_HOUR,LAST_MINUTE);
        Pair<LocalDateTime ,LocalDateTime> pair = new Pair<>(start,end);
        return pair;        
    }
    private static Pair<LocalDateTime,LocalDateTime> getRangeFullMonth(LocalDateTime ld){
        
        boolean isLeapFeb = Year.isLeap(ld.getYear()) && ld.getMonth()==Month.FEBRUARY;
        int k = isLeapFeb?29:ld.getMonth().minLength();
        LocalDateTime start = LocalDateTime.of(ld.getYear(),ld.getMonth(),1,0,0,0);
        LocalDateTime end = LocalDateTime.of(ld.getYear(),ld.getMonth(),k,LAST_HOUR,LAST_MINUTE,LAST_SECOND);
        Pair<LocalDateTime,LocalDateTime> pair = new Pair<>(start,end);
        return pair;
    }
    
}

