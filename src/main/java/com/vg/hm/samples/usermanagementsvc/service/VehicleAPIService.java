package com.vg.hm.samples.usermanagementsvc.service;

import com.vg.hm.samples.usermanagementsvc.service.model.MakesJson;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.GET;

import java.io.IOException;

@Service
public interface VehicleAPIService {
    @GET("getallmakes?format=json")
    Call<MakesJson> getAllMakes() throws IOException;
}
