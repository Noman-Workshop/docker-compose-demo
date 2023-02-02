package com.eu.taxcalculation.user.repository;

import com.eu.taxcalculation.payment.entity.Payment;
import com.eu.taxcalculation.user.entity.Activation;
import com.eu.taxcalculation.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationRepository extends JpaRepository<Activation, Long> {
    public Activation findByUuid(String uuid);

    public Activation findByActivationCode(String uuid);


}
