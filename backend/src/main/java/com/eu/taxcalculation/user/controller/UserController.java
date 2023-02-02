package com.eu.taxcalculation.user.controller;

import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.entity.Activation;
import com.eu.taxcalculation.user.entity.EmailDetails;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.service.ActivationService;
import com.eu.taxcalculation.user.service.EmailService;
import com.eu.taxcalculation.user.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private JwtGeneratorInterface jwtGenerator;

    @Autowired
    private ActivationService activationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, ActivationService activationService, JwtGeneratorInterface jwtGenerator){
        this.userService=userService;
        this.jwtGenerator=jwtGenerator;
        this.activationService = activationService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            User user1 = userService.saveUser(user);
            Activation activation = activationService.saveActivation(user1);
            sendRegistrationEmail(user1,activation);
            return new ResponseEntity<>(user1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new UserNotFoundException("Username or Password is Empty");
            }
            User userData = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            //System.out.println(userData.equals(null));
            if(userData==null){
                return new ResponseEntity<>("Username or Password is Invalid", HttpStatus.CONFLICT);
            }else{

                    Activation activation = activationService.findByUuid(userData);
                    if(activation==null){
                        return new ResponseEntity<>("Activation user not found", HttpStatus.CONFLICT);
                    }else{
                        String status = activation.getStatus();

                        if(status.equals("active")){
                            System.out.println(status+"1");
                            return new ResponseEntity<>(jwtGenerator.generateToken(userData), HttpStatus.OK);
                        }else{
                            System.out.println(status+"2");

                            return new ResponseEntity<>("Activate your Account", HttpStatus.CONFLICT);
                        }

                    }
                 /*   String status = activation.getStatus();
                    System.out.println("0"+activation.getStatus()+ "0"+"active0");
                    System.out.println("0"+activation.getStatus()+ "0");
                    System.out.println(status.getClass().getName());
                    System.out.println("status".getClass().getName());*/


            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<?> GetAllUsers() {
        return new ResponseEntity<>(userService.getAllTaxPayer(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/activation/{code}")
    public ResponseEntity<?> activateUser(@PathVariable String code){
        try{
            Activation activation = activationService.activateUser(code);
            //activationService.saveActivation(user1);
            return new ResponseEntity<>(activation, HttpStatus.CREATED);
        } catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public void sendRegistrationEmail (User user, Activation activation){
        try{
            String body = "Congratulations! Your registration is successful. Please paste the following link on the browser.\n"+
                    "localhost:4200/activation/"+activation.getActivationCode();
            EmailDetails emailDetails = new EmailDetails(user.getEmail(),body,"Welcome to Tax Service","");
            String status
                    = emailService.sendSimpleMail(emailDetails);
            System.out.println(status);
        }catch(Exception e){
            System.out.println("Exception occurred"+e);
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> GetAUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getATaxPayer(id), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
