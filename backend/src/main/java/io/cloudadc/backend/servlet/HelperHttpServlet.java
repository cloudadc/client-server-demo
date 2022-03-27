package io.cloudadc.backend.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

@WebServlet(urlPatterns = "/help")
public class HelperHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 8177947317003916595L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Resource resource = new ClassPathResource("classpath:help.txt");
		InputStream inputStream = resource.getInputStream();
		byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		String data = new String(bdata, StandardCharsets.UTF_8);
				
		PrintWriter out = resp.getWriter();
		out.println(data);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
