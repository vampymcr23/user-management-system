package com.vg.hm.samples.usermanagementsvc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vg.hm.samples.usermanagementsvc.service.VehicleAPIService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@Configuration
public class VehicleAPIRetrofitClient {
    @Value("${vehicleAPI.baseUrl}")
    private String BASE_URL;

    @Bean(name = "vehicleServiceRetrofitClient")
    public VehicleAPIService getRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyy-MM-dd'T'HH:mm:ssz")
                .setLenient()
                .create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(responseInterceptor);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit.create(VehicleAPIService.class);
    }

    private Interceptor responseInterceptor = new Interceptor() {
    @SneakyThrows
    @Override
    public Response intercept(Chain chain) throws IOException {
        log.info("Intercepting http call");
        Request request = chain.request();
        Response response = chain.proceed(request);

        URI uri = Thread.currentThread().getContextClassLoader().getResource("vehicleCatalog.json").toURI();
        String path = Paths.get(uri).toString();
        String jsonString = readStringFromFile(path);

        log.info("Creating new response body");
        return response.newBuilder().body(ResponseBody.create(response.body().contentType(),jsonString)).build();
    }
    };

    private String readStringFromFile(String path) throws IOException{
        String fileContent = new String(Files.readAllBytes(Paths.get(path)));
        return fileContent;
    }


}
