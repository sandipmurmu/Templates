package com.silicus.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silicus.exception.ApiException;
import com.silicus.model.User;
import com.silicus.service.UserService;

@Component
@Path("/user")
@Api(value = "/user")
@Produces(value={"application/json", "application/xml"})
public class UserWebService {

	@Autowired
	private UserService userservice;

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Get user details by user id", response=User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Invalid user id supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	public Response getUser(@ApiParam(value = "user id for the desired user") @PathParam("id") String id)
			throws ApiException {
		User user = null;
		try {
			user = userservice.findUser(id);
			if (null == user) {
				throw new com.silicus.exception.NotFoundException(404, "User not found");
			}
		} catch (Exception e) {
			throw new ApiException(400, "Invalid user id supplied");
		}
		return Response.ok().entity(user).build();
	}

}
