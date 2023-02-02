package com.eu.taxcalculation.user.config;

import com.eu.taxcalculation.user.filter.JwtFilter;
import com.eu.taxcalculation.user.service.UserServiceImplemented;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
@Configuration
public class FilterConfig {

    @Autowired
    UserServiceImplemented userService;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter(userService));

        //provide endpoints which needs to be restricted.
        //All Endpoints would be restricted if unspecified
        filter.addUrlPatterns("/api/v1/blog/restricted");
        //filter.addUrlPatterns("/api/v1/payment/all");
        return filter;
    }


}
