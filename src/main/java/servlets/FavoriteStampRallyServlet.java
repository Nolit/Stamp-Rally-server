package servlets;

import database.entities.StampRallys;
import database.entities.Users;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FavoriteStampRallyServlet", urlPatterns = {"/favorite"})
public class FavoriteStampRallyServlet extends HttpServlet {
    @EJB
    UserManager um;
    @EJB
    StampRallyManager srm;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int stampRallyId = Integer.valueOf(request.getParameter("stampRallyId"));
        Users user = um.readByEmailAndPassword(email, password);
        StampRallys stampRally = srm.read(stampRallyId);
        
        boolean isFavorite = Boolean.valueOf(request.getParameter("favorite"));
        if(isFavorite){
            addFavorite(user, stampRally);
            return;
        }
        srm.removeFavoriteStampRally(user, stampRally);
    }
    
    private void addFavorite(Users user, StampRallys stampRally){        
        try{
            srm.addFavoriteStampRally(user, stampRally);
        }catch(Exception ex){
            //既にお気に入り登録されている場合等の例外
            System.err.println("既にお気に入り登録されている場合等の例外");
        }
    }
}
