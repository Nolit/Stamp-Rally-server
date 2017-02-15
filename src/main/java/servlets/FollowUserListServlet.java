/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.Friends;
import database.entities.Users;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.ImageUtil;

/**
 *
 * @author Nolit
 */
@WebServlet(name = "FollowUserListServlet", urlPatterns = {"/follow"})
public class FollowUserListServlet extends HttpServlet {
    @EJB
    UserManager um;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int userId = Integer.valueOf(request.getParameter("referenceUserId"));
        String mode = request.getParameter("mode");
        Users[] friends;
        if(mode.equals("follow")){
            List<Friends> l =  um.getFollowList(userId);
            friends = new Users[l.size()];
            for(Friends f : l){
                friends[friends.length - 1] = f.getUsers();
            }
        }else{
            List<Friends> l =  um.getFollowerList(userId);
            friends = new Users[l.size()];
            for(Friends f : l){
                friends[friends.length - 1] = f.getUsers1();
            }
        }
        Users[] responseUsers = new Users[friends.length];
        for(Users origin : friends){
            Users copiedUser = new Users();
            copiedUser.setUserName(origin.getUserName());
            copiedUser.setUserId(origin.getUserId());
            copiedUser.setSearchId(origin.getSearchId());
            copiedUser.setThumbnailData(ImageUtil.read(origin.getThumbnail()));
            responseUsers[responseUsers.length - 1] = copiedUser;
        }
        try (PrintWriter out = response.getWriter()) {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(responseUsers);
            out.print(json);
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
