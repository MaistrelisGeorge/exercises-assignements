package com.example.lostandfound.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * gmaistrelis - Lost and Found - Filter to protect secured routes requiring authentication
 */
@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {

    /**
     * Filter requests to check if user is logged in
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("loggedinuser") == null) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Define which URLs should not be filtered (public access)
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        // Public paths that don't require authentication
        return path.equals("/") ||
               path.equals("/login") ||
               path.equals("/dologin") ||
               path.equals("/register") ||
               path.equals("/doregister") ||
               path.startsWith("/css/") ||
               path.startsWith("/js/") ||
               path.startsWith("/img/") ||
               path.equals("/items/public") ||
               path.equals("/items/search");
    }
}
