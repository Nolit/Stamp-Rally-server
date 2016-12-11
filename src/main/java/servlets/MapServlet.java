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
import database.entities.Stamps;
@WebServlet(name = "MapServlet", urlPatterns = {"/map"})
public class MapServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        //新規作成
//        sm.create(new Sample("name"));

        //読み込み
        StampRallys stampRally = srm.read(1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(stampRally);
        
//        StampRallys stampRally2 = new ObjectMapper().readValue(json, StampRallys.class);
//        try (PrintWriter out = response.getWriter()) {
//    
//        for(Stamps i:stampRally.getStampsCollection()){
//            out.println(i.getStampName());
//        }
//        
//        }
        
        

        //更新
//        Sample sample = sm.read(2);
//        sample.setName("updatedName");
//        sm.update(sample);

        //削除
//        Sample sample = sm.read(2);
//        sm.remove(sample);

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

}
