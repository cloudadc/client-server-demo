package io.cloudadc.backend.servlet;

import static io.cloudadc.backend.Utils.env;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/gluebanking/login.html")
public class HttpRedirectServlet_Login extends HttpServlet {
	
	private Logger log = LoggerFactory.getLogger(HttpRedirectServlet_Login.class);

	private static final long serialVersionUID = 8731800219691995725L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("/gluebanking/login.html");
		String url = "/gluebanking/login_jump.html";
		if(env("APP_REDIRECT_ABSOLUTE_PATH").equals("true")) {
			url = "http://" + req.getHeader("host") + ":" + System.getProperty("server.port", "8080") + url;
		}
		resp.sendRedirect(url);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
