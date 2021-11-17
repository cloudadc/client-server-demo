package com.example.backend.foo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	
	
	@RequestMapping("/webroot/decision/login")
    @ResponseBody
    public String login(){
		System.out.println("----> BI REST");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return "<h1>BI Login Page.. !</h1>";
    }
}
