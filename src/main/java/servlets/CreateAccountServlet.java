package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import utilities.StringDecoder;

@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/createAccount"})
public class CreateAccountServlet extends HttpServlet {
    @EJB
    UserManager um;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String userName = StringDecoder.decode(request.getParameter("userName"));
        String password = request.getParameter("password");
        Users newUser = new Users(email, password, userName);
        
        try {
            um.create(newUser);
        } catch(Exception ex) {
            newUser = null;
        } finally {
            String json = new ObjectMapper().writeValueAsString(newUser);
            try(PrintWriter out = response.getWriter()) {
                out.print(json);
            }
        }
    }
}
