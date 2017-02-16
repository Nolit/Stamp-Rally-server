/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.entities.Friends;
import database.entities.Users;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FollowRequestServlet", urlPatterns = {"/followRequest"})
public class FollowRequestServlet extends HttpServlet {
    @EJB
    UserManager um;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("フォローリクエストサーブレット");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int referenceUserId = Integer.valueOf(request.getParameter("referenceUserId"));
        Users loginUser = um.readByEmailAndPassword(email, password);
        Users referenceUser = um.read(referenceUserId);
        
        boolean isFollowRequest = Boolean.valueOf(request.getParameter("followRequest"));
        System.out.println(loginUser.getUserName() + " -> " + referenceUser.getUserName() + " : " + (isFollowRequest ? "フォロー" : "アンフォロー"));
        if(isFollowRequest && ! um.isFollow(loginUser.getUserId(), referenceUserId)){
            um.follow(loginUser, referenceUser);
            return;
        }
        um.unFollow(loginUser.getUserId(), referenceUserId);
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
