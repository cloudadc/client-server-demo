package io.cloudadc.backend;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utils {
	
	public final static String RETURN = "<br>";
	public final static String LN = "\n";
	public final static String TAB = "    ";
	public final static String COLON = ": ";
	public final static String EMPTY = " ";
	public final static String COMMA = ",";
	public final static String EQ = "=";
	public final static String BLANK = " ";
	public final static String QUOTATION = "\"";
	public final static String PARENTHESIS_LEFT = "{";
	public final static String PARENTHESIS_RIGHT = "}";
	
	public static String env(String virable) {
		String value = System.getenv(virable);
		if(null == value) {
			value = System.getProperty(virable, "none");
		}
		return value;
	}
	
	public static String buildPlainTextReqHeaders(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append(TAB).append("Request Headers").append(COLON).append(buildRequestHeadersPlainText(request)).append(LN).append(LN);
		return sb.toString();
	}
	
	public static String buildPlainText(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(RETURN);
		sb.append("F5 Demo App").append(LN).append(LN);
		
		sb.append(TAB).append("Request URI").append(COLON).append(request.getRequestURI()).append(buildParameter(request.getParameterMap())).append(LN);
		sb.append(TAB).append("Protocol").append(COLON).append(request.getProtocol()).append(LN).append(LN);
		
		sb.append(TAB).append("Server IP").append(COLON).append(request.getLocalAddr()).append(LN);
		sb.append(TAB).append("Server Port").append(COLON).append(request.getLocalPort()).append(LN);
		sb.append(TAB).append("Server Hostname").append(COLON).append(request.getLocalName()).append(LN).append(LN);
		
		sb.append(TAB).append("Client IP").append(COLON).append(request.getRemoteAddr()).append(LN);
		sb.append(TAB).append("Client Port").append(COLON).append(request.getRemotePort()).append(LN);
		sb.append(TAB).append("Client Hostname").append(COLON).append(request.getRemoteHost()).append(LN).append(LN);
		
		sb.append(TAB).append("Session").append(COLON).append(request.getSession() == null ? "XXXX" : request.getSession().getId()).append(LN).append(LN);
		
		String remoteAddr = request.getHeader("X-FORWARDED-FOR");
		sb.append(TAB).append("X-Forwarded-For").append(COLON).append(remoteAddr).append(LN).append(LN);
		
		sb.append(TAB).append("Cookies").append(COLON).append(buildCookiePlainText(request)).append(LN).append(LN);
				
		sb.append(TAB).append("Request Headers").append(COLON).append(buildRequestHeadersPlainText(request)).append(LN).append(LN);
		
		
		return sb.toString();
	}
    
	
    private static String buildParameter(Map<String, String[]> parameterMap) {
    	
    	if (parameterMap.size() == 0) {
    		return "";
    	}
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("?");
    	    	
    	parameterMap.keySet().forEach(key -> {
    		if(sb.length() > 3) {
    			sb.append("&");
    		}
    		String[] array = parameterMap.get(key);
    		String value = "";
    		for(String s : array) {
    			if(value.length() > 0) {
    				value += "";
    			}
    			value += s;
    		}
    		sb.append(key).append("=").append(value);
    	});
    	
		return sb.toString();
	}

	public static String buildPlainTextServlet(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(RETURN);
		sb.append("<h1>F5 Demo App</h1>").append(RETURN).append(RETURN);
		
		sb.append(buildClientServerHeaders(request));
		
		sb.append(TAB).append("<h2>Cookies</h2>").append(RETURN).append(buildCookiePlainText(request)).append(RETURN).append(RETURN);
		
		
		sb.append(TAB).append("<h2>Request Headers</h2>").append(RETURN).append(buildRequestHeadersPlainText(request)).append(RETURN).append(RETURN);
		
		
		String remoteAddr = request.getHeader("X-FORWARDED-FOR");
		sb.append(TAB).append("X-Forwarded-For").append(COLON).append(remoteAddr).append(RETURN).append(RETURN);
		
		return sb.toString();
	}
    
    public static String buildPlainTextServletHeaders(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(RETURN);
		sb.append("<h1>F5 Demo App</h1>").append(RETURN).append(RETURN);
		
		sb.append(buildClientServerHeaders(request));
		
		
		sb.append(TAB).append("<h2>Request Headers</h2>").append(RETURN).append(buildRequestHeadersPlainText(request)).append(RETURN).append(RETURN);
		
		
		return sb.toString();
	}
    
    
	
	public static String buildRequestHeadersPlainText(HttpServletRequest request) {
		
		Map<String, List<String>> headersMap = Collections
			    .list(request.getHeaderNames())
			    .stream()
			    .collect(Collectors.toMap(
			        Function.identity(), 
			        h -> Collections.list(request.getHeaders(h))
			    ));
		
		StringBuffer sb = new StringBuffer();
		
		for (Map.Entry<String, List<String>> e : headersMap.entrySet()) {
			sb.append(e.getKey()).append(COLON).append(e.getValue()).append(EMPTY);
		}
		
		return sb.toString();
	}

    
    private static Object buildClientServerHeaders(HttpServletRequest request) {
    	
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append(TAB).append("Request URI").append(COLON).append(request.getRequestURI()).append(RETURN);
		sb.append(TAB).append("Protocol").append(COLON).append(request.getProtocol()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Server IP").append(COLON).append(request.getLocalAddr()).append(RETURN);
		sb.append(TAB).append("Server Port").append(COLON).append(request.getLocalPort()).append(RETURN);
		sb.append(TAB).append("Server Hostname").append(COLON).append(request.getLocalName()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Client IP").append(COLON).append(request.getRemoteAddr()).append(RETURN);
		sb.append(TAB).append("Client Port").append(COLON).append(request.getRemotePort()).append(RETURN);
		sb.append(TAB).append("Client Hostname").append(COLON).append(request.getRemoteHost()).append(RETURN).append(RETURN);
		
		sb.append(TAB).append("Session").append(COLON).append(request.getSession() == null ? "XXXX" : request.getSession().getId()).append(RETURN).append(RETURN);
		
		return sb.toString();
	}

	
	public static String buildCookiePlainText(HttpServletRequest request) {
		
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
