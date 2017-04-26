
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
         <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({maxDate:new Date()});
  } );
  </script>
 
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <style>
        form{
    width: 300px;
    margin: 0 auto;
     }
    </style>
    <body style="background-color:powderblue">
        <form method="post" action="ReportingServlet"><br><br>
     <table>
     <tr><td><h3>Select Date</h3></td>
     <td><input type="text" id="datepicker" name="selecteddate"></td></tr>
    <tr><td><input type="radio" name="reportType" value="day" checked>Selected Date</td></tr>
    <tr><td><input type="radio" name="reportType" value="monthtodate">Month to Date</td></tr>
    <tr><td><input type="radio" name="reportType" value="monthly">Monthly</td></tr>
    <tr><td><input type="submit" name="Generate Report"></td></tr>
    </table>
     </form>
    </body>
</html>
