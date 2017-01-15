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
import data.StampData;
import database.entities.Stamps;
import database.managers.StampManager;
import database.managers.UserManager;
import java.text.SimpleDateFormat;
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
        
        StampData[] stampDataSet = copy(sm.getMyStampBook(referenceUserId));
        
        //読み込み
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(stampDataSet);
        try (PrintWriter out = response.getWriter()) {
            out.println(referenceUserName);
            out.println(json);
        }
    }
    
    private StampData[] copy(List<Stamps> fromObj){
        StampData[] stampDataSet = new StampData[fromObj.size()];
        for(int index = 0;index < fromObj.size();index++){
            Stamps i = fromObj.get(index);
            StampData data = new StampData();
            data.setStampId(i.getStampId());
            data.setPicture(ImageUtil.read(i.getPicturePass()));
            data.setStampName(i.getStampName());
            data.setStampDate(new SimpleDateFormat("yyyy年MM月dd日hh時mm分").format(i.getStampDate()));
            data.setStampRallyName(i.getStampRallysList().get(0).getStamprallyName());
            stampDataSet[index] = data;
        }
        
        return stampDataSet;
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
