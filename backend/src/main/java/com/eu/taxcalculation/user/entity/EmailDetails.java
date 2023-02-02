package com.eu.taxcalculation.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

    public EmailDetails(String rec, String msg, String sub, String att){
        this.recipient = rec;
        this.msgBody = msg;
        this.subject = sub;
        this.attachment = att;
    }
}
