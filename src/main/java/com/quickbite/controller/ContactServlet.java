package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.quickbite.service.FeedbackService;

@WebServlet(asyncSupported = true, urlPatterns = { "/ContactServlet" })
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Always show contact page (form changes based on login)
        request.getRequestDispatcher("/WEB-INF/views/public/contact.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        // Security: Only logged-in users can submit
        if (session == null || session.getAttribute("userId") == null) {
            request.setAttribute("error", "You must be logged in to submit feedback.");
            request.getRequestDispatcher("/WEB-INF/views/public/contact.jsp").forward(request, response);
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        String ratingStr = request.getParameter("rating");
        String message = request.getParameter("message");

        // Validation
        if (ratingStr == null || ratingStr.trim().isEmpty() || 
            message == null || message.trim().isEmpty()) {
            request.setAttribute("error", "Rating and message are required.");
            request.getRequestDispatcher("/WEB-INF/views/public/contact.jsp").forward(request, response);
            return;
        }

        try {
            int rating = Integer.parseInt(ratingStr);
            
            if (rating < 1 || rating > 5) {
                request.setAttribute("error", "Rating must be between 1 and 5.");
                request.getRequestDispatcher("/WEB-INF/views/public/contact.jsp").forward(request, response);
                return;
            }

            FeedbackService service = new FeedbackService();
            service.submitFeedback(userId, rating, message.trim());

            request.setAttribute("success", "Thank you! Your feedback has been submitted successfully.");
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid rating value.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong. Please try again later.");
        }

        // Forward back to contact page
        request.getRequestDispatcher("/WEB-INF/views/public/contact.jsp").forward(request, response);
    }
}