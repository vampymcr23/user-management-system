package com.vg.hm.samples.usermanagementsvc.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vg.hm.samples.usermanagementsvc.service.VehicleAPIService;
import com.vg.hm.samples.usermanagementsvc.service.VehicleService;
import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import com.vg.hm.samples.usermanagementsvc.service.model.MakesJson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleAPIService vehicleRetrofitClient;

    @Override
    @HystrixCommand(fallbackMethod = "getDummyMakes")
    public List<Make> getMakes() throws IOException {
       log.info("Getting all makes...");
        Call<MakesJson> call = vehicleRetrofitClient.getAllMakes();
        Response<MakesJson> response = call.execute();
        MakesJson makes  = response.body();
        List<Make> makeList= makes.getResults();
        return makeList;
    }

    public List<Make> getDummyMakes(){
        log.info("Getting dummy makes");
        List<Make> makeList = new ArrayList<>();
        Make Tesla = Make.builder().makeID(1237).makeName("Tesla").build();
        Make Nissan = Make.builder().makeID(1235).makeName("Nissan").build();
        makeList.add(Tesla);
        makeList.add(Nissan);
        return makeList;
    }


}
