package io.cloudadc.backend.nginx;

import static io.cloudadc.backend.Utils.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "RewriteController", description = "The RewriteController API")
public class RewriteController {
	
	private Logger log = LoggerFactory.getLogger(RewriteController.class);
	
	@RequestMapping(path = {"/webroot/decision/login"}, method = {RequestMethod.GET})
    @ResponseBody
    public String login(){
		
		log.info("----> " + env("APP_DECISION_BI_RPT"));
        return "<h1>" + env("APP_DECISION_BI_RPT") + " Login Page.. !</h1>";
    }

}
