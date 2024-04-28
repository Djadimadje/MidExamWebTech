/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDao;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentDao studentDAO = new StudentDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentId = request.getParameter("id");

        try {
            studentDAO.deleteStudentById(studentId);
            response.sendRedirect("students"); // Redirect to success page after successful deletion
        } catch (Exception e) {
            response.sendRedirect("error.html"); // Redirect to error page if an exception occurs during deletion
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}

