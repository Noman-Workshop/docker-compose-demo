package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.Activation;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.repository.ActivationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.SchemaOutputResolver;

@Service
public class ActivationService {
    ActivationRepository activationRepository;
    @Autowired
    public ActivationService(ActivationRepository activationRepository){
        this.activationRepository= activationRepository;
    }

    public Activation saveActivation(User user) {
        //String password = user.getPassword();
        Activation activation = new Activation(user.getUuid(),"inactive");
        Activation activation1 = activationRepository.save(activation);
        return activation1;
    }

    public Activation saveActivationBootstrap(User user) {
        //String password = user.getPassword();
        Activation activation = new Activation(user.getUuid(),"active");
        Activation activation1 = activationRepository.save(activation);
        return activation1;
    }


    public Activation activateUser(String uuid) throws Exception {
        Activation activation = activationRepository.findByActivationCode(uuid);
        if(activation==null)
            throw new Exception("code_not_found");
        else{
            activation.setStatus("active");
            activationRepository.save(activation);
            return activation;

        }

    }

    public Activation findByUuid(User user)  {
        //String password = user.getPassword();
        System.out.println(user.getUuid());
        Activation activation = activationRepository.findByUuid(user.getUuid());
        return activation;
    }
}
