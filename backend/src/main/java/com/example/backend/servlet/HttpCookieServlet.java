package com.example.backend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/webroot/cookies", loadOnStartup = 1)
public class HttpCookieServlet extends HttpServlet {

	private static final long serialVersionUID = 2712871415862760601L;
	
	private final static String RETURN = "<br>";
	private final static String TAB = "    ";
	private final static String COLON = ": ";
	private final static String EMPTY = " ";
	private final static String COMMA = ",";
	private final static String EQ = "=";

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie rememberMe = new Cookie("rememberMe", "deleteMe");
		rememberMe.setPath("/");
		rememberMe.setMaxAge(0);
		resp.addCookie(rememberMe);
		
		Cookie session = new Cookie("JSESSIONID", req.getSession() == null ? "XXXX" : req.getSession().getId());
		session.setPath("/");
		session.setDomain("mp.io");
		resp.addCookie(session);
		
		Cookie mstep = new Cookie("SESSION_MSTEP_COOKIE", "8148814898125824000");
		mstep.setPath("/");
		mstep.setDomain("www.mpoin.cebbank.com");
		mstep.setHttpOnly(false);
		mstep.setSecure(true);
		resp.addCookie(mstep);
		
		Cookie wt_user = new Cookie("WT_USER_ID", "5622-2456734867");
		wt_user.setPath("/");
		resp.addCookie(wt_user);
		
		Cookie s = new Cookie("S", "3ED3E4C8");
		s.setPath("/");
		resp.addCookie(s);
		
		PrintWriter out = resp.getWriter();
		String text = buildPlainText(req);
		System.out.println(text);
		out.println(text);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private String buildPlainText(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(RETURN);
		sb.append("<h1>F5 Demo App</h1>").append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Request URI").append(COLON).append(request.getRequestURI()).append(RETURN);
		sb.append(TAB).append("Protocol").append(COLON).append(request.getProtocol()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Server IP").append(COLON).append(request.getLocalAddr()).append(RETURN);
		sb.append(TAB).append("Server Port").append(COLON).append(request.getLocalPort()).append(RETURN);
		sb.append(TAB).append("Server Hostname").append(COLON).append(request.getLocalName()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Client IP").append(COLON).append(request.getRemoteAddr()).append(RETURN);
		sb.append(TAB).append("Client Port").append(COLON).append(request.getRemotePort()).append(RETURN);
		sb.append(TAB).append("Client Hostname").append(COLON).append(request.getRemoteHost()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Session").append(COLON).append(request.getSession() == null ? "XXXX" : request.getSession().getId()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("<h2>Cookies</h2>").append(RETURN).append(buildCookiePlainText(request)).append(RETURN).append(RETURN);
		
		
		sb.append(TAB).append("<h2>Request Headers</h2>").append(RETURN).append(buildHeadersPlainText(request)).append(RETURN).append(RETURN);
		
		
		String remoteAddr = request.getHeader("X-FORWARDED-FOR");
		sb.append(TAB).append("X-Forwarded-For").append(COLON).append(remoteAddr).append(RETURN).append(RETURN);
		
		return sb.toString();
	}
	
	private String buildHeadersPlainText(HttpServletRequest request) {
		
		Map<String, List<String>> headersMap = Collections
			    .list(request.getHeaderNames())
			    .stream()
			    .collect(Collectors.toMap(
			        Function.identity(), 
			        h -> Collections.list(request.getHeaders(h))
			    ));
		
		StringBuffer sb = new StringBuffer();
		
		for (Map.Entry<String, List<String>> e : headersMap.entrySet()) {
			sb.append(e.getKey()).append(COLON).append(e.getValue()).append(RETURN);
		}
		
		return sb.toString();
	}

	private Object buildCookiePlainText(HttpServletRequest request) {
		
		Cookie [] cookies = request.getCookies();
		
		if(cookies == null || cookies.length == 0) {
			return EMPTY;
		}
		
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		
		for (Cookie cookie : cookies) {
			sb.append(cookie.getName()).append(EQ).append(cookie.getValue()).append(RETURN);
			if(isFirst) {
				isFirst = true;
			} else {
				sb.append(COMMA);
			}
		}
		
		return sb.toString();
	}
}
