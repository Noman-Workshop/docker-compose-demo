package com.eu.taxcalculation.payment.controller;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.payment.service.PaymentService;
import com.eu.taxcalculation.user.config.JwtGeneratorInterface;
import com.eu.taxcalculation.user.entity.User;
import com.eu.taxcalculation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private PaymentService paymentService;

    @Autowired
    public PaymentController( PaymentService paymentService){

        this.paymentService=paymentService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<?> savePayment(@RequestBody Payment payment){
        try{
            paymentService.savePayment(payment);
            return new ResponseEntity<>(payment, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/payment-id/{id}")
    public ResponseEntity<?> getPayment(@PathVariable String id){
        try{
            Payment p = paymentService.getPaymentByUUID(id);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/payment-tin")
    public ResponseEntity<?> getPaymentByTinAndYear(@RequestParam("tin") String tin, @RequestParam("year") String year){
        try{
            //System.out.println("getting tin=="+tin);
            Payment p = paymentService.getPaymentByTinNoAndYear(tin,year);
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<?> getPayments() {

        //Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(authentication);
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }

}
