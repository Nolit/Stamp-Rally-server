/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.ImageUtil;

@WebServlet(name = "OtherPageServlet", urlPatterns = {"/otherPage"})
public class OtherPageServlet extends HttpServlet {
    @EJB
    UserManager um;
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int referenceUserId = Integer.valueOf(request.getParameter("referenceUserId"));
        Users loginUser = um.readByEmailAndPassword(email, password);
        Users referenceUser = um.read(referenceUserId);
        
        Users responseUser = new Users();
        System.out.println("他人ページ : " + referenceUser.getUserName());
        byte[] thumbnail = ImageUtil.read(referenceUser.getThumbnail());
        responseUser.setThumbnailData(thumbnail);
        responseUser.setProfile(referenceUser.getProfile());
        responseUser.setUserName(referenceUser.getUserName());
        responseUser.followUserCount = um.getFollowCount(referenceUser.getUserId());
        responseUser.followerCount = um.getFollowerCount(referenceUser.getUserId());
        responseUser.isFollow = um.isFollow(loginUser.getUserId(), referenceUserId);
        
        ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.addMixInAnnotations(Users.class, JsonIgnoreFilter.class);
        String json = mapper.writeValueAsString(responseUser);

        try (PrintWriter out = response.getWriter()) {
            out.println(json);          
        }
    }
   
    static interface JsonIgnoreFilter {
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
        @JsonIgnore String getThumbnail();
        @JsonIgnore String getMailAddress();
        @JsonIgnore String getPassword();
        @JsonIgnore Date getDeleteDate();
    }
}
