package com.vg.hm.samples.usermanagementsvc.rest.api.v1;

import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api/v1/auto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@EnableHystrix
public class AutoRestService {
    @GET
    @Path("/makes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMakes(){
        List<Make> makeList = new ArrayList<>();
        Make Tesla = Make.builder().makeID(1237).makeName("Tesla").build();
        Make Nissan = Make.builder().makeID(1235).makeName("Nissan").build();
        makeList.add(Tesla);
        makeList.add(Nissan);
        return Response.ok(makeList).build();
    }
}
