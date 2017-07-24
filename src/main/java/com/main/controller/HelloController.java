
package com.main.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Api(value = "My Hello Service", description = "APIs to get all the information about My Hello Service")
@Path("/v1")
public class HelloController {

	@ApiOperation(value = "Get the respective Entry based on the given input")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hello")
	public String getHello() {
		return "Hello from Tanuj Gupta";
	}

}