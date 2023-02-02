package com.eu.taxcalculation.payment.service;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.exception.PaymentNotFoundException;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface PaymentService {
    public void savePayment(Payment payment);

    public Payment getPaymentByUUID(String id) throws PaymentNotFoundException;

    public Payment getPaymentByTinNoAndYear(String id, String year) throws PaymentNotFoundException;

    public List<Payment> getAll();

}
