package controller;

import dao.StudentDao;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addStudent")
public class StudentServlet extends HttpServlet {

    private final StudentDao studentDAO = new StudentDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String fname = req.getParameter("fname");
        String lname= req.getParameter("lname");
        String dob=req.getParameter("dob");

        Student student = new Student();
        student.setId(id);
        student.setFirstName(fname);
        student.setLastName(lname);
        student.setDob(dob);

        studentDAO.addStudent(student);
        resp.sendRedirect("students");

        resp.setContentType("text/html");
        resp.getWriter().println("<h2>Student information saved successfully</h2>");
    }
}
