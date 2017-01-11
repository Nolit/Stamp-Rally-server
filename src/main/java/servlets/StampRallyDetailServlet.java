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
import database.managers.StampRallyManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utilities.ImageUtil;

@WebServlet(name = "StampRallyDetailServlet", urlPatterns = {"/StampRallyDetail"})
public class StampRallyDetailServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    
    String loginUserId;
    String referenceUserId;
    String stampRallyId;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        loginUserId = request.getParameter("loginUserId");
        referenceUserId = request.getParameter("referenceUserId");
        stampRallyId = request.getParameter("stampRallyId");
        
        System.out.println("デバッグ:"+request.getParameter("loginUserId"));
        System.out.println("デバッグ:"+request.getParameter("referenceUserId"));
        System.out.println("デバッグ:"+request.getParameter("stampRallyId"));
        
        //読み込み
        ObjectMapper mapper = new ObjectMapper();
        StampRallys stampRally = copy(srm.read(Integer.parseInt(stampRallyId)));
        String json = mapper.writeValueAsString(stampRally);
        
        /* ここにスタンプラリー獲得先テーブルからreferenceUserIdとstampRallyIdで検索する
           結果を下のcopyの中でtoStampに入れていく
        */

        //更新
//        Sample sample = sm.read(2);
//        sample.setName("updatedName");
//        sm.update(sample);
        
        
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private StampRallys copy(StampRallys fromObj){
        StampRallys toObj = new StampRallys();
        toObj.setStamprallyId(fromObj.getStamprallyId());
        toObj.setStamprallyName(fromObj.getStamprallyName());
        toObj.setStamrallyComment(fromObj.getStamrallyComment());
        toObj.setUsersList(fromObj.getUsersList());
       
        List<Stamps> stampList = new ArrayList<>();
        for(Stamps fromStamp : fromObj.getStampList()){
            Stamps toStamp = new Stamps();
            toStamp.setStampId(fromStamp.getStampId());
            toStamp.setStampName(fromStamp.getStampName());
            toStamp.setStampComment(fromStamp.getStampComment());

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
