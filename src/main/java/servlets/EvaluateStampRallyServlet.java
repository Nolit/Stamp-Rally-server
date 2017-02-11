package servlets;

import database.entities.Reviews;
import database.entities.ReviewsPK;
import database.entities.StampRallys;
import database.entities.Users;
import database.managers.ReviewManager;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EvaluateStampRallyServlet", urlPatterns = {"/evaluation"})
public class EvaluateStampRallyServlet extends HttpServlet {
    @EJB
    UserManager um;
    @EJB
    StampRallyManager srm;
    @EJB
    ReviewManager rm;
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int stampRallyId = Integer.valueOf(request.getParameter("stampRallyId"));
        
        Users user = um.readByEmailAndPassword(email, password);
        StampRallys stampRally = srm.read(stampRallyId);
        int point = Integer.valueOf(request.getParameter("point"));
        
        rm.createOrUpdate(user, stampRally, point);
    }
}
