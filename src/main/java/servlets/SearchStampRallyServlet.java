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
import data.StampRallyData;
import java.util.List;
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

        StampRallyData[] stampRallyData = copy(srm.search(decodeString(request.getParameter("searchKey"))));
        
        if(stampRallyData.length < 1){
            System.out.println("debug:search:検索結果がありませんでした。");
        }else{
            System.out.println("debug:search:検索結果の1行目:"+stampRallyData[0].getStampRallyTitle());
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(stampRallyData);
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
