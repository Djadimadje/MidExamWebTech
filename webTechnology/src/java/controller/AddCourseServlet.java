/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CourseDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import dao.SemesterDao;
import model.Semester;

/**
 *
 * @author jms
 */
@WebServlet(name = "addCourse", urlPatterns = {"/addCourse"})
public class AddCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form parameters
        String courseCode = request.getParameter("ccode");
        String courseName = request.getParameter("cname");
        int semesterId = Integer.parseInt(request.getParameter("semId")); // Assuming you get semesterId from the form
        
        // Retrieve Semester object based on semesterId
        SemesterDao semesterDao = new SemesterDao();
        Semester semester = semesterDao.getSemesterById(semesterId);
        
        // Create Course object
        Course course = new Course();
        course.setCourse_code(courseCode);
        course.setCourse_name(courseName);
        course.setSemester(semester);
        
        // Save Course object using CourseDao
        CourseDao courseDao = new CourseDao();
        courseDao.addCourse(course);
        
        // Redirect to a confirmation page
        response.sendRedirect("displayCourses");
    }
}
