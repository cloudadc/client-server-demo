package io.cloudadc.backend.nginx;

import static io.cloudadc.backend.Utils.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Nginx", description = "The Nginx Backend API")
public class NginxBackendController {
	
	private Logger log = LoggerFactory.getLogger(NginxBackendController.class);
	
	@RequestMapping(path = {"/webroot/decision/login"}, method = {RequestMethod.GET})
    @ResponseBody
    public String login(){
		
		log.info("----> " + env("APP_DECISION_BI_RPT"));
        return "<h1>" + env("APP_DECISION_BI_RPT") + " Login Page.. !</h1>";
    }
	
	@RequestMapping(path = {"/rlzy"}, method = {RequestMethod.GET})
	@Operation(summary = "rlzy rest API", description = "rlzy rest API")
	public String rest() {
		return "rxyz rest";
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(path = {"/webroot/content"}, method = {RequestMethod.GET})
    @ResponseBody
    public String content() throws InterruptedException{
		
		Thread.currentThread().sleep(1000 * 10);
		
        return "<h1> Content Page.. !</h1>";
    }

}
