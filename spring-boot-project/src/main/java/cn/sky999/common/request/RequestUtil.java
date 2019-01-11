package cn.sky999.common.request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestUtil {
	public static Cookie getCookieIfExist(Cookie[] cookies, String name) {
		if (cookies == null) {
			Cookie newCookie = new Cookie(name, "");
			return newCookie;
		}
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName()))
				return cookie;
		}
		return null;
	}

	public static Cookie getCookie(Cookie[] cookies, String name) {
		if (cookies == null) {
			Cookie newCookie = new Cookie(name, "");
			return newCookie;
		}
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName()))
				return cookie;
		}
		Cookie newCookie = new Cookie(name, "");
		return newCookie;
	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String ckValue, int hours) {
		Cookie ck = getCookie(request.getCookies(), name);
		ck.setValue(ckValue);
		setCookieAge(ck, hours);
		response.addCookie(ck);
	}

	public static void setCookieAge(Cookie cookie, int hours) {
		cookie.setMaxAge(3600 * hours);
	}

	public static String getCookieValueByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = getCookie(cookies, name);
		return cookie.getValue();
	}

	public static HttpSession getHttpSession(HttpServletRequest request) {
		return request.getSession();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSessionObject(HttpServletRequest request, String attributeName) {
		return (T) getHttpSession(request).getAttribute(attributeName);
	}

	public static void setSessionObject(HttpServletRequest request, String attributeName, Object obj) {
		getHttpSession(request).setAttribute(attributeName, obj);
	}

	public static void delCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String path) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookieName.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					cookie.setPath(path);
					response.addCookie(cookie);
				}
			}
		}
	}
}
