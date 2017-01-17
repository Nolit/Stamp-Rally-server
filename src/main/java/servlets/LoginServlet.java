package servlets;

import database.entities.Users;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 *
 * @author karin757
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @EJB
    UserManager um;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        String mail = request.getParameter("mailAddress");
        String pass = request.getParameter("password");
        
        System.out.println(mail+" : " + pass);
        
        boolean isLogin = um.login(mail,pass);
        Users user = um.readByEmailAndPassword(mail, pass);
        
        int userId = 0;
        if(isLogin){
            userId = user.getUserId();
            try (PrintWriter out = response.getWriter()) {
                out.println(userId);
            }
        }else{
            try (PrintWriter out = response.getWriter()) {
                out.println(userId);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
