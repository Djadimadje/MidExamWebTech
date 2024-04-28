package controller;

import dao.LoginDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginDao loginDao = new LoginDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String role = loginDao.authenticateUser(email, password);
        if (role.equals("admin")) {
            response.sendRedirect("adminPage.html");
        } else if (role.equals("student")) {
            response.sendRedirect("studentPage.html");
        } else if (role.equals("teacher")) {
            response.sendRedirect("teacherPage.html");
        } else {
            // Handle invalid login or unknown role
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }

private void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
    Cookie cookie = new Cookie("authenticated", "true");
    cookie.setMaxAge(60); // Cookie expires in 1 minute
    cookie.setPath("/"); // Set cookie path to root
    response.addCookie(cookie);
    response.sendRedirect(page); // Use relative path
}

}
