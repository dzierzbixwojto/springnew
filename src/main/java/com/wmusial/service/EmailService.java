package com.wmusial.service;

/**
 * Created by dzier on 29.10.2016.
 */
public interface EmailService {
    void sendEmail(String fromAddress, String toAddress, String subject, String body);
}
