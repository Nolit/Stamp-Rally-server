package servlets;

import database.entities.StampPads;
import database.entities.Stamps;
import database.entities.Users;
import database.managers.StampManager;
import database.managers.StampPadsManager;
import database.managers.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.ImageSaver;

@WebServlet(name = "StampUploadServlet", urlPatterns = {"/stampUpload"})
public class StampUploadServlet extends HttpServlet {
    @EJB
    UserManager um;
    @EJB
    StampPadsManager spm;
    @EJB
    StampManager sm;      
//[{"stampRallyId":1,"note":1,"stampId":1,"latitude":0,"title":1,"picture":1481452995217}]
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
                
//            List<Map<String, Object>> stampList = new ObjectMapper().readValue(request.getParameter("stampList"), List.class);
            List<Map<String, Object>> stampList = a();
            for(Map<String, Object> stampData : stampList){
                Stamps myStamp = new Stamps(stampData);
                int stampId = (int)stampData.get("stampId");
                StampPads pad;
                if(stampId == 0){
                    pad = new StampPads(stampData);
                    spm.create(pad);
                }else{
                    pad = spm.read(stampId);
                }
                myStamp.setStamptableId(pad);
                myStamp.setUserId(user);
                String picturePath = ImageSaver.create(user.getUserId(), (byte[]) stampData.get("picture"));
                myStamp.setPicturePass(picturePath);
                sm.create(myStamp);
            }
            out.println(false);
        }
    }

    private List<Map<String, Object>> a(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> stamp = new HashMap<>();
        stamp.put("stampId", 1);
        stamp.put("stampRallyId", 1);
        stamp.put("latitude", 0);
        stamp.put("latitude", 0);
        stamp.put("title", "タイトル1");
        stamp.put("note", "ノート１");
        stamp.put("picture", new byte[0]);
        stamp.put("createDate", System.currentTimeMillis());
        list.add(stamp);
        return list;
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
