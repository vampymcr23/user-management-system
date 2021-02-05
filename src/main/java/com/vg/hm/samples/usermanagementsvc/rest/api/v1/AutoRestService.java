package com.vg.hm.samples.usermanagementsvc.rest.api.v1;

import com.vg.hm.samples.usermanagementsvc.service.VehicleService;
import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
@Path("/api/v1/auto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutoRestService {

    @Autowired
    private VehicleService vehicleService;
    @GET
    @Path("/makes/example")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMakesExample() throws IOException {
        log.info("Makes example...");
        List<Make> makeList =vehicleService.getDummyMakes();
        return Response.ok(makeList).build();
    }
    @GET
    @Path("/makes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMakes() throws IOException {
        List<Make> makeList =vehicleService.getMakes();
        return Response.ok(makeList).build();
    }
}
