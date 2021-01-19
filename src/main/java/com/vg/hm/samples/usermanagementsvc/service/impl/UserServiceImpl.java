package com.vg.hm.samples.usermanagementsvc.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vg.hm.samples.usermanagementsvc.service.UserService;
import com.vg.hm.samples.usermanagementsvc.service.VehicleAPIService;
import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import com.vg.hm.samples.usermanagementsvc.service.model.MakesJson;
import com.vg.hm.samples.usermanagementsvc.service.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
@EnableHystrix
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private Map<String, User> storage = new ConcurrentHashMap<>();

    @Override
    public User getUserDetailsByAccount(String email) {
        log.debug("Fetching user account: {}", email);
        if (!storage.containsKey(email)) {
            throw new NotFoundException();
        }
        User user = storage.get(email);
        log.info("User found! Details : {}", user);
        return user;
    }

    @Override
    public void createUser(User user) {
        log.info("Adding new user: {}", user);
        if (storage.containsKey(user.getEmail())) {
            log.warn("User already exists! Updating user: {}", user);
            User existingUser = storage.get(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setLastTimeUpdated(existingUser.getLastTimeUpdated());
            storage.replace(user.getEmail(), existingUser);
            return;
        }
        user.setCreationDate(LocalDateTime.now());
        user.setLastTimeUpdated(user.getCreationDate());
        storage.put(user.getEmail(), user);
    }

    @Override
    public void updateUser(User user) {
        log.info("Updating new user: {}", user);
        if (!storage.containsKey(user.getEmail())) {

        }
        User existingUser = storage.get(user.getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setLastTimeUpdated(existingUser.getLastTimeUpdated());
        storage.replace(user.getEmail(), existingUser);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Listing all users!");
        return storage.values().stream().collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
     public String hello()throws InterruptedException{
      Thread.sleep(3000);
       return "Welcome Hystrix";
     }
     public String fallback_hello(){
        return "Request fails. It takes long time to response";
    }

    @Override
    public List<Make>getMakes() throws IOException {
        String BASE_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyy-MM-dd'T'HH:mm:ssz")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        VehicleAPIService vehicleAPIService = retrofit.create(VehicleAPIService.class);
        Call<MakesJson> call = vehicleAPIService.getAllMakes();
        Response<MakesJson> response = call.execute();
        MakesJson makes  = response.body();
        List<Make> makeList= makes.getResults();
        return makeList;
    }

}
