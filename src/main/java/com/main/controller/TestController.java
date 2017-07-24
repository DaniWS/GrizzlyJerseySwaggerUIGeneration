package com.main.controller;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "My Service", description = "APIs to get all the information about My Service")
@Path("/v2")
public class TestController {

	@ApiOperation(value = "Get the respective Entry based on the given input")
	@GET
	@Path("/test/{name}")
	public String test(@PathParam("name") @DefaultValue("Tanuj") String name) {
		return "Hello to check Swagger UI :: " + name;
	}

}
