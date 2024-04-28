package controller;
import dao.CourseDao;
import dao.SemesterDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;
import model.Semester;

@WebServlet("/updateCourses")
public class UpdateCourse extends HttpServlet {
    private CourseDao courseDAO; // Assuming you have a CourseDao class
    
    @Override
    public void init() throws ServletException {
        // Initialize your CourseDAO instance here
        courseDAO = new CourseDao(); // Initialize it according to your implementation
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the courseId, courseName, courseCode, and semester parameters from the request
        String courseCode = req.getParameter("ccode");
        String courseName = req.getParameter("cname");
        int semesterId = Integer.parseInt(req.getParameter("semId"));
        
        // Check if any of the parameters are null or empty
        SemesterDao semesterDao = new SemesterDao();
        Semester semester = semesterDao.getSemesterById(semesterId);
                
                // Create a Course object with the provided parameters
                Course updatedCourse = new Course();
                updatedCourse.setCourse_name(courseName);
                updatedCourse.setCourse_code(courseCode);
                updatedCourse.setSemester(semester);
                
                // Call the updateCourse method from your CourseDao
                courseDAO.updateCourse(updatedCourse);
                
                // Send a success response
                resp.getWriter().write("Course updated successfully");
            
            // If any of the parameters are missing or empty
            resp.getWriter().write("One or more parameters are missing");
        }
    }
