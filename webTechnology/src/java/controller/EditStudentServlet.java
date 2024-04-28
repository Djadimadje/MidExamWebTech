package controller;

import dao.StudentDao;
import model.Student;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    private final StudentDao studentDAO = new StudentDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type
        response.setContentType("text/html");
        
        // Retrieve the student ID from the request parameter
        String studentId = request.getParameter("id");

        // Initialize PrintWriter
        PrintWriter out = response.getWriter();

        try {
            // Retrieve the existing student record from the database
            Student student = studentDAO.getStudentById(studentId);
            if (student != null) {
                // Generate HTML form for editing student details
                out.println("<html><head><title>Edit Student</title>");
                out.println("<link rel='stylesheet' type='text/css' href='css/style.css'>");
                out.println("</head><body>");
                out.println("<div class='container'>");
                out.println("<h2>Edit Student Details</h2>");
                out.println("<form action='updateStudent' method='post'>");
                out.println("<input type='hidden' name='studentId' value='" + student.getId() + "'>");
                out.println("<label for='firstName'>First Name:</label>");
                out.println("<input type='text' name='firstName' value='" + student.getFirstName() + "'>");
                out.println("<label for='lastName'>Last Name:</label>");
                out.println("<input type='text' name='lastName' value='" + student.getLastName() + "'>");
                out.println("<label for='dob'>Date of Birth:</label>");
                out.println("<input type='text' name='dob' value='" + student.getDob() + "'>");
                out.println("<button type='submit'>Update</button>");
                out.println("</form>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                // Redirect to an error page if the student record is not found
                response.sendRedirect("error.html");
            }
        } catch (Exception e) {
            // Redirect to an error page if an exception occurs
            response.sendRedirect("error.html");
        } finally {
            // Close PrintWriter
            out.close();
        }
    }
}
