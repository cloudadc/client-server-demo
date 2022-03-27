package io.cloudadc.backend.servlet;

import static io.cloudadc.backend.Utils.buildPlainTextServletHeaders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/webroot/headers")
public class HttpHeadersServlet extends HttpServlet {

	private static final long serialVersionUID = -2614793306506941896L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		String text = buildPlainTextServletHeaders(req);
		out.println(text);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
