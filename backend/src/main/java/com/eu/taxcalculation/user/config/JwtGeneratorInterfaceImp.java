package com.eu.taxcalculation.user.config;

import com.eu.taxcalculation.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtGeneratorInterfaceImp implements JwtGeneratorInterface{
    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                //.setExpiration(new Date(System.currentTimeMillis() + (4 * 60 * 60 * 1000) )
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        jwtTokenGen.put("type", "bearer");
        jwtTokenGen.put("id", user.getUuid());
        jwtTokenGen.put("tin", user.getTin());
        jwtTokenGen.put("username", user.getUsername());
        jwtTokenGen.put("role", user.getRoles());
        System.out.println(user.getTin());
        System.out.println(user.getUsername());
        return jwtTokenGen;
    }
}
