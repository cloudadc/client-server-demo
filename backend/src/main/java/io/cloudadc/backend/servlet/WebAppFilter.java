package io.cloudadc.backend.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@WebFilter(urlPatterns = {"/*"})
@Component
public class WebAppFilter implements Filter {
	
	Logger log = LoggerFactory.getLogger(WebAppFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Server", "Spring Boot");
        chain.doFilter(request, response);
	}

}
