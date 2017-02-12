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
import database.managers.StampManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import utilities.ImageUtil;
@WebServlet(name = "MapServlet", urlPatterns = {"/map"})
    public class MapServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    @EJB
    StampManager sm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        int stampRallyId = Integer.valueOf(request.getParameter("playingStampRallyId"));
        int userId = Integer.valueOf(request.getParameter("userId"));
        System.out.println("MapServlet : "+stampRallyId);

        List<Stamps> myStamps = sm.findByUserId(userId);
        StampRallys nonCopied = srm.read(stampRallyId);
        calcurateGotStamps(nonCopied, myStamps);
        StampRallys stampRally = copy(nonCopied);
        
        
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
            toStamp.isHaving = fromStamp.isHaving;
            
            byte[] image = ImageUtil.read(fromStamp.getPicturePass());
            toStamp.setPicture(image);
            
            StampPads pad = new StampPads();
            pad.setLatitude(fromStamp.getStampPads().getLatitude());
            pad.setLongitude(fromStamp.getStampPads().getLongitude());
            toStamp.setStampPads(pad);
            stampList.add(toStamp);
        }
        toObj.setStampList(stampList);

        return toObj;
    }
    
    //StampRallyに含まれるStampの内、myStampsに存在するもののisHavingをtrueにする
    private void calcurateGotStamps(final StampRallys stampRally, List<Stamps> myStamps){
        for (Stamps stamp : stampRally.getStampList()) {
            for (Stamps myStamp : myStamps) {
                System.out.println(stamp.getStampPads().getStamptableId() + "");
                System.out.println(stamp.getStampPads() + "");
                System.out.println(stamp.getStampId() + "");
                if(myStamp.getStampPads().getStamptableId().equals(stamp.getStampPads().getStamptableId())){
                    System.out.println("equal!");
                    stamp.isHaving = true;
                    break;
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
}
