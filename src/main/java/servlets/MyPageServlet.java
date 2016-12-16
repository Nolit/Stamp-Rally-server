/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.Activities;
import database.entities.Admins;
import database.entities.FriendRequests;
import database.entities.Friends;
import database.entities.Questions;
import database.entities.RallyCompleteUsers;
import database.entities.Reports;
import database.entities.Reviews;
import database.entities.Sample;
import database.entities.StampBookLikes;
import database.entities.StampPads;
import database.entities.StampRallys;
import database.entities.Stamps;
import database.entities.Users;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyPageServlet", urlPatterns = {"/myPage"})
public class MyPageServlet extends HttpServlet {
    @EJB
    UserManager um;
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        //新規作成
//        sm.create(new Sample("name"));

        //読み込み
        Users user = um.read(2);
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixInAnnotations(Users.class, MyPageServlet.OgoriView.class);
        String json = mapper.writeValueAsString(user);
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
    }// </editor-fold
   
    static interface OgoriView {
        @JsonIgnore String getPrice();
        @JsonIgnore Collection<Stamps> getStampsCollection();
        @JsonIgnore Collection<StampPads> getStampPadsCollection();
        @JsonIgnore Collection<Activities> getActivitiesCollection();
        @JsonIgnore Collection<Reviews> getReviewsCollection();
        @JsonIgnore Activities getActivityId();
        @JsonIgnore StampRallys getStamprallyId();
        @JsonIgnore Collection<Friends> getFriendsCollection();
        @JsonIgnore Collection<Friends> getFriendsCollection1();
        @JsonIgnore Collection<Questions> getQuestionsCollection();
        @JsonIgnore Collection<FriendRequests> getFriendRequestsCollection();
        @JsonIgnore Collection<FriendRequests> getFriendRequestsCollection1();
        @JsonIgnore Collection<Reports> getReportsCollection();
        @JsonIgnore Collection<Reports> getReportsCollection1();
        @JsonIgnore Collection<RallyCompleteUsers> getRallyCompleteUsersCollection();
        @JsonIgnore StampBookLikes getStampBookLikes();
        @JsonIgnore Collection<Admins> getAdminsCollection();
        @JsonIgnore Collection<Activities> getActivitiesCollection1();
        @JsonIgnore Collection<StampRallys> getStampRallysCollection();
        @JsonIgnore Collection<Questions> getQuestionsCollection1();
    }
}