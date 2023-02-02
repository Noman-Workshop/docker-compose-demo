package com.eu.taxcalculation.user.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;


@Entity
@NoArgsConstructor
@Table(name = "activations")
public class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@OneToOne(fetch= FetchType.EAGER)
    String uuid;

    @NonNull
    @Column(nullable = false)
    String status;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String activationCode;

    @Column(name = "created_at")
    @CreationTimestamp()
    private Date created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updated_at;



    public Activation(String uuid, String status){
        this.uuid = uuid;
        this.status = status;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getUuid() {
        return uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
    @PrePersist
    public void autofill() {
        this.setActivationCode(UUID.randomUUID().toString());
    }
}
