package controller;

import dao.SemesterDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Semester;

@WebServlet("/displaySemester")
public class DisplaySemesterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private SemesterDao studentDAO;

    public void init() {
        studentDAO = new SemesterDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Semester> students = studentDAO.getAllSemesters();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Student List</title>");
        // Bootstrap CSS link
        out.println("<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("<style>");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println(".navbar { overflow: hidden; background-color: #333; }");
        out.println(".navbar a { float: left; display: block; color: #f2f2f2; text-align: center; padding: 14px 20px; text-decoration: none; }");
        out.println(".navbar a:hover { background-color: #ddd; color: black; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Navigation bar
        out.println("<div class=\"navbar\">");
        out.println("<a href=\"#home\">Home</a>");
        out.println("<a href=\"#about\">About</a>");
        out.println("<a href=\"#contact\">Contact</a>");
        out.println("</div>");

        out.println("<div class=\"container\">");
        out.println("<h2>Student List</h2>");

        // Add button to trigger modal
        out.println("<a href=\"addTeacher.html\" class=\"btn btn-primary\">Add Teacher</a>");
        out.println("<a href=\"addStudent.html\" class=\"btn btn-primary\">Add Student</a>");
        out.println("<a href=\"addCourse.html\" class=\"btn btn-primary\">Add Course</a>");
        out.println("<a href=\"addSemester.html\" class=\"btn btn-primary\">Add Semester</a>");
        out.println("<a href=\"registration.html\" class=\"btn btn-primary\">Make Registration</a>");
        out.println("<a href=\"academic.html\" class=\"btn btn-primary\">Academic</a>");

        // Bootstrap modal for adding student form
//        

        out.println("<table class='table'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Start Date</th>");
        out.println("<th>End Date</th>");
        out.println("<th>Action</th>");
        // Add more columns as needed
        out.println("</tr>");
        if (students != null) {
            for (Semester student : students) {
                out.println("<tr>");
                out.println("<td>" + student.getId() + "</td>");
                out.println("<td>" + student.getName()+ "</td>");
                out.println("<td>" + student.getStartDate()+ "</td>");
                out.println("<td>" + student.getEndDate()+ "</td>");
                out.println("<td><a href='editSemester?id=" + student.getId() + "' class='btn btn-primary'>Edit</a></td>");
                out.println("<td><a href='deleteStudent?id=" + student.getId() + "' class='btn btn-danger'>Delete</a></td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='2'>No students found</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
