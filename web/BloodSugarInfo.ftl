
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
    <script>  
function validateform(){  
var rec=document.myform.record.value;  

if (rec==null || rec=="" || Number(rec) <1){  
  alert("Glucose value should be positive.");  
  return false;  
}  
}  
</script>  
        <title>Daily Sugar Info</title>
        <link rel="stylesheet" href="/resources/demos/style.css">
 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
      
    </head>
    <style>
  html, body {
    height: 100%;
  }
   form{
    width: 300px;
    margin: 0 auto;
     }
  #tableContainer-1 {
    height: 100%;
    width: 100%;
    display: table;
  }
  #tableContainer-2 {
    vertical-align: middle;
    display: table-cell;
    height: 100%;
  }
  #myTable {
    margin: 0 auto;
  }
    tr { background-color:#F0FFFF; color:black;padding: 15px }
    th { background-color:#F0FFFF; color:black;padding: 15px }
</style>
    <body style="background-color:powderblue">
        <form name="myform" action="UserReader" method="post" onsubmit="return validateform()">
        <br>
        <br>
        <div id="tableContainer-1">
        <div id="tableContainer-2">
        <table border=2>
        <tr><th colspan="3">Your today's records.</th></tr>
         <tr>
          <th>No</th><th>Time</th><th>Glucose Level(mg/dl)</th>
        </tr>
         <#list dailyRecords as record>
         <tr>
            <td>${record_index+1}</td>
             <td>${record.entryTime}</td>
             <td>${record.glucoseLevel}</td>
         </tr>            
         </#list>
         <#if (dailyRecords?size >=4)>
         <tr><td colspan="3">Maximum 4 records allowed per day.</td></tr>
         </#if>
        </table>
        </div>
        </div>
        <#assign max_allowed = 4>
        <#if dailyRecords?size gte max_allowed>
        <input type=text disabled name="record">
        <input type=submit disabled value="enter">
        <#else>
        <input type=text name="record">
        <input type="submit" name="enter" value="enter">
        
        </#if>
        </form>
        <br>
        <form method=post action="ReportingInfo.ftl">
        <input type=submit name="Click for report" value = "Click for Report" >
        <a href="Logout">Logout</a>
        </form>
    </body>
</html>
