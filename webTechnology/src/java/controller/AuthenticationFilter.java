package controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        // Check if the user is logged in
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        // Get the requested URI
        String requestURI = httpRequest.getRequestURI();

        // If the user is not logged in and tries to access a protected page, redirect to login page
        if (!loggedIn && (requestURI.endsWith("/admin.html") || requestURI.endsWith("/student.html") || requestURI.endsWith("/teacher.html"))) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
            return;
        }

        // Continue to the requested resource
        chain.doFilter(request, response);
    }

    public void destroy() {
        // Cleanup code, if needed
    }
}
