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

@WebServlet("/editCourse")
public class EditCourseServlet extends HttpServlet {

    private final CourseDao courseDAO = new CourseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("courseId"));
        Course course = courseDAO.getCourseById(courseId);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel='stylesheet' type='text/css' href='css/style.css'>");
        out.println("<title>Edit Course</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Edit Course</h2>");
        out.println("<form action='updateCourses' method='POST'>");
        out.println("<input type='hidden' name='courseId' value='" + courseId + "'>");
        out.println("Course Name: <input type='text' name='cname' value='" + course.getCourse_name() + "'><br>");
        out.println("Course Code: <input type='text' name='ccode' value='" + course.getCourse_code() + "'><br>");
        out.println("Semester: <input type='text' name='semId' value='" + course.getSemester() + "'><br>");
        out.println("<button type='submit'>Update</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
