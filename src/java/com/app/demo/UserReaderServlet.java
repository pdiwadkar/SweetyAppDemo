/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.demo;

import com.app.dao.UserDAO;
import com.app.dao.UserGlucoseDAO;
import com.app.dao.UserGlucoseReading;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amruta
 */
public class UserReaderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String userName = (String)session.getAttribute("userName");
            String recordStr = request.getParameter("record");
            UserDAO userDAO = new UserDAO();
            UserGlucoseDAO dao = new UserGlucoseDAO();
            dao.addGlucoseEntry(userName, Integer.parseInt(recordStr));
            List<UserGlucoseReading> list = userDAO.getRecordsForToday(userName);
            request.setAttribute("dailyRecords",list);
            //list.forEach((Record) -> System.out.println(Record.getUserInfo().getLoginName()+"  "+Record.getEntryTime() +"  "+Record.getGlucoseLevel()));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/BloodSugarInfo.ftl");
            dispatcher.forward(request,response);
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
        //Check if its insert operation or refresh.
        Enumeration<String> tr= request.getParameterNames();
        while(tr.hasMoreElements()){
            System.out.println(tr.nextElement());
        }
        String buttonAction = request.getParameter("enter");
        if(buttonAction != null){
            System.out.println(buttonAction);
        processRequest(request, response);
        }
        else{
            //send it back..
            System.out.println("Came in refresh");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/BloodSugarInfo.ftl");
            rd.forward(request, response);
        }
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
