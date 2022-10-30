package io.cloudadc.backend.mgw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = {"text/plain", "application/*"})
@Tag(name = "Mgw", description = "The Mgw API")
public class MgwController {
	
	Logger log = LoggerFactory.getLogger(MgwController.class);
	
	@RequestMapping(path = {"/mgs.mgw.hml"}, method = {RequestMethod.POST}, produces = {"text/plain", "application/*"})
	@Operation(summary = "Add Mgw", description = "add Mgw")
	public void addMgw(@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Mgw mgw, HttpServletRequest request, HttpServletResponse response) {
		String traceid = request.getHeader("traceid");
		if(traceid != null && traceid.length() > 0) {
			response.addHeader("traceid", traceid);
		}
		log.info(traceid);
	}

}
