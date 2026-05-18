package com.quickbite.utils;

import com.quickbite.model.UserModel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	/**
	 * Binds the logged-in user's identity (UserModel object) to the current HTTP session.
	 * 
	 * If an active session does not exist, this method instantiates a new one, and then
	 * maps the user model object to a specific tracking identifier in the server's memory.
	 * @param request
	 * @param key - unique string identifier used to map the session attribute.
	 * @param user - UserModel being stored in the session context.
	 */
	public static void setAttribute(HttpServletRequest request, String key, UserModel user) {
		HttpSession session = request.getSession(); //default true
		session.setAttribute(key, user);
	}
	
	/**
	 * Retrieves the stored session attributes (like active user profile) to verify identity permissions.
	 * 
	 * It calls the getSession(false) method in request to look up an existing session without instantiating a new one. 
	 * If an active session is found, it extracts the attributes associated with the specified key, otherwise returns null. 
	 *
	 * @param request
	 * @param key
	 * @return Object instance mapped to key if valid session; null otherwise.
	 */
	public static Object getAttribute(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(key);
		}
		return null;
	}
	
	/**
	 * Removes specific attribute being mapped from the user's active session.
	 * 
	 * It removes a single cached data from the user’s active session state without destroying the 
	 * session itself. It does this by unbinding the object mapped to the specified key to remove it 
	 * from the session storage.
	 * 
	 * @param request
	 * @param key
	 */
	public static void removeAttribute(HttpServletRequest request, String key) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(key);
		}
	}
	
	/**
	 * It handles the operations of a secure Logout feature by completely wiping the user's session
	 * from the server.
	 * 
	 * It firstly locates the active session profile and triggers invalidate method, which destroys 
	 * the entire session record. 
	 * 
	 * @param request
	 */
	public static void invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
}

