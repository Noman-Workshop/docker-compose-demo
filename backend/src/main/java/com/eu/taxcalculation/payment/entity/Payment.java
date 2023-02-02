package com.eu.taxcalculation.payment.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "payments")
@NoArgsConstructor

public class Payment {
    @Id   //BINARY(16)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier  default newid()")
    private String uuid;


   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid" , insertable = false,updatable = false)
    private User userid;*/

    @NonNull
    @Column(nullable = false)
    private String tinNo;

    @NonNull
    @Column(nullable = false)
    private double amount;

    @NonNull
    @Column(nullable = false)
    private String assessmentYear;

    @NonNull
    @Column(nullable = false)
    private String getway;

    @NonNull
    @Column(nullable = false)
    private String mobile;

   /* @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)*/
    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;



    public Payment(String uuid, String tin, String assessmentYear, String getway, String mobile, double amount) {
        this.uuid = uuid;
        this.tinNo = tin;
        this.assessmentYear = assessmentYear;
        this.getway = getway;
        this.mobile = mobile;
        this.amount = amount;
    }
}
