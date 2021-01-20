package com.vg.hm.samples.usermanagementsvc.rest.api.v1;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vg.hm.samples.usermanagementsvc.rest.model.UserResource;
import com.vg.hm.samples.usermanagementsvc.rest.validator.APIValidator;
import com.vg.hm.samples.usermanagementsvc.service.UserService;
import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import com.vg.hm.samples.usermanagementsvc.service.model.User;
import com.vg.hm.samples.usermanagementsvc.util.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserV1RestService {

    @Autowired
    private APIValidator createUserV1Validator;

    @Autowired
    private APIValidator updateUserV1Validator;

    @Autowired
    private UserService userService;

    /**
     * Returns list of users.
     *
     * @return
     */
    @GET
    public Response getUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResource> list = new ArrayList<>(users.size());
        users.stream().forEach(user -> {
            list.add(UserResource.builder()
                    .userEmail(user.getEmail())
                    .creationDate(user.getCreationDate())
                    .lastUpdateDate(user.getLastTimeUpdated())
                    .userFirstName(user.getFirstName())
                    .userLastName(user.getLastName())
                    .build());
        });

        return Response.ok(list).build();
    }

    /**
     * Add user to the storage.
     * @param userResource user resource.
     * @return successful response if added.
     */
    @POST
    public Response addUser(UserResource userResource) {

        createUserV1Validator.validate(userResource);

        User user = User.builder()
                .firstName(userResource.getUserFirstName())
                .lastName(userResource.getUserLastName())
                .email(userResource.getUserEmail())
                .build();

        userService.createUser(user);
        return Response.ok(ApplicationConstants.DEFAULT_RESPONSE).build();
    }

    /**
     * Update user to the storage.
     * @param userResource user resource.
     * @return successful response if added.
     */
    @PUT
    public Response updateUser(UserResource userResource) {

        updateUserV1Validator.validate(userResource);

        User user = User.builder()
                .firstName(userResource.getUserFirstName())
                .lastName(userResource.getUserLastName())
                .email(userResource.getUserEmail())
                .build();

        userService.updateUser(user);
        return Response.ok(ApplicationConstants.DEFAULT_RESPONSE).build();
    }

    @GET
    @Path("/{userEmail}")
    public Response getUserByAccount(@PathParam("userEmail") String userEmail) {

        User user = userService.getUserDetailsByAccount(userEmail);
        UserResource userResource = UserResource.builder()
                .userLastName(user.getLastName())
                .userFirstName(user.getFirstName())
                .userEmail(user.getEmail())
                .creationDate(user.getCreationDate())
                .lastUpdateDate(user.getLastTimeUpdated())
                .build();

        return Response.ok(userResource).build();
    }

    @GET
    @Path("/example")
    public Response helloHystrix() throws InterruptedException {
        String callback = userService.hello();
        return Response.ok(callback).build();
    }

    @GET
    @Path("/makes")
    public Response retrofitExample() throws IOException {
        List<Make> makes = userService.getMakes();
        return Response.ok(makes).build();
    }
}
