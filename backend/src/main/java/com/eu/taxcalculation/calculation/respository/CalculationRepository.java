package com.eu.taxcalculation.calculation.respository;

import com.eu.taxcalculation.calculation.entity.Calculation;
import com.eu.taxcalculation.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    public Calculation findByUuid(String id);

    //public void savePayment(Payment payment);

    public Calculation findByTin(String userid);

    public List<Calculation> findAll();

    public Calculation save(Calculation calculation);

    public Calculation findByTinAndAssessmentYear(String tin, String assmntYear);
    @Query(value = "select * from calculations c where c.tin = ?1 and c.assessment_year = ?2 and c.submitted = ?3",
            nativeQuery = true)
    public Calculation findByTinAndAssessmentYearAndSubmitted(String tin, String assmntYear, String submitted);
}
