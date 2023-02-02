package com.eu.taxcalculation.user.service;

import com.eu.taxcalculation.user.entity.EmailDetails;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
