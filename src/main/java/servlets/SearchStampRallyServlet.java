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
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import utilities.ImageUtil;

@WebServlet(name = "SearchStampRallyServelet", urlPatterns = {"/SearchStampRally"})
public class SearchStampRallyServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    
    List<Map<String,Object>> searchStampRallyList;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");        
        System.out.println("デバッグ:Search:"+request.getParameter("searchKey"));

        searchStampRallyList = copy(srm.search(request.getParameter("searchKey")));
        
        if(searchStampRallyList.size() < 1){
            System.out.println("debug:search:検索結果:検索結果がありませんでした。");
        }else{
            System.out.println("debug:search:検索結果"+searchStampRallyList.get(0).get("stampRallyTitle"));
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(searchStampRallyList);
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private List<Map<String,Object>> copy(List<StampRallys> fromObj){
        List<Map<String,Object>> ret = new ArrayList<>() ;
        Map<String,Object> make = new HashMap<>();
        for(StampRallys index : fromObj){
            make.put("stampRallyId", index.getStamprallyId());
            make.put("stampRallyThumbnail", Base64.encodeBase64(ImageUtil.read(index.getStampThumbnail())));
            make.put("stampRallyTitle", index.getStamprallyName());
            make.put("stampRallyCreatorUserName", index.getUsersList().get(0).getUserName());
            make.put("stampRallyCreatorUserId", index.getUsersList().get(0).getUserId());
            ret.add(make);
        }
        return ret;
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
