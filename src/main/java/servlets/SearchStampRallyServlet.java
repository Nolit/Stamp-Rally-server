package servlets;

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
import java.util.ArrayList;
import java.util.List;
import java.io.*;

@WebServlet(name = "SearchStampRallyServelet", urlPatterns = {"/SearchStampRally"})
public class SearchStampRallyServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        System.out.println("デバッグ:Search:"+request.getParameter("searchKey"));
        
        ObjectMapper mapper = new ObjectMapper();
        srm.search(request.getParameter("searchKey"));
        List<StampRallys> stampRallys = srm.search(request.getParameter("searchKey"));
//        List<StampRallys> hoge = srm.search(request.getParameter("searchKey"));
        
        if(stampRallys.size() < 1){
            System.out.println("debug:search:検索結果:検索結果がありませんでした。");
        }else{
            System.out.println("debug:search:検索結果"+stampRallys.get(0).getStamprallyName());
        }
        
        String json = mapper.writeValueAsString(stampRallys);
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private List<StampRallys> copy(List<StampRallys> fromObj){
        ArrayList<StampRallys> toObj = new ArrayList<>();
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
