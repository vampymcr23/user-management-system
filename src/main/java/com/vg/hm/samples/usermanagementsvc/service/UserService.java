package com.vg.hm.samples.usermanagementsvc.service;

import com.vg.hm.samples.usermanagementsvc.service.model.User;

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
}
