package com.quickbite.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	/**
	 * Creates and adds a HTTP cookie to the servlet response
	 * This cookie has a global scope across the root domain, as shown by the "/".
	 * 
	 * @param response
	 * @param name - unique identifier key for the cookie
	 * @param value - string value stored within that cookie
	 * @param maxAge - the maxmium lifespan of the cookie in seconds
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * Scans the client's inbound HTTP request headers for a specific cookie matching 
	 * the designated key name.
	 * 
	 * @param request HttpServletRequest which contains the cookie array sent by the client.
	 * @param name    The targeted cookie key string to search for.
	 * @return Cookie instance if it matches the search; null otherwise
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		
		// iterate through array of cookies and search to return
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	/**
	 * Removes an active cookie from the client's browser local storage
	 * It does this by sending a blank token with expired age configured.
	 * @param response - HttpServletResponse used to send the invalidation instruction.
	 * @param name - the key of the cookie to be deleted.
	 */
	public static void deleteCookie(HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}

