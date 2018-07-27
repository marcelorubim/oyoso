package br.com.runsol.api;

import br.com.runsol.entity.User;
import br.com.runsol.service.UserService;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("usuarios")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/")
    public List<User> getAll() {

        return userService.getAll();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public User save(User user) {
        return new User("","","");
    }
}