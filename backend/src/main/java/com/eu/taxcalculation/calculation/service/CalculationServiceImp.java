package com.eu.taxcalculation.calculation.service;

import com.eu.taxcalculation.calculation.entity.Calculation;
import com.eu.taxcalculation.calculation.exception.CalculationNotFoundException;
import com.eu.taxcalculation.calculation.respository.CalculationRepository;
import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.exception.PaymentNotFoundException;
import com.eu.taxcalculation.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationServiceImp implements CalculationService{
    @Autowired
    private CalculationRepository calculationRepository;
    @Autowired
    public void CalculationServiceImp( CalculationRepository calculationRepository){
        this.calculationRepository = calculationRepository;
    }

    public Calculation exceptionHandler(Calculation calculation) throws CalculationNotFoundException {
        if(calculation==null)
            throw new CalculationNotFoundException("Calculation not found");
        else return calculation;
    }
    public Calculation getCalculationByUUID(String id) throws CalculationNotFoundException {
        Calculation calculation = calculationRepository.findByUuid(id);
        return exceptionHandler(calculation);
    }
    public Calculation getCalculationByTin(String tin) throws CalculationNotFoundException {
        Calculation calculation = calculationRepository.findByTin(tin);
        return exceptionHandler(calculation);
    }
    public List<Calculation> getAllCalculation(){
        return calculationRepository.findAll();
    }
    public Calculation updateCalculationByTinNo(String tin, Calculation calculation) throws CalculationNotFoundException {
        Calculation calculation1 = calculationRepository.findByTin(tin);

        if(calculation1!=null){
            calculation1.setGender(calculation.getGender());
            calculation1.setSalary(calculation.getSalary());
            calculation1.setInvestment(calculation.getInvestment());
            //calculation1.setTin(calculation1.getSalary());
            calculation1.setFestivalBonus(calculation.getFestivalBonus());
            calculation1.setSourceTax(calculation.getSourceTax());
            calculation1.setHouseRent(calculation.getHouseRent());
            calculation1.setAssessmentYear(calculation.getAssessmentYear());
            calculation1.setAmount(calculation.getAmount());
            calculation1.setSubmitted(calculation.getSubmitted());

            Calculation c =calculationRepository.save(calculation1);
            return c;
        }else{
            return exceptionHandler(calculation1);
        }

    }

    public Calculation getCalculationByTinNAssessmentYear(String tin, String year) {
        Calculation calculation = calculationRepository.findByTinAndAssessmentYear(tin,year);
        return calculation;
    }

    public Calculation getCalculationByTinNAssessmentYearNSubmitted(String tin, String year) {
        Calculation calculation = calculationRepository.findByTinAndAssessmentYearAndSubmitted(tin,year,"true");
        return calculation;
    }

    public Calculation saveCalculation(Calculation calculation) throws CalculationNotFoundException{

        double amount= calculateTax(calculation);
        calculation.setAmount(String.valueOf(amount));
        Calculation calc = getCalculationByTinNAssessmentYear(calculation.getTin(),calculation.getAssessmentYear());
        if(calc==null){
            calculationRepository.save(calculation);
            return calculation;
        }else{
            try{
                Calculation cal = updateCalculationByTinNo(calculation.getTin(),calculation);
                return cal;
            }catch (Exception e){
                 throw new CalculationNotFoundException();
            }

        }

    }

    private double maleTaxCalculate(double totalTax){

        double payableAmount =0;
        if(totalTax<=300000)
            payableAmount = 0;

        else if(totalTax>300000 && totalTax<=400000)
            payableAmount = 0.05*(totalTax-300000);

        else if(totalTax>400000 && totalTax<=700000)
            payableAmount=(0.1*(totalTax-400000))+(0.05*100000);

        else if(totalTax>700000 && totalTax<=1100000)
            payableAmount=(0.15*(totalTax-700000))+(0.1*300000)+(0.05*100000);

        else if(totalTax>1100000 && totalTax<=1600000)
            payableAmount=(0.2*(totalTax-1100000))+(0.15*400000)+(0.1*300000)+(0.05*100000);

        else
            payableAmount=(0.25*(totalTax-1600000))+(0.2*500000)+(0.15*400000)+(0.1*300000)+(0.05*100000);
        return payableAmount;
    }

    private double femaleTaxCalculate(double totalTax){

        double payableAmount=0;

        if(totalTax<=350000)
            payableAmount = 0;

        else if(totalTax>350000 && totalTax<=450000)
            payableAmount = 0.05*(totalTax-350000);

        else if(totalTax>450000 && totalTax<=750000)
            payableAmount=(0.1*(totalTax-450000))+(0.05*100000);

        else if(totalTax>750000 && totalTax<=1150000)
            payableAmount=(0.15*(totalTax-750000))+(0.1*300000)+(0.05*100000);

        else if(totalTax>1150000 && totalTax<=1650000)
            payableAmount=(0.2*(totalTax-1150000))+(0.15*400000)+(0.1*300000)+(0.05*100000);

        else
            payableAmount=(0.25*(totalTax-1650000))+(0.2*500000)+(0.15*400000)+(0.1*300000)+(0.05*100000);

        return payableAmount;
    }
    private double calculateTax(Calculation calculation) {
        double baseSalary = Double.parseDouble(calculation.getSalary());
        double festiveNSalaryTax = baseSalary+Double.parseDouble(calculation.getFestivalBonus());
        double baseHouseRent = Double.parseDouble(calculation.getHouseRent());
        double primaryHouseRent = baseSalary*0.5;
        double smallerOne = primaryHouseRent>300000?300000:primaryHouseRent;
        double houseRentTax = baseHouseRent-smallerOne>0?baseHouseRent-smallerOne:0;
        double totalTax = festiveNSalaryTax+houseRentTax;
        double investment = Double.parseDouble(calculation.getInvestment());
        double investmentRebate= 0;
        if(totalTax>1500000)
            investmentRebate = investment*0.1;
        else
            investmentRebate = investment*0.15;
        double payableAmount=0;
        String gender = calculation.getGender();
//
        if(gender=="male"){
            payableAmount = maleTaxCalculate(totalTax);
        }else{
          payableAmount = femaleTaxCalculate(totalTax);
        }

        double finalPayableTax = payableAmount - investmentRebate ;
        if(finalPayableTax<5000)
            finalPayableTax = 5000- Double.parseDouble(calculation.getSourceTax());
        return finalPayableTax;



    }
}
