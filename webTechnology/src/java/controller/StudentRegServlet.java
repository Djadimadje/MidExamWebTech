package controller;
import dao.SemesterDao;
import dao.StudentDao;
import dao.StudentRegDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Semester;
import model.Student;
import model.StudentReg;

@WebServlet(name = "StudentRegServlet", urlPatterns = {"/addStudentReg"})
public class StudentRegServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Retrieve form parameters
        String regDate = request.getParameter("regDate");
        String studentId = request.getParameter("studentId"); // Assuming studentId is provided in the form
        int semesterId = Integer.parseInt(request.getParameter("semesterId")); // Assuming semesterId is provided in the form
        
        // Create StudentReg object
        StudentReg studentReg = new StudentReg();
        studentReg.setRegDate(regDate);
        
        // Retrieve Student object based on studentId
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.getStudentById(studentId);
        studentReg.setStudent(student);
        
        // Retrieve Semester object based on semesterId
        SemesterDao semesterDao = new SemesterDao();
        Semester semester = semesterDao.getSemesterById(semesterId);
        studentReg.setSemester(semester);
        
        // Save StudentReg object using StudentRegDao
        StudentRegDao studentRegDao = new StudentRegDao();
        studentRegDao.addStudentReg(studentReg);
        
        // Redirect to a confirmation page
        response.sendRedirect("confirmation.html");
    }
}
