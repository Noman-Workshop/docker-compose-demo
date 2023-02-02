package com.eu.taxcalculation.payment.repository;

import com.eu.taxcalculation.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public Payment findByUuid(String id);

    //public void savePayment(Payment payment);

    public Payment findByTinNoAndAssessmentYear(String tin, String assessmentYear);
}
