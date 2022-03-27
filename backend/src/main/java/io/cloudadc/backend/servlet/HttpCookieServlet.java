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

@WebServlet(urlPatterns = "/webroot/cookies")
public class HttpCookieServlet extends HttpServlet {

	private static final long serialVersionUID = 2712871415862760601L;
	
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
		String text = buildPlainTextServlet(req);
		out.println(text);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
