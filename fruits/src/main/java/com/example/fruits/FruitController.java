package com.example.fruits;


import com.example.fruits.Fruit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = {"application/json", "application/xml"})
@Tag(name = "fruit", description = "the fruit API")
public class FruitController {
  static List<Fruit> repository = Collections.synchronizedList(new ArrayList<>());
  
  static {
    repository.add(new Fruit(Long.valueOf(1L), "Cherry"));
    repository.add(new Fruit(Long.valueOf(2L), "Apple"));
    repository.add(new Fruit(Long.valueOf(3L), "Banana"));
  }
  
  @RequestMapping(path = {"/api/fruits"}, method = {RequestMethod.GET})
  @Operation(summary = "Get all fruits", description = "Return all fruits in repositories")
  public List<Fruit> getAll() {
    return repository;
  }
  
  @RequestMapping(path = {"/api/fruits/{id}"}, method = {RequestMethod.GET})
  @Operation(summary = "Get fruit by id", description = "Returns fruit for id specified.")
  @ApiResponses({@ApiResponse(responseCode = "404", description = "Fruit not found")})
  public Fruit getFruit(@Parameter(description = "Fruit id", required = true) @PathVariable("id") Long id) {
    for (int i = 0; i < repository.size(); i++) {
      if (((Fruit)repository.get(i)).getId() == id)
        return repository.get(i); 
    } 
    return new Fruit("New");
  }
}