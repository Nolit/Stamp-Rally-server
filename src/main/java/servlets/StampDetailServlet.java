package servlets;

import database.managers.SampleManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.StampPads;
import database.entities.StampRallys;
import database.entities.Stamps;
import database.managers.StampManager;
import database.managers.StampRallyManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilities.ImageUtil;

@WebServlet(name = "StampDetailServlet", urlPatterns = {"/StampDetail"})
public class StampDetailServlet extends HttpServlet {
    @EJB
    StampManager sm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        System.out.println("デバッグ:StampDetail:"+request.getParameter("stampId"));

        //読み込み
        ObjectMapper mapper = new ObjectMapper();
        Stamps stamp = sm.read(Integer.parseInt(request.getParameter("stampId")));
        
        byte[] image = ImageUtil.read(stamp.getPicturePass());
        stamp.setPicture(image);
        
        String json = mapper.writeValueAsString(stamp);
        
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Map<String, Object>> a(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> stamp = new HashMap<>();
        stamp.put("stampId", 1);
        stamp.put("stampRallyId", 1);
        stamp.put("latitude", 0);
        stamp.put("latitude", 0);
        stamp.put("title", "タイトル1");
        stamp.put("note", "ノート１");
        stamp.put("picture", "");
        stamp.put("title", 1);
        stamp.put("note", 1);
        stamp.put("picture", 1);
        stamp.put("picture", System.currentTimeMillis());
        list.add(stamp);
        return list;
    }
}
