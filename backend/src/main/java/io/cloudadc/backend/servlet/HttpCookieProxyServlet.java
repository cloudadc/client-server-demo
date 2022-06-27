package io.cloudadc.backend.servlet;

import static io.cloudadc.backend.Utils.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/webroot/proxy/cookies")
public class HttpCookieProxyServlet extends HttpServlet {
	
	Logger log = LoggerFactory.getLogger(HttpCookieProxyServlet.class);

	private static final long serialVersionUID = 8053022480390418241L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				log.info("cookie: " + cookie.getName() + " = " + cookie.getValue());
				resp.addCookie(cookie);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(RETURN);
		sb.append(TAB).append("<h2>Cookies</h2>").append(RETURN);
		sb.append(buildCookiePlainText(req));
		
		
		PrintWriter out = resp.getWriter();
		
		out.println(sb.toString());
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	


}
