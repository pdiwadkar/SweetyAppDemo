/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

import com.app.dao.UserGlucoseReading;
import java.awt.Color;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 *
 * @author Prasanna
 */
public class ReportData implements Serializable {
 
    private List<UserGlucoseReading> list;
    private ReportType reportType;
    private String user;
    private JasperReportBuilder reportBuilder = null;
    public ReportData(List<UserGlucoseReading> list,String user,ReportType type){
        System.out.println("Generating Report..");
        this.list = list;
        this.reportType = type;
        this.user = user;
        list.forEach((Record) -> System.out.println(Record.getEntryTime()+"  "+Record.getGlucoseLevel()));
        
    }
    public boolean run(){
        /*
        1) Create header
        2) Write data in tabular way.
        3)Write stat summary
        4)write page footer
        */
        boolean success = false;
        
        try{
        StringBuilder builder  = new StringBuilder();
        LocalDateTime currentDate = LocalDateTime.now();
         DRDataSource source = new DRDataSource("time","Value");
        TextColumnBuilder  tc = col.column("Timing","time", Timestamp.class).setPattern("MM-dd-yyyy HH:mm:ss");
        if(list.size()>0)
        list.forEach((Record) -> source.add(Record.getEntryTime(),Record.getGlucoseLevel()));
      
        if(reportType==ReportType.DAY){
            builder.append("Report generated for User ").append(user).
                    append("  on ").append(currentDate);
            builder.append("\n\n");
            builder.append("Report Type: ").append("Daily").append("\n\n");
                               
        }
        if(reportType==ReportType.MONTHLY){
            
            builder.append("Report generated for User ").append(user).
                    append("  on ").append(currentDate).append("\n\n");            
            builder.append("Report Type: ").append("Monthly");
            
        }
        if(reportType==ReportType.MONTH_TO_DATE){
            builder.append("Report generated for User ").append(user).
                    append("  on ").append(currentDate).append("\n\n");
            builder.append("Report Type: ").append("Month To Date");
        }
        StyleBuilder boldStyle= stl.style().bold();
        StyleBuilder summaryStyle = stl.style().setBorder(stl.penDouble());
        StyleBuilder centerBoldStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
        StyleBuilder columnTitleStyle  = stl.style(centerBoldStyle)
		                                    .setBorder(stl.pen1Point())
		                                    .setBackgroundColor(Color.CYAN);
        
         reportBuilder = report().setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows().
                columns(tc,
                        col.column("Reading(mg/dl)","Value",type.integerType()))
                .title(cmp.text(builder.toString()).setStyle(centerBoldStyle))
                .pageFooter(cmp.pageXofY().setStyle(centerBoldStyle))
                .addSummary(cmp.text(getSummary(list).toString()))
                .setSummaryStyle(summaryStyle)
                .setDataSource(source);
                success = true;
        }
        catch(Exception dr){
            System.out.println("Error in generating report");
        }
        
        return success;
    }
    public JasperReportBuilder getReportBuilder(){
        try{
            if(list.size() <1){
                //Report is empty.
                reportBuilder.setDataSource(new JREmptyDataSource());
                reportBuilder.addSummary(cmp.text(getSummary(list).getTextForEmptyReport()));
            }
        return reportBuilder;
        }
        catch(Exception dr){
            dr.printStackTrace();
        }
        System.out.println("cAME AFTER ELSE");
        return null; 
    }
             
                        
    private static Summary  getSummary(List<UserGlucoseReading> list){
              //Get the stats.
        //IntSummaryStatistics stat = list.stream().collect(Collectors.summarizingInt(UserGlucoseReading::getGlucoseLevel));
         IntSummaryStatistics  stat = list.stream().collect(Collectors.summarizingInt(UserGlucoseReading::getGlucoseLevel));
        Summary summary = new Summary();
        if(list.size() >0){
        summary.setAvg(stat.getAverage());
        summary.setMax(stat.getMax());
        summary.setMin(stat.getMin());
        summary.setSum((int)stat.getSum());
        }
        else{
            summary.setTextForEmptyReport("There are no records for the given date range.");
        }
        return summary;
    }
  
}
class Summary{
    
    private double avg;
    private int sum,min,max;
    private String emptyReport;
    public Summary(){
        
    }
    public void setTextForEmptyReport(String text){
        this.emptyReport = text;
    }
    public String getTextForEmptyReport(){
        return emptyReport;
    }
    public void setAvg(double avg){
        this.avg = avg;
    }
    public double getAvg(){return avg;}
    public void setSum(int sum){
        this.sum = sum;
    }
    public int getSum(){return sum;}
    public void setMax(int max){
        this.max = max;
    }
    public int getMax(){return max;}
    public void setMin(int min){
        this.min = min;
    }
    public int min(){return min;}
    public String toString(){
        return "Sum = "+sum+ "\n"+
                "Average = "+avg+"\n"+
                "Maximum = "+max+"\n"+
                "Minimum = "+min+"\n";
    }
}
