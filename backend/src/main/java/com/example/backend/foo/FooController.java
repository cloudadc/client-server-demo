	package com.example.backend.foo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = "text/plain;charset=UTF-8")
@Tag(name = "Foo", description = "The Foo Bar API")
public class FooController {
	
	@RequestMapping(path = {"/foo"}, method = {RequestMethod.GET})
	@Operation(summary = "Path Foo API", description = "Path Foo API")
	public String foo (HttpServletRequest request) {
		return buildPlainText(request);
	}

	@RequestMapping(path = {"/bar"}, method = {RequestMethod.GET})
	@Operation(summary = "Path Bar API", description = "Path Bar API")
	public String bar (HttpServletRequest request) {
		return buildPlainText(request);
	}
	
	@RequestMapping(path = {"/"}, method = {RequestMethod.GET})
	@Operation(summary = "Path Root API", description = "Path Root API")
	public String root (HttpServletRequest request) {
		return buildPlainText(request);
	}
	
	
	@RequestMapping(path = {"/info"}, method = {RequestMethod.GET})
	@Operation(summary = "Get app information", description = "Get app information API")
	public String info() {
		return new APPInfo("io.cloudadc", "backend", "0.0.9").toString();
	}
	
	@RequestMapping(path = {"/version"}, method = {RequestMethod.GET})
	@Operation(summary = "Get Version", description = "Get Version API")
	public String version(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(PARENTHESIS_LEFT);
		
		sb.append(QUOTATION).append("datacenter").append(QUOTATION).append(COLON).append(QUOTATION).append(System.getenv("APP_DATACENTER_NAME")).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("serverIP").append(QUOTATION).append(COLON).append(QUOTATION).append(request.getLocalAddr()).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("clientIP").append(QUOTATION).append(COLON).append(QUOTATION).append(request.getRemoteAddr()).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("appVersion").append(QUOTATION).append(COLON).append(QUOTATION).append(System.getenv("APP_VERSION_NUMBER")).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("appStatus").append(QUOTATION).append(COLON).append(QUOTATION).append("running").append(QUOTATION);	
		
		sb.append(PARENTHESIS_RIGHT);
		sb.append(RETURN);
		
		return sb.toString();
	}
	
	@RequestMapping(path = {"/health"}, method = {RequestMethod.GET})
	@Operation(summary = "Health Check", description = "Health Check API")
	public String health() {
		return "ok";
	}
	
	private final static String RETURN = "\n";
	private final static String TAB = "    ";
	private final static String BLANK = " ";
	private final static String COLON = ": ";
	private final static String EMPTY = " ";
	private final static String COMMA = ",";
	private final static String QUOTATION = "\"";
	private final static String PARENTHESIS_LEFT = "{";
	private final static String PARENTHESIS_RIGHT = "}";
	private final static String EQ = "=";
	
	private String buildPlainText(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(RETURN);
		sb.append("F5 Demo App").append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Request URI").append(COLON).append(request.getRequestURI()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Server IP").append(COLON).append(request.getLocalAddr()).append(RETURN);
		sb.append(TAB).append("Server Port").append(COLON).append(request.getLocalPort()).append(RETURN);
		sb.append(TAB).append("Server Hostname").append(COLON).append(request.getLocalName()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Client IP").append(COLON).append(request.getRemoteAddr()).append(RETURN);
		sb.append(TAB).append("Client Port").append(COLON).append(request.getRemotePort()).append(RETURN);
		sb.append(TAB).append("Client Hostname").append(COLON).append(request.getRemoteHost()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Session").append(COLON).append(request.getSession() == null ? "XXXX" : request.getSession().getId()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Cookies").append(COLON).append(buildCookiePlainText(request)).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Protocol").append(COLON).append(request.getProtocol()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Request Headers").append(COLON).append(buildHeadersPlainText(request)).append(RETURN).append(RETURN);
		
		
		String remoteAddr = request.getHeader("X-FORWARDED-FOR");
		sb.append(TAB).append("X-Forwarded-For").append(COLON).append(remoteAddr).append(RETURN).append(RETURN);
		
		return sb.toString();
	}

	

	private Object buildHeadersPlainText(HttpServletRequest request) {
		
		Map<String, List<String>> headersMap = Collections
			    .list(request.getHeaderNames())
			    .stream()
			    .collect(Collectors.toMap(
			        Function.identity(), 
			        h -> Collections.list(request.getHeaders(h))
			    ));
		
		return headersMap.toString();
	}

	private Object buildCookiePlainText(HttpServletRequest request) {
		
		Cookie [] cookies = request.getCookies();
		
		if(cookies == null || cookies.length == 0) {
			return EMPTY;
		}
		
		StringBuffer sb = new StringBuffer();
		boolean isFirst = true;
		
		for (Cookie cookie : cookies) {
			sb.append(cookie.getName()).append(EQ).append(cookie.getValue());
			if(isFirst) {
				isFirst = true;
			} else {
				sb.append(COMMA);
			}
		}
		
		return sb.toString();
	}


}
