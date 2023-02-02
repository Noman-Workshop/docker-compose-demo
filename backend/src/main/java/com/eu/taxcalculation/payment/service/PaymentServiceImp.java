package com.eu.taxcalculation.payment.service;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.exception.PaymentNotFoundException;
import com.eu.taxcalculation.payment.repository.PaymentRepository;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaymentServiceImp implements PaymentService{

    @Autowired

    private PaymentRepository paymentRepository;

    @Autowired
    public void PaymentServiceImp( PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentByUUID(String id) throws PaymentNotFoundException {

        Payment payment = paymentRepository.findByUuid(id);

        if(payment == null){
            throw new PaymentNotFoundException("Invalid payment id");
        }
        return payment;
    }

    @Override
    public Payment getPaymentByTinNoAndYear(String id, String year) throws PaymentNotFoundException {

        Payment payment = paymentRepository.findByTinNoAndAssessmentYear(id,year);

        if(payment == null){
            throw new PaymentNotFoundException("Invalid payment id");
        }
        return payment;
    }

    @Override
    public List<Payment> getAll(){
        return paymentRepository.findAll();
    }

}
