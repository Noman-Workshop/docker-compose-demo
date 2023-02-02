package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImplemented implements UserService{
    private UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public void UserServiceImplemented(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public User saveUser(User user) {
        //String password = user.getPassword();
        this.passwordEncoder = new BCryptPasswordEncoder();
        String pass = this.passwordEncoder.encode(user.getPassword());
        //System.out.println("The pass is "+pass);
        user.setPassword(pass);
        User u = userRepository.save(user);
        return u;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        this.passwordEncoder = new BCryptPasswordEncoder();

        //User user = userRepository.findByUsernameAndPassword(username, pass);
        User user = userRepository.findByUsername(username);

        if(user == null){
            return null;
        }else{
            boolean isPasswordMatches = this.passwordEncoder.matches(password, user.getPassword());
            if(isPasswordMatches)
                return user;
            else
                return null;
        }
    }

    @Override
    public List<User> getAllTaxPayer(){
       return userRepository.findAll();
    }

    @Override
    public User getATaxPayer(String id){
        return userRepository.findByUuid(id);
    }


    @Override
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


}
