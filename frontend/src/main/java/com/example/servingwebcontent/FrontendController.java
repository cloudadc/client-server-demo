package com.example.servingwebcontent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class FrontendController {
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@RequestMapping(path = {"/ui"}, method = {RequestMethod.GET})
	public String getAll() {
		
		String host = env("BACKEND_HOST");
				
		String port = env("BACKEND_PORT");
		
		//System.out.println(host + " " + port );
		
		List<Object> fruits = restTemplate.getForObject("http://" + host + ":" + port + "/api/fruits", ArrayList.class);
		
		StringBuffer sb = new StringBuffer();
		fruits.forEach(f -> {
			sb.append(f).append("\n");
		});
		
		return sb.toString();
	}
	
	private String env(String key) {
		
		String value = System.getProperty(key);
		
		if(null != value) {
			return value;
		}
		
		value = System.getenv().get(key);
		
		if(null != value) {
			return value;
		}
		
		throw new RuntimeException("Can not get the value of " + key);
	}

	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder.build();
    }

}
