/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.entities.Sample;
import database.managers.SampleManager;
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
@WebServlet(name = "SampleServlet", urlPatterns = {"/sample"})
public class SampleServlet extends HttpServlet {
    @EJB
    SampleManager sm;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        //新規作成
//        sm.create(new Sample("name"));

        //読み込み
        Sample sample = sm.read(2);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(sample);

        //更新
//        Sample sample = sm.read(2);
//        sample.setName("updatedName");
//        sm.update(sample);

        //削除
//        Sample sample = sm.read(2);
//        sm.remove(sample);

        try (PrintWriter out = response.getWriter()) {
            out.println(json);
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
