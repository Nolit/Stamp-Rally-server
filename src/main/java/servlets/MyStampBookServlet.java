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
import database.entities.Stamps;
import database.managers.StampManager;
import database.managers.UserManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MyStampBookServlet", urlPatterns = {"/MyStampBook"})
public class MyStampBookServlet extends HttpServlet {
    @EJB
    StampManager sm;
    @EJB
    UserManager um;
    int referenceUserId;
    String referenceUserName;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        
        referenceUserId = Integer.parseInt(request.getParameter("referenceUserId"));
        System.out.println("デバッグ:MyStampBookServlet:referenceUserId:"+referenceUserId);

        //読み込み
        ObjectMapper mapper = new ObjectMapper();
        referenceUserName = um.read(referenceUserId).getUserName();
        System.out.println("デバッグ:referenceUserName"+um.read(referenceUserId).getUserName());
        
        Map<String, Object> myStampBook = copy(sm.getMyStampBook(referenceUserId));
        
        ArrayList<Stamps> hoge = (ArrayList<Stamps>) myStampBook.get("Stamps");
        
        System.out.println("debug:"+hoge.get(0).getStampName());
        
        String json = mapper.writeValueAsString(myStampBook);
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private Map<String, Object> copy(List<Stamps> fromObj){
        Map<String, Object> retStampMap = new HashMap<>();
        ArrayList<Stamps> stamps = new ArrayList<>();
        retStampMap.put("userName", referenceUserName);
        for(Stamps i:fromObj){
            Stamps stamp = new Stamps();
            stamp.setStampId(i.getStampId());
            stamp.setPicturePass(i.getPicturePass());
            stamp.setStampName(i.getStampName());
            stamp.setStampDate(i.getStampDate());
            stamps.add(i);
        }
        retStampMap.put("Stamps", stamps);
        return retStampMap;
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
