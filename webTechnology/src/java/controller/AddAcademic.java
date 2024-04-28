/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AcademicDAO;
import dao.CourseDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Academic;
import model.AcademicType;

/**
 *
 * @author jms
 */
@WebServlet(name = "Academic", urlPatterns = {"/addAcademic"})
public class AddAcademic extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Retrieve form parameters
        String name = request.getParameter("name");
        String type = request.getParameter("type");
      
        
        // Create Teacher object
        Academic academic = new Academic();
        academic.setName(name);
        academic.setType(AcademicType.valueOf(type)); // Convert String to Enum
        // Retrieve Course object based on courseId
        AcademicDAO dao = new AcademicDAO();
        dao.addAcademic(academic);
        

        response.sendRedirect("confirmation.html");
    }
}