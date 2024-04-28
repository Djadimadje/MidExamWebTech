import dao.CourseDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Course;

@WebServlet("/displayCourses")
public class DisplayCoursesServlet extends HttpServlet {

    private final CourseDao courseDAO = new CourseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseDAO.getAllCourses();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Course List</title>");
        // Bootstrap CSS link
        out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
        // Custom CSS for table
        out.println("<link rel='stylesheet' type='text/css' href='tables.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>Course List</h2>");
        out.println("<table class='table'>");
        out.println("<thead class='thead-dark'>");
        out.println("<tr><th>ID</th><th>Course Name</th><th>Course Code</th><th>Semester</th><th>Action</th></tr>");
        out.println("</thead>");
        out.println("<tbody>");
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            // Alternate row colors
            String rowClass = (i % 2 == 0) ? "table-primary" : "table-secondary";
            out.println("<tr class='" + rowClass + "'>");
            out.println("<td>" + course.getId() + "</td>");
            out.println("<td>" + course.getCourse_name()+ "</td>");
            out.println("<td>" + course.getCourse_code()+ "</td>");
            out.println("<td>" + course.getSemester() + "</td>");
            // Apply button style to links
            out.println("<td><a href='editCourse?courseId=" + course.getId() + "' class='btn btn-primary'>Edit</a>"
                            + " <a href='deleteCourse?courseId=" + course.getId() + "' class='btn btn-danger'>Delete</a></td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("<a href='course.html' class='btn btn-success'>Add Course</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
