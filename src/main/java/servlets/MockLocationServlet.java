/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilities.LocationObserver;

/**
 *
 * @author karin757
 */
@WebServlet(name = "MockLocationServlet", urlPatterns = {"/mockLocation"})
public class MockLocationServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.getWriter().print("<h1>Result</h1>");
        try{
            double latitude = Double.valueOf(request.getParameter("latitude"));
            double longitude = Double.valueOf(request.getParameter("longitude"));
            LocationObserver.observer.fire(latitude, longitude);
            response.getWriter().print("Success");
        }catch(Exception ex){
            ex.printStackTrace();
            response.getWriter().print("Failed");
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
}
