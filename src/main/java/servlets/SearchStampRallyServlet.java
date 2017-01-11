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
import utilities.ImageUtil;

@WebServlet(name = "SearchStampRallyServelet", urlPatterns = {"/SearchStampRally"})
public class SearchStampRallyServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        System.out.println("デバッグ:Search:"+request.getParameter("searchKey"));
        
        ObjectMapper mapper = new ObjectMapper();
//        ArrayList<StampRallys> stampRallys = copy( srm.search(request.getParameter("searchKey")));
        ArrayList<StampRallys> stampRallys = srm.search(request.getParameter("searchKey"));
        String json = mapper.writeValueAsString(stampRallys);
        
        
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private ArrayList<StampRallys> copy(ArrayList<StampRallys> fromObj){
        ArrayList<StampRallys> toObj = new ArrayList<StampRallys>();
        StampRallys stampRallys = new StampRallys();
        for(StampRallys fromStampRally : fromObj){
            stampRallys.setStamprallyId(fromStampRally.getStamprallyId());
            stampRallys.setStampThumbnail(fromStampRally.getStampThumbnail());
            stampRallys.setStamprallyName(fromStampRally.getStamprallyName());
            stampRallys.setUsersList(fromStampRally.getUsersList());
            
            toObj.add(stampRallys);
        }
        return toObj;
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
