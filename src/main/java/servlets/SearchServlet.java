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
import org.omg.IOP.Encoding;
import java.io.*;
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
        response.setContentType("application/json;charset=UTF-8");

            
    String sr = request.getParameter("stamprally");
    String decodesr = decodeString(sr);
    List<StampRallys> stampRally = srm.search(decodesr);
    
        try (PrintWriter out = response.getWriter()) {
            for(StampRallys s : stampRally){
                if(s.getStamrallyComment().length()<20)
                {
                out.println(s.getStamprallyName() + " " + s.getStamrallyComment() + " " + s.getStampThumbnail() + " " + s.getUsersList().get(0).getUserName());
                }else{
                out.println(s.getStamprallyName() + " " + s.getStamrallyComment().substring(0, 20) + " " + s.getStampThumbnail() + " " + s.getUsersList().get(0).getUserName());
                }
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
    
    protected String decodeString(String decodesr){
    try {
      byte[] byteData = decodesr.getBytes("ISO_8859_1");
      decodesr = new String(byteData, "UTF-8");
    }catch(UnsupportedEncodingException e){
      return null;
    }

    return decodesr;
  }
    
}
