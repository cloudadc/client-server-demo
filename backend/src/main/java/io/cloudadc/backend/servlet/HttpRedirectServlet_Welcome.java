package io.cloudadc.backend.servlet;

import static io.cloudadc.backend.Utils.RETURN;
import static io.cloudadc.backend.Utils.TAB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/gluebanking/welcomemanage/welcomeset")
public class HttpRedirectServlet_Welcome extends HttpServlet {


	private static final long serialVersionUID = -7683293878184975040L;
	
	private Logger log = LoggerFactory.getLogger(HttpRedirectServlet_Welcome.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("/gluebanking/welcomemanage/welcomeset");
		StringBuffer sb = new StringBuffer();
		sb.append(RETURN);
		sb.append(TAB).append("<h2>Welcome</h2>").append(RETURN);
		sb.append("This is /gluebanking/welcomemanage/welcomeset page");
		
		
		PrintWriter out = resp.getWriter();
		
		out.println(sb.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
