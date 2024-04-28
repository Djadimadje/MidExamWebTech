package controller;

import dao.TeacherDao;
import model.Teacher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/teachers")
public class DisplayTeacherServlet extends HttpServlet {
    private final TeacherDao teacherDAO = new TeacherDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Teacher> teachers = teacherDAO.getAllTeachers();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Teacher List</title>");
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

        out.println("<div class=\"navbar\">");
        out.println("<a href=\"#home\">Home</a>");
        out.println("<a href=\"#about\">About</a>");
        out.println("<a href=\"#contact\">Contact</a>");
        out.println("</div>");

        out.println("<div class=\"container\">");
        out.println("<h2>Teacher List</h2>");

        out.println("<table class='table'>");
        out.println("<tr>");
        out.println("<th>ID</th>");
        out.println("<th>First Name</th>");
        out.println("<th>Last Name</th>");
        out.println("<th>Qualification</th>");
        out.println("</tr>");

        if (teachers != null) {
            for (Teacher teacher : teachers) {
                out.println("<tr>");
                out.println("<td>" + teacher.getId() + "</td>");
                out.println("<td>" + teacher.getFirstName() + "</td>");
                out.println("<td>" + teacher.getLastName() + "</td>");
                out.println("<td>" + teacher.getQualification() + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='4'>No teachers found</td></tr>");
        }

        out.println("</table>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
