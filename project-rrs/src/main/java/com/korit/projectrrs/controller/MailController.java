package com.korit.projectrrs.controller;

import com.korit.projectrrs.dto.mail.SendMailRequestDto;
import com.korit.projectrrs.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;

//    @PostMapping("/send")
//    public String sendEmail(@RequestBody SendMailRequestDto mailDto, String token) throws MessagingException {
//        return mailService.sendSimpleMessage(mailDto.getEmail(), mailDto.getUsername(), token);
//    }
//
//    @GetMapping("/verify")
//    public String verifyEmail(@RequestParam String token) {
//        return mailService.verifyEmail(token);
//    }
}