package io.cloudadc.backend.foo;

import static io.cloudadc.backend.Utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = "text/plain;charset=UTF-8")
@Tag(name = "Foo", description = "The Foo Bar API")
public class FooController {
	
	Logger log = LoggerFactory.getLogger(FooController.class);
	
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
		return new APPInfo("io.cloudadc", "backend", env("APP_VERSION_NUMBER")).toString();
	}
	
	@RequestMapping(path = {"/auth"}, method = {RequestMethod.GET})
	@Operation(summary = "auth-server", description = "Authentication Server")
	public ResponseEntity<String> auth(HttpServletResponse response) {
		log.info("authing user");
		response.addHeader("X-Forwarded-User", "username=admin");
		return ResponseEntity.ok("success");
	}
	
	@RequestMapping(path = {"/secret"}, method = {RequestMethod.GET})
	@Operation(summary = "auth-server", description = "Authentication Server")
	public ResponseEntity<String> secret(HttpServletRequest request) {
		String xUser = request.getHeader("X-User");
        log.info("current user is {}", xUser);
		return ResponseEntity.ok(xUser);
	}
	
	@RequestMapping(path = {"/version"}, method = {RequestMethod.GET})
	@Operation(summary = "Get Version", description = "Get Version API")
	public String version(HttpServletRequest request) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(PARENTHESIS_LEFT);
		
		sb.append(QUOTATION).append("datacenter").append(QUOTATION).append(COLON).append(QUOTATION).append(env("APP_DATACENTER_NAME")).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("serverIP").append(QUOTATION).append(COLON).append(QUOTATION).append(request.getLocalAddr()).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("clientIP").append(QUOTATION).append(COLON).append(QUOTATION).append(request.getRemoteAddr()).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("appVersion").append(QUOTATION).append(COLON).append(QUOTATION).append(env("APP_VERSION_NUMBER")).append(QUOTATION).append(COMMA).append(BLANK);
		sb.append(QUOTATION).append("appStatus").append(QUOTATION).append(COLON).append(QUOTATION).append("running").append(QUOTATION);	
		
		sb.append(PARENTHESIS_RIGHT);
		sb.append(RETURN);
		
		return sb.toString();
	}
	
	@RequestMapping(path = {"/health"}, method = {RequestMethod.GET})
	@Operation(summary = "Health Check", description = "Health Check API")
	public String health() {
		return env("APP_DATACENTER_NAME") + COLON + env("APP_VERSION_NUMBER") + " RUNNING";
	}
	
	
	

	




}
