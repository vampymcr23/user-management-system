package com.vg.hm.samples.usermanagementsvc.service;

import com.vg.hm.samples.usermanagementsvc.service.model.Make;
import com.vg.hm.samples.usermanagementsvc.service.model.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    /**
     * Retrieve User details by account.
     * @param email email.
     * @return User details.
     */
    User getUserDetailsByAccount(String email);

    /**
     * Creates a user.
     * @param user user.
     */
    void createUser(User user);

    /**
     * Update a user.
     * @param user user.
     */
    void updateUser(User user);

    /**
     * Retrieves a list of all users.
     * @return list of users.
     */
    List<User> getAllUsers();

    /**
     * Hystrix example
     * @return message when executing the callback
     * @throws InterruptedException
     */
    public String hello()throws InterruptedException;

    /**
     * Retrofit example
     * @return list of car's makes
     * @throws IOException
     */
    public List<Make>getMakes() throws IOException;
}
