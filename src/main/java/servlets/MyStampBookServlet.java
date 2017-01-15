package servlets;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import database.entities.Stamps;
import database.managers.StampManager;
import database.managers.UserManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import utilities.ImageUtil;

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

        referenceUserName = um.read(referenceUserId).getUserName();
        System.out.println("デバッグ:referenceUserName"+um.read(referenceUserId).getUserName());
        
        Map<String, Object> myStampBook = copy(sm.getMyStampBook(referenceUserId));
        
        //読み込み
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(myStampBook);
        try (PrintWriter out = response.getWriter()) {
            out.println(json);
        }
    }
    
    private Map<String, Object> copy(List<Stamps> fromObj){
        Map<String, Object> retStampMap = new HashMap<>();
        ArrayList<Map<String, Object>> stamps = new ArrayList<>();
        retStampMap.put("userName", referenceUserName);
        for(Stamps i:fromObj){
            Map<String, Object> stamp = new HashMap<>();
            stamp.put("stampId", i.getStampId());
            stamp.put("picture", Base64.encodeBase64(ImageUtil.read(i.getPicturePass())));
            stamp.put("stampName", i.getStampName());
            stamp.put("stampDate", i.getStampDate().toString());
            stamp.put("stampRallyName", i.getStampRallysList().get(0).getStamprallyName());
            stamps.add(stamp);
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
