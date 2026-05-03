package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.quickbite.dao.FeedbackDAO;
import com.quickbite.model.FeedbackModel;

/**
 * Servlet implementation class AdminFeedbackView
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/feedback" })
public class AdminFeedbackView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminFeedbackView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FeedbackDAO feedbackDao = new FeedbackDAO();
			List<FeedbackModel> feedbackList = feedbackDao.getAllFeedbacks();
			
			// Calculate total, average rating, and rating distribution count
            int totalFeedback = feedbackList.size();
            double avgRating = 0.0;
            int[] ratingCount = new int[6];
            
            if (totalFeedback > 0) {
                int sum = 0;
                for (FeedbackModel fb : feedbackList) {
                    sum += fb.getRatingValue();
                    if (fb.getRatingValue() >= 1 && fb.getRatingValue() <= 5) {
                        ratingCount[fb.getRatingValue()]++;
                    }
                }
                avgRating = (double) sum / totalFeedback;
            }
            
            // Round to 1 decimal place
            avgRating = Math.round(avgRating * 10.0) / 10.0;

            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("totalFeedback", totalFeedback);
            request.setAttribute("avgRating", avgRating);
            request.setAttribute("ratingCount", ratingCount);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load feedbacks.");
		}
		request.getRequestDispatcher("/WEB-INF/views/admin/admin-feedback.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
