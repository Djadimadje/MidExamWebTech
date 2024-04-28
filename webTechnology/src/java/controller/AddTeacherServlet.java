/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDao;
import dao.TeacherDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Qualification;
import model.Teacher;


/**
 *
 * @author jms
 */
@WebServlet(name = "TeacherServlet", urlPatterns = {"/addTeacher"})
public class AddTeacherServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Retrieve form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String qualification = request.getParameter("qualification");
        int courseId = Integer.parseInt(request.getParameter("courseId")); // Assuming courseId is provided in the form
        
        // Create Teacher object
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setQualification(Qualification.valueOf(qualification)); // Convert String to Enum
        // Retrieve Course object based on courseId
        CourseDao courseDao = new CourseDao();
        Course course = courseDao.getCourseById(courseId);
        teacher.setCourse(course);
        
        // Save Teacher object using TeacherDao
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.addTeacher(teacher);
        
        // Redirect to a confirmation page
        response.sendRedirect("confirmation.html");
    }
}