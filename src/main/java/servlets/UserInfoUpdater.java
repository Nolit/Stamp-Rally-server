/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import utilities.StringDecoder;

@WebServlet(name = "UserInfoUpdater", urlPatterns = {"/updateUserInfo"})
public class UserInfoUpdater extends HttpServlet {
    @EJB
    UserManager um;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String searchId = request.getParameter("searchId");
        String userName = StringDecoder.decode(request.getParameter("userName"));
        String profile = StringDecoder.decode(request.getParameter("profile"));
        System.out.println("ユーザー情報更新");
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        System.out.println("searchId : " + searchId);
        System.out.println("userName : " + userName);
        System.out.println("profile : " + profile);
        
        
        int userId = Integer.valueOf(request.getParameter("userId"));
        Users loginUser = um.read(userId);
        Users responseUser = new Users();
        
        if(um.readByEmail(email) == null){
            loginUser.setMailAddress(email);
            responseUser.setMailAddress(email);
        }else{
            System.out.println("そのemailは使われています");
        }
        if(um.readBySearchId(searchId) == null){
            loginUser.setSearchId(searchId);
            responseUser.setSearchId(searchId);
        }else{
            System.out.println("そのsearch_idは使われています");
        }
        loginUser.setPassword(password);
        loginUser.setUserName(userName);
        loginUser.setProfile(profile);
        um.update(loginUser);
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseUser);
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
