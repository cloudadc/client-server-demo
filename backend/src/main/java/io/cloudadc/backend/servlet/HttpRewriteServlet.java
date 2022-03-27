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


@WebServlet(urlPatterns = "/webroot/decision")
public class HttpRewriteServlet extends HttpServlet {

	private static final long serialVersionUID = -4397852735335564960L;
	
	private Logger log = LoggerFactory.getLogger(HttpRewriteServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("----> " + env("APP_DECISION_BI_RPT"));
		resp.sendRedirect("/webroot/decision/login");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
