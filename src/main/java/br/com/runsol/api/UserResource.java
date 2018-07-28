package br.com.runsol.api;

import br.com.runsol.entity.User;
import br.com.runsol.service.UserService;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
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
    public User save(JsonObject user) {
               try{
            return userService.addUser(new User("","",""));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}