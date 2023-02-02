package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User saveUser(User user);

    public User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException;
    public List<User> getAllTaxPayer();

    public User getATaxPayer(String id);

    public User getUserByUsername(String username);


}
