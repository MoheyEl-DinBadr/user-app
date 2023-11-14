package com.example.api;

import com.example.dto.UserDTO;
import com.example.dto.UserGetDto;
import com.example.dto.UserPostDto;
import com.example.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/user")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(RestResponse.StatusCode.OK)
    public Uni<UserGetDto> findById(@PathParam("id") String id) {
        return userService.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    public Uni<UserDTO> addUser(UserPostDto postDto) {
        return userService.saveUser(postDto);
    }
}
