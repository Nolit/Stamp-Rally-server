/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.StampPads;
import database.entities.StampRallys;
import database.entities.Users;
import database.managers.StampManager;
import database.managers.StampPadsManager;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;
import utilities.CharactorDecoder;

/**
 *
 * @author Nolit
 */
@WebServlet(name = "CreatedStampRallyReciever", urlPatterns = {"/createdStampRallyUpload"})
public class CreatedStampRallyReciever extends HttpServlet {
    @EJB
    StampRallyManager srm;
    @EJB
    UserManager um;
    @EJB
    StampPadsManager spm;
    @EJB
    StampManager sm;

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
        
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        String title = CharactorDecoder.decodeString(request.getParameter("title"));
        String summary = CharactorDecoder.decodeString(request.getParameter("summary"));
        String thumbnailStampId = request.getParameter("thumbnailStampId");
        List<Integer> selectedStampIdList = new ObjectMapper().readValue(request.getParameter("selectedStampIdList"), List.class);
        
        System.out.println(userId);
        System.out.println(title);
        System.out.println(summary);
        System.out.println(thumbnailStampId);
        System.out.println(selectedStampIdList);
        
        Users user = um.read(userId);
        StampRallys stampRally = new StampRallys();
        stampRally.setStamprallyName(title);
        stampRally.setStamrallyComment(summary);
        String str = UUID.randomUUID().toString().substring(0, 11);
        stampRally.setStampThumbnail(str);
        stampRally.setSearchId(str);
        stampRally.getUsersList().add(user);
        for(Integer id : selectedStampIdList){
            stampRally.getStampList().add(sm.read(id));
        }
        srm.create(stampRally);
        srm.clearStampRally(stampRally, user);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(true);
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
