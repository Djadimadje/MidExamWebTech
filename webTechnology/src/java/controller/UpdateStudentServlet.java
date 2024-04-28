package controller;

import dao.StudentDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Student;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentDao studentDAO = new StudentDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve parameters from the request
        String studentId = request.getParameter("studentId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");

        try {
            // Retrieve the student record from the database
            Student student = studentDAO.getStudentById(studentId);

            // Set new values for the student attributes
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDob(dob);

            // Update the student record in the database
            studentDAO.updateStudent(student);

            // Redirect to a confirmation page or back to the edit page
            response.sendRedirect("students");
        } catch (Exception e) {
            // Redirect to an error page if an exception occurs
            response.sendRedirect("editStudents");
        }
    }
}
