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
import database.entities.StampPads;
import database.entities.Stamps;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "MapServlet", urlPatterns = {"/map"})
public class MapServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        //読み込み
        StampRallys stampRally = copy(srm.read(2));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(stampRally);

        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private StampRallys copy(StampRallys fromObj){
        StampRallys toObj = new StampRallys();
        toObj.setStamprallyId(fromObj.getStamprallyId());
        toObj.setStamprallyName(fromObj.getStamprallyName());
        toObj.setStamrallyComment(fromObj.getStamrallyComment());
        List<Stamps> stampList = new ArrayList<>();
        for(Stamps fromStamp : fromObj.getStampList()){
            Stamps toStamp = new Stamps();
            toStamp.setStampId(fromStamp.getStampId());
            toStamp.setStampName(fromStamp.getStampName());
            toStamp.setStampComment(fromStamp.getStampComment());
            StampPads pad = new StampPads();
            pad.setLatitude(fromStamp.getStampPads().getLatitude());
            pad.setLongitude(fromStamp.getStampPads().getLongitude());
            toStamp.setStampPads(pad);
            stampList.add(toStamp);
        }
        toObj.setStampList(stampList);

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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
