package com.korit.projectrrs.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailService {
    MimeMessage createMailForId(String email, String username, String token) throws MessagingException;
    MimeMessage createMailForPw(String email, String username, String token) throws MessagingException;
    String sendSimpleMessage(MimeMessage message,String token) throws MessagingException;
    String verifyEmail(String token);
}