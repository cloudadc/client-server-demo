package com.example.backend.foo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = "text/plain;charset=UTF-8")
@Tag(name = "Proxy", description = "The Proxy Test API")
public class ProxyController {

	@RequestMapping(path = {"/rxyz/rest"}, method = {RequestMethod.GET})
	@Operation(summary = "/rxyz/rest API", description = "/rxyz/rest API")
	public String rest() {
		return "/rxyz/rest";
	}
	
	@RequestMapping(path = {"/rxyz/ws"}, method = {RequestMethod.GET})
	@Operation(summary = "/rxyz/ws API", description = "/rxyz/ws API")
	public String ws() {
		return "/rxyz/ws";
	}
	
	@RequestMapping(path = {"/rxyz/websocket"}, method = {RequestMethod.GET})
	@Operation(summary = "/rxyz/websocket API", description = "/rxyz/websocket API")
	public String websocket() {
		return "/rxyz/websocket";
	}
}
