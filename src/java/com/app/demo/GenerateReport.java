/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

import com.app.dao.UserGlucoseDAO;
import com.app.dao.UserGlucoseReading;
import java.util.List;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
/**
 *
 * @author Prasanna
 * Utility class for generating reports.
 */
public class GenerateReport {
    
        public static void main(String[] args){
            generateReportMonthly("Prasanna","04/16/2017");
        }
        private static JasperReportBuilder  generateReportForDay(String user,String date){
            /*
            generate report for the day.
            */
            UserGlucoseDAO dao = new UserGlucoseDAO();
            List<UserGlucoseReading> list = dao.getRecordsForDay(user, date);
            sortRecords(list);
            ReportData report = new ReportData(list,user,ReportType.DAY);
            report.run();
            return report.getReportBuilder();
                        
        }
        private static JasperReportBuilder generateReportMonthToDate(String user,String date){
           
            UserGlucoseDAO dao = new UserGlucoseDAO();
            List<UserGlucoseReading> list = dao.getRecordsMonthToDate(user, date);
            sortRecords(list);
            ReportData report = new ReportData(list,user,ReportType.MONTH_TO_DATE);
            report.run();
            return report.getReportBuilder();
           
        }
        private static JasperReportBuilder generateReportMonthly(String user,String date){
           
            UserGlucoseDAO dao = new UserGlucoseDAO();
            List<UserGlucoseReading> list = dao.getRecordsMonthly(user, date);
            sortRecords(list);
            ReportData report = new ReportData(list,user,ReportType.MONTHLY);
            report.run();
            return report.getReportBuilder();
        }
        private static void sortRecords(List<UserGlucoseReading> list){
            /*
            Sort the reccords if needed.
            */
           list.sort((r1,r2) -> r1.getEntryTime().compareTo(r2.getEntryTime()));
            
        }
        
        public static JasperReportBuilder getReportBuider(String userName,String date,String reportType){
            JasperReportBuilder builder = null;
            switch(reportType){
                case "day":
                    builder = generateReportForDay(userName,date);
                    break;
                case "monthtodate":
                    builder = generateReportMonthToDate(userName,date);
                    break;
                case "monthly":
                    builder= generateReportMonthly(userName,date);
                    break;
                default:
                    break;
                                
            }
            return builder;
            
        }
    
}


