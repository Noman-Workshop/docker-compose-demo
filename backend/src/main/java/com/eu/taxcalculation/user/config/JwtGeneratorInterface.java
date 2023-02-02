package com.eu.taxcalculation.user.config;

import com.eu.taxcalculation.user.entity.User;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);
}