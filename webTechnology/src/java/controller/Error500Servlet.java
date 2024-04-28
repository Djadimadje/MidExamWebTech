package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/error500")
public class Error500Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Write error message HTML
        response.getWriter().println("<h1>500 Internal Server Error</h1>");
        response.getWriter().println("<p>We apologize for the inconvenience. Please try again later.</p>");
    }
}