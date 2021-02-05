package com.vg.hm.samples.usermanagementsvc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vg.hm.samples.usermanagementsvc.service.VehicleAPIService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class VehicleAPIRetrofitClient {
    @Value("${vehicleAPI.baseUrl}")
    private String BASE_URL;

    @Bean(name = "vehicleServiceRetrofitClient")
    public VehicleAPIService getRetrofit() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyy-MM-dd'T'HH:mm:ssz")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        VehicleAPIService client = retrofit.create(VehicleAPIService.class);
        return client;
    }


}
