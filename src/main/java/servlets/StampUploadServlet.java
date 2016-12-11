package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.Users;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "StampUploadServlet", urlPatterns = {"/stampUpload"})
public class StampUploadServlet extends HttpServlet {
    @EJB
    UserManager um;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Users user = um.readByEmailAndPassword(email, password);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(user == null){
                out.println(false);
                return;
            }
            out.println(true);
//            List<Map<String, Object>> stampList = new ObjectMapper().readValue(request.getParameter("stampList"), List.class);
//            for(Map<String, Object> stamp : stampList){
//                out.println(stamp.get("stampId"));
//                out.println(stamp.get("stampRallyId"));
//                out.println(stamp.get("title"));
//                out.println(stamp.get("picture"));
//            }
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
    }

}
