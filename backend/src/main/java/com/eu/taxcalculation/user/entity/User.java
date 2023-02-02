package com.eu.taxcalculation.user.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    private String uuid;

    @NonNull
    @Column(nullable = false, unique=true)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;


    @NonNull
    @Column(nullable = false)
    private String  tin;

    @NonNull
    @Column(nullable = false)
    private String roles;

    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String zone;

    @NonNull
    @Column(nullable = false)
    private String circle;

    private Boolean enabled=true;

    private Date dob;

    public User(String id, String username, String password, String roles, String tin,String email, String zone, String circle, Date dob) {
        this.uuid = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.tin = tin;
        this.email = email;
        this.zone = zone;
        this.circle = circle;
        this.dob = dob;
        this.enabled = true;
    }
}
