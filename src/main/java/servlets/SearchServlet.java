/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import database.entities.StampRallys;
import database.managers.StampRallyManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.Activities;
import database.entities.Admins;
import database.entities.FriendRequests;
import database.entities.Friends;
import database.entities.Questions;
import database.entities.RallyCompleteUsers;
import database.entities.Reports;
import database.entities.Reviews;
import database.entities.StampBookLikes;
import database.entities.StampPads;
import database.entities.Stamps;
import database.entities.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author karin757
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
            
    List<StampRallys> stampRally = srm.search("ス");
    
        try (PrintWriter out = response.getWriter()) {
            for(StampRallys s : stampRally){
                out.println(s.getStamprallyName());
            }
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
