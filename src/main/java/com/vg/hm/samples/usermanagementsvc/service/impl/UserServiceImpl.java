package com.vg.hm.samples.usermanagementsvc.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vg.hm.samples.usermanagementsvc.service.UserService;
import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import com.vg.hm.samples.usermanagementsvc.service.model.MakesJson;
import com.vg.hm.samples.usermanagementsvc.service.model.User;
import com.vg.hm.samples.usermanagementsvc.util.VehicleAPIRetrofitClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private Map<String, User> storage = new ConcurrentHashMap<>();
    @Autowired
    private VehicleAPIRetrofitClient retrofitClient;

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

    @HystrixCommand(fallbackMethod = "fallBack", commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
     public String hello()throws InterruptedException{
      Thread.sleep(3000);
       return "Welcome Hystrix";
     }
     public String fallBack(){
        return "Request fails. It takes long time to response";
    }

    @Override
    public List<Make>getMakes() throws IOException {
        Call<MakesJson> call = retrofitClient.getRetrofit().getAllMakes();
        Response<MakesJson> response = call.execute();
        MakesJson makes  = response.body();
        List<Make> makeList= makes.getResults();
        return makeList;
    }

}
