package com.eu.taxcalculation.calculation.controller;

import com.eu.taxcalculation.calculation.entity.Calculation;
import com.eu.taxcalculation.calculation.exception.CalculationNotFoundException;
import com.eu.taxcalculation.calculation.respository.CalculationRepository;
import com.eu.taxcalculation.calculation.service.CalculationService;
import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.exception.UserNotFoundException;
import com.eu.taxcalculation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.Year;

@RestController
@RequestMapping("/api/v1/calculation")
public class CalculationController {
    @Autowired
    private CalculationService  calculationService;

    @Autowired
    private CalculationRepository calculationRepository;
    @Autowired
    public CalculationController(CalculationService calculationService){
        this.calculationService = calculationService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<?> saveCalculation(@RequestBody Calculation calculation){
        try{
            Calculation calculation1 = calculationService.saveCalculation(calculation);
            return new ResponseEntity<>(calculation1, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/calculation-tin")
    public ResponseEntity<?> getCalculationByTin(@RequestParam("tin") String tin, @RequestParam("year") String year){
        try{
            return new ResponseEntity<>(calculationService.getCalculationByTinNAssessmentYearNSubmitted(tin,year), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/calculation-uuid/{uuid}")
    public ResponseEntity<?> getCalculationByUUID(@PathVariable String uuid){
        try{
            return new ResponseEntity<>(calculationService.getCalculationByUUID(uuid), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update/{tin}")
    public ResponseEntity<?> updateCalculation(@PathVariable String tin, @RequestBody Calculation calculation) {
        try {

            Calculation calculation1 = calculationService.updateCalculationByTinNo(tin,calculation);
           /* if(calculation1 == null){
                throw new CalculationNotFoundException("Calculation Not Found");
            }*/
            return new ResponseEntity<>(calculation1, HttpStatus.OK);
        } catch (CalculationNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/all")

    public ResponseEntity<?> GetAllCalculation() {
        return new ResponseEntity<>(calculationService.getAllCalculation(), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/submitted/{tin}")
    public ResponseEntity<?> GetSubmittedReturn(@PathVariable String tin) {
        int year = Year.now().getValue();
        int nextYear = Year.now().getValue()+1;
        String assessmentYear = year+"-"+nextYear;
        System.out.println(assessmentYear);
        System.out.println(tin);
        try{
            Calculation calculation = calculationService.getCalculationByTinNAssessmentYearNSubmitted(tin,assessmentYear);
            return new ResponseEntity<>(calculation, HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
