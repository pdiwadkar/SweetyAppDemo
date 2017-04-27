/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

import com.app.dao.UserDAO;
import com.app.dao.UserGlucoseReading;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amruta
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

   private JdbcPooledConnectionSource conn;
    @Override
    public void init(ServletConfig config){
        try{
        super.init(config);
        conn = (JdbcPooledConnectionSource)config.getServletContext().getAttribute("DBConnection");
        System.out.println("connection got to loginservlet");
        }
        catch(Exception se){
            System.out.println("LoginServlet init failed");
        }
    }
    public List<UserGlucoseReading> getTest(){
        List<UserGlucoseReading> list = new ArrayList<>();
        UserGlucoseReading r1 = new UserGlucoseReading();
        r1.setGlucoseLevel(432);
        r1.setEntryTime(Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        UserGlucoseReading r2 = new UserGlucoseReading();
        r2.setGlucoseLevel(432);
        r2.setEntryTime(Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        UserGlucoseReading r3 = new UserGlucoseReading();
        r3.setGlucoseLevel(321);
        r3.setEntryTime(Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
       
        list.add(r1);
        list.add(r2);
        list.add(r3);
        
        return list;
    }
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        Validate the patient's credentials 
        if authenticated, show Enty screen.
        else message and redirect to login screen.
        */
        //get user login and password.
        String userName = request.getParameter("login");
        String pwd = request.getParameter("password");
        //validate
        UserDAO userDAO = new UserDAO();
        if(userDAO.validUser(userName, pwd)){
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            List<UserGlucoseReading> list = userDAO.getRecordsForToday(userName);
            
            //list.forEach((Record) -> System.out.println(Record.getUserInfo().getLoginName()+"  "+Record.getEntryTime() +"  "+Record.getGlucoseLevel()));
                       
            request.setAttribute("dailyRecords",list);
            System.out.println("------------------------------------------");
              list.forEach((Record) -> System.out.println(Record.getUserInfo().getLoginName()+"  "+Record.getEntryTime() +"  "+Record.getGlucoseLevel()));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/BloodSugarInfo.ftl");
            rd.forward(request, response);
                        
             }
        
            
        
        else{
            //Invalid Login.
            response.sendRedirect("sweetyPage.html");
                    
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
