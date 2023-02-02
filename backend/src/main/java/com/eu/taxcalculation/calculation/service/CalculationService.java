package com.eu.taxcalculation.calculation.service;

import com.eu.taxcalculation.calculation.entity.Calculation;
import com.eu.taxcalculation.calculation.exception.CalculationNotFoundException;
import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.exception.PaymentNotFoundException;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface CalculationService {
    //public void save(Payment payment);

    public Calculation getCalculationByUUID(String id) throws CalculationNotFoundException;

    public Calculation getCalculationByTin(String id) throws CalculationNotFoundException;

    public List<Calculation> getAllCalculation();

    public Calculation updateCalculationByTinNo(String id, Calculation cal) throws CalculationNotFoundException;

    public Calculation saveCalculation(Calculation calculation) throws CalculationNotFoundException;

    public Calculation getCalculationByTinNAssessmentYear(String tin, String assmntYear) throws CalculationNotFoundException;

    public Calculation getCalculationByTinNAssessmentYearNSubmitted(String tin, String assmntYear);


}
