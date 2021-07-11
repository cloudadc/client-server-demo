package com.example.backend.specifications;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = {"application/json", "application/xml"})
@Tag(name = "specification", description = "The F5/VE Platform Specifications API")
public class SpecificationController {
	
	@RequestMapping(path = {"/specifications"}, method = {RequestMethod.GET})
	@ResponseBody
	@Operation(summary = "Get all f5 platform specifications", description = "Return all F5 Platform Specifications")  
	public List<Specification> getAll() {
	    return Arrays.asList();
	  }

}
