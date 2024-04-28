package controller;
import dao.SemesterDao;
import model.Semester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SemesterServlet", urlPatterns = {"/semesters"})
public class SemesterServlet extends HttpServlet {

    private SemesterDao semesterDao;

    @Override
    public void init() throws ServletException {
        super.init();
        semesterDao = new SemesterDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Add new Semester
        String semesterName = request.getParameter("semesterName");
        Semester semester = new Semester();
        semester.setSemesterName(semesterName);
        semesterDao.addSemester(semester);

        response.sendRedirect(request.getContextPath() + "/semesters");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete Semester
        String semesterId = request.getParameter("semesterId");
        try {
            semesterDao.deleteSemesterById(semesterId);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(); // Log the exception for debugging
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update Semester
        String semesterId = request.getParameter("semesterId");
        String semesterName = request.getParameter("semesterName");
        Semester semester = new Semester();
        semester.setSemesterId(semesterId);
        semester.setSemesterName(semesterName);
        try {
            semesterDao.updateSemester(semester);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace(); // Log the exception for debugging
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all Semesters
        List<Semester> semesters = semesterDao.getAllSemesters();
        request.setAttribute("semesters", semesters);
        request.getRequestDispatcher("/semesters.jsp").forward(request, response);
    }
}
