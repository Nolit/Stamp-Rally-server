package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entities.StampPads;
import database.entities.StampRallys;
import database.entities.Stamps;
import database.entities.Users;
import database.managers.StampManager;
import database.managers.StampPadsManager;
import database.managers.StampRallyManager;
import database.managers.UserManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.commons.codec.binary.Base64;
import utilities.ImageUtil;

@WebServlet(name = "StampUploadServlet", urlPatterns = {"/stampUpload"})
public class StampUploadServlet extends HttpServlet {
    @EJB
    UserManager um;
    @EJB
    StampPadsManager spm;
    @EJB
    StampManager sm;     
    @EJB
    StampRallyManager srm;  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mailAddress = request.getParameter("mailAddress");
        String password = request.getParameter("password");

        Users user = um.readByEmailAndPassword(mailAddress, password);
        if(user == null){
            return;
        }

        String stampListJson = new String(request.getParameter("stampList").getBytes("ISO_8859_1"), "UTF-8");
        List<Map<String, Object>> stampList = new ObjectMapper().readValue(stampListJson, List.class);
//        List<Map<String, Object>> stampList = a();
        saveStamp(stampList, user);
        Object[] completedStampIds = extractCompletedAsId(stampList, user);
        if(completedStampIds.length == 0){
            return;
        }
        System.out.println("クリア!");
        for (Object completedStampId : completedStampIds) {
            srm.clearStampRally(srm.read((int) completedStampId), user);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(new ObjectMapper().writer().writeValueAsString(true));
        }
    }
    
    private void saveStamp(List<Map<String, Object>> stampList, Users user){
        for(Map<String, Object> stampData : stampList){
            int stampId = (int)stampData.getOrDefault("stampId", 0);
            StampPads pad;
            if(stampId == 0){
                pad = new StampPads(stampData);
                pad.setUserId(user);
                spm.create(pad);
            }else{
                pad = sm.read(stampId).getStampPads();
            }
            
            byte[] picture = Base64.decodeBase64((String) stampData.get("picture"));
            String picturePath = ImageUtil.createStamp(user.getUserId(), picture);
            
            Stamps myStamp = new Stamps(stampData);
            myStamp.setStampPads(pad);
            myStamp.setUserId(user);
            myStamp.setPicturePass(picturePath);
            
            sm.create(myStamp);
//            sm.flash();
        }
    }
    
    private Object[] extractCompletedAsId(List<Map<String, Object>> stampList, final Users user){
        return stampList.stream()
        .map(new Function<Map<String, Object>, Integer>() {
            @Override
            public Integer apply(Map<String, Object> stampData) {
                return (Integer) stampData.get("stampRallyId");
            }
        })
        .distinct()
        .filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer stampRallyId) {
                return !stampRallyId.equals(0) && isComplete(user, stampRallyId);
            }
        }).toArray();
    }

    private boolean isComplete(final Users user, int stampRallyId){
        StampRallys stampRally = srm.read(stampRallyId);
        final List<Stamps> myStamps = sm.findByUserId(user.getUserId());
        return stampRally.getStructurePads().stream().allMatch(new Predicate<StampPads>() {
            @Override
            public boolean test(final StampPads pad) {
                for(Stamps myStamp : myStamps){
                    if(pad.getStamptableId().equals(myStamp.getStampPads().getStamptableId())){
                        return true;
                    }
                }
                return false;
            }
        });
    }
   
//    private boolean isComplete(final Users user, int stampRallyId){
//        System.out.println("check stampRallyId : " + stampRallyId);
//        StampRallys stampRally = srm.read(stampRallyId);
//        return stampRally.getStructurePads().stream().allMatch(new Predicate<StampPads>() {
//            @Override
//            public boolean test(final StampPads pad) {
//                System.out.println("check pad : " + pad.getStamptableId());
//                for(Stamps myStamp : user.getStampsCollection()){
//                    System.out.println("my stamp : " + myStamp.getStampPads().getStamptableId());
//                    if(pad.getStamptableId().equals(myStamp.getStampPads().getStamptableId())){
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });
//    }

    private List<Map<String, Object>> a(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> stamp = new HashMap<>();
        stamp.put("stampId", 24);
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
