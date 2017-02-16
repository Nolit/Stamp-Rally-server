package servlets;

import com.fasterxml.jackson.annotation.JsonInclude;
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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @EJB
    UserManager um;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        String mail = request.getParameter("mailAddress");
        String pass = request.getParameter("password");
        
        System.out.println("デバッグ:ログインメールアドレス" + mail);
        System.out.println("デバッグ:ログインパスワード" + pass);
        
        Users loginUser = um.readByEmailAndPassword(mail,pass);
        if(loginUser != null){
            System.out.println("デバッグ:ログイン成功！" + loginUser.getUserName() +"でログインしました。");
        }else{   
            System.out.println("デバッグ:ログインユーザーがnullです");
        }
        
        try (PrintWriter out = response.getWriter()) {
            if(loginUser != null){
                out.print(loginUser.getUserId() + "," + loginUser.getMailAddress() + "," + loginUser.getPassword());
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
    }

}
