/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.StampRallyData;
import database.entities.StampRallys;
import database.entities.Users;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.ImageUtil;

@WebServlet(name = "VariousStampRallyServlet", urlPatterns = {"/VariousStampRally"})
public class VariousStampRallyServlet extends HttpServlet {
    private static final String COMPLETED = "complete";
    private static final String CREATED = "create";
    private static final String FAVORITE = "favorite";
    
    @EJB
    UserManager um;
    @EJB
    StampRallyManager srm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int userId = Integer.valueOf(request.getParameter("referenceUserId"));
        String mode = request.getParameter("mode");
        System.out.println("スタンプラリーリスト : mode = "+ mode);
        Users user = um.read(userId);
        
        List<StampRallys> stampRallyList;
        if(mode.equals(COMPLETED)){
            stampRallyList = srm.getCompletedStampRally(userId);
        }else if(mode.equals(CREATED)){
            stampRallyList = user.getStampRallysCollection();
        }else if(mode.equals(FAVORITE)){
            stampRallyList = srm.getFavoriteStampRally(userId);
        }else{
            return;
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(copy(stampRallyList));
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private StampRallyData[] copy(List<StampRallys> fromObj){
        StampRallyData[] stampRallyData = new StampRallyData[fromObj.size()];
        for(int index = 0;index < fromObj.size();index++){
            StampRallys i = fromObj.get(index);
            StampRallyData data = new StampRallyData();
            data.setStampRallyId(i.getStamprallyId());
            data.setPicture(ImageUtil.read(i.getStampThumbnail()));
            data.setStampRallyTitle(i.getStamprallyName());
            data.setStampRallyCreatorName(i.getUsersList().get(0).getUserName());
            data.setStampRallyCreatorUserId(i.getUsersList().get(0).getUserId());
            stampRallyData[index] = data;
        }
        return stampRallyData;
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
