package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.StampData;
import data.StampRallyDetailPageData;
import database.entities.StampRallys;
import database.entities.Stamps;
import database.entities.Users;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StampRallyDetailServlet", urlPatterns = {"/StampRallyDetail"})
public class StampRallyDetailServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    @EJB
    UserManager um;
    
    Integer loginUserId;
    Integer referenceUserId;
    Integer stampRallyId;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        //パラメータの受取
        loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        referenceUserId = Integer.parseInt(request.getParameter("referenceUserId"));
        stampRallyId = Integer.parseInt(request.getParameter("stampRallyId"));
        System.out.println("デバッグ:StampRallyDetail:"+request.getParameter("loginUserId"));
        System.out.println("デバッグ:StampRallyDetail:"+request.getParameter("referenceUserId"));
        System.out.println("デバッグ:StampRallyDetail:"+request.getParameter("stampRallyId"));

        //データベース読み込み
        Users user = um.read(referenceUserId);
        System.out.println("aaa"+user.getUserName());
        StampRallyDetailPageData pageData = getPageData(srm.read(stampRallyId), user);
        StampData[] stampData = null;
        
        //Androidにレスポンスを送る
        ObjectMapper mapper = new ObjectMapper();
        String json1 = mapper.writeValueAsString(pageData);
        String json2 = mapper.writeValueAsString(stampData);
        try (PrintWriter out = response.getWriter()) {
            out.println(json1);
            out.println(json2);
        }
    }
    
    private StampRallyDetailPageData getPageData(StampRallys stampRally, Users referenceUser){
        StampRallyDetailPageData retPageData = new StampRallyDetailPageData();
        
        retPageData.setStampRallyId(stampRally.getStamprallyId());
        retPageData.setStampRallyTitle(stampRally.getStamprallyName());
        retPageData.setStampRallyComment(stampRally.getStamrallyComment());
        retPageData.setStampRallyCreatorsUserName(stampRally.getUsersList().get(0).getUserName());
        
        System.out.println(referenceUser.getUserName());
        retPageData.setReferenceUserName(referenceUser.getUserName());
//        retPageData.setStampRallyReviewPoint();           //参照ユーザーの評価値（スタンプラリーに対する評価値）
//        retPageData.setStampRallyReviewAveragePoint();    //スタンプラリーの平均評価地
        
//        retPageData.setStampRallyChallengeDate();     //参照ユーザーのスタンプラリーIDに対する挑戦日時
//        retPageData.setStampRallyCompleteDate();      //参照ユーザーのスタンプラリーIDに対するクリア日時

        return retPageData;
    }
    
    private StampData[] getStampData(ArrayList<Stamps> fromObj){
          StampData[] retStampDataArray = new StampData[fromObj.size()];
                  
//        List<Stamps> stampList = new ArrayList<>();
//        for(Stamps fromStamp : fromObj.getStampList()){
//            Stamps toStamp = new Stamps();
//            toStamp.setStampId(fromStamp.getStampId());
//            toStamp.setStampName(fromStamp.getStampName());
//            toStamp.setStampComment(fromStamp.getStampComment());
//
//            byte[] image = ImageUtil.read(fromStamp.getPicturePass());
//            toStamp.setPicture(image);
//
//            StampPads pad = new StampPads();
//            pad.setLatitude(fromStamp.getStampPads().getLatitude());
//            pad.setLongitude(fromStamp.getStampPads().getLongitude());
//            toStamp.setStampPads(pad);
//            stampList.add(toStamp);
//        }
//        toObj.setStampList(stampList);
        
        return retStampDataArray;
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
