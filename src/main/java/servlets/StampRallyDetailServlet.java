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
import database.entities.RallyCompleteUsers;
import database.entities.Reviews;
import database.entities.StampRallys;
import database.entities.Stamps;
import database.entities.Users;
import database.managers.ReviewManager;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import utilities.ImageUtil;

@WebServlet(name = "StampRallyDetailServlet", urlPatterns = {"/StampRallyDetail"})
public class StampRallyDetailServlet extends HttpServlet {
    @EJB
    StampRallyManager srm;
    @EJB
    UserManager um;
    @EJB
    ReviewManager rm;
    
    Integer loginUserId;
    Integer referenceUserId;
    Integer stampRallyId;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        //パラメータの受取
        System.out.println("デバッグ:StampRallyDetail:"+request.getParameter("loginUserId"));
        System.out.println("デバッグ:StampRallyDetail:"+request.getParameter("referenceUserId"));
        System.out.println("デバッグ:StampRallyDetail:"+request.getParameter("stampRallyId"));
        loginUserId = Integer.parseInt(request.getParameter("loginUserId"));
        referenceUserId = Integer.parseInt(request.getParameter("referenceUserId"));
        stampRallyId = Integer.parseInt(request.getParameter("stampRallyId"));

        //データベース読み込み
        Users referenceUser = um.read(referenceUserId);
        StampRallys stampRally = srm.read(stampRallyId);
        StampRallyDetailPageData pageData = getPageData(referenceUser, stampRally);
        StampData[] stampData = copyStampData(readStampData(referenceUser, stampRally));
        
        //Androidにレスポンスを送る
        ObjectMapper mapper = new ObjectMapper();
        String json1 = mapper.writeValueAsString(pageData);
        String json2 = mapper.writeValueAsString(stampData);
        try (PrintWriter out = response.getWriter()) {
            out.println(json1);
            out.println(json2);
        }
    }
    
    private StampRallyDetailPageData getPageData(Users referenceUser, StampRallys stampRally){
        StampRallyDetailPageData retPageData = new StampRallyDetailPageData();
        
        retPageData.setStampRallyId(stampRally.getStamprallyId());
        retPageData.setStampRallyTitle(stampRally.getStamprallyName());
        retPageData.setStampRallyComment(stampRally.getStamrallyComment());
        retPageData.setStampRallyCreatorsUserName(stampRally.getUsersList().get(0).getUserName());
        retPageData.setReferenceUserName(referenceUser.getUserName());
        
        //レビューテーブル
        Reviews review = rm.findEvaluatedData(referenceUserId, stampRallyId);
        Integer evaluatedPoint = review == null ? null : review.getReview();
        Double averagedPoint = rm.getAveragePoint(stampRallyId);
        retPageData.setStampRallyReviewPoint(evaluatedPoint);           //参照ユーザーの評価値（スタンプラリーに対する評価値）
        retPageData.setStampRallyReviewAveragePoint(averagedPoint);    //スタンプラリーの平均評価地
        
        //お気に入り
        retPageData.setFavorite(srm.isFavoriteStampRally(um.read(loginUserId), stampRally));
        
        //コンプリートユーザーテーブル
        RallyCompleteUsers RallyCompleteData = srm.getStampRallyCompleteUser(loginUserId, stampRally.getStamprallyId());
        if(RallyCompleteData == null){
            //挑戦したことがないユーザー
            retPageData.setStampRallyChallengeDate(null);     //参照ユーザーのスタンプラリーIDに対する挑戦日時
            retPageData.setStampRallyCompleteDate(null);
            System.out.println("未挑戦:挑戦日時:" + retPageData.getStampRallyChallengeDate());
            System.out.println("未挑戦:クリア日時:" + retPageData.getStampRallyCompleteDate());
        }else if(RallyCompleteData.getAchieveDate() == null){
            //挑戦したことがあるユーザー
            retPageData.setStampRallyChallengeDate(sdf.format(RallyCompleteData.getChallangeDate()));     //参照ユーザーのスタンプラリーIDに対する挑戦日時
            retPageData.setStampRallyCompleteDate(null);
            System.out.println("挑戦済み:挑戦日時:" + retPageData.getStampRallyChallengeDate());
            System.out.println("挑戦済み:クリア日時:" + retPageData.getStampRallyCompleteDate());
        }else{
            //コンプリートしているユーザー
            retPageData.setStampRallyChallengeDate(sdf.format(RallyCompleteData.getAchieveDate()));      //参照ユーザーのスタンプラリーIDに対するクリア日時
            retPageData.setStampRallyCompleteDate(sdf.format(RallyCompleteData.getChallangeDate()));     //参照ユーザーのスタンプラリーIDに対する挑戦日時
            System.out.println("クリア済み:挑戦日時:" + retPageData.getStampRallyChallengeDate());
            System.out.println("クリア済み:クリア日時:" + retPageData.getStampRallyCompleteDate());
        }
        
        return retPageData;
    }
    
    private List<Stamps> readStampData(Users user, StampRallys stampRally){
        List<Stamps> retStampList = new ArrayList<>();
        for (Stamps stamp : stampRally.getStampList()) {
            Stamps insertStamp = stamp;
            for(Stamps myStamp : user.getStampsCollection()){
                if(myStamp.getStampPads().getStamptableId().equals(stamp.getStampPads().getStamptableId())){
                    insertStamp = myStamp;
                    break;
                }
            }
            retStampList.add(insertStamp);
        }
        
        return retStampList;
    }
    
    private StampData[] copyStampData(List<Stamps> fromObj){
        StampData[] retStampDataArray = new StampData[fromObj.size()];
        for(int i=0; i<fromObj.size(); i++){
            StampData data = new StampData();
            Stamps stamp = fromObj.get(i);
            
            data.setStampId(stamp.getStampId());
            data.setStampName(stamp.getStampName());
            data.setStampComment(stamp.getStampComment());
            data.setPicture(ImageUtil.read(stamp.getPicturePass()));
            retStampDataArray[i] = data;
        }
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
}
