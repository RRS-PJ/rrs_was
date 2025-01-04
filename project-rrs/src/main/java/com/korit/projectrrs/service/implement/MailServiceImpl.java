package com.korit.projectrrs.service.implement;

import com.korit.projectrrs.provider.JwtProvider;
import com.korit.projectrrs.service.MailService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;
    @Value("${spring.mail.username}")
    private static String senderEmail;
    @Override
    public MimeMessage createMail(String mail, String token) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("RRS 이메일 인증 링크");
        String body = "";
        body += "<h3>RRS 이메일 인증 링크입니다.</h3>";
        body += "<a href=\"http://localhost:4040/api/v1/mail/verify?token=" + token + "\">여기를 클릭하여 인증하세요</a>";
        body += "<p>감사합니다.</p>";
        message.setText(body, "UTF-8", "html");
        return message;
    }
    @Override
    public String sendSimpleMessage(String sendEmail, String username) throws MessagingException {
        String token = jwtProvider.generateEmailValidToken(username);
        MimeMessage message = createMail(sendEmail, token);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return "메일 발송 중 오류가 발생하였습니다.";
        }
        return token;
    }
    @Override
    public String verifyEmail(String token) {
        try {
            String username = jwtProvider.getUsernameFromEmailJwt(token);
            return username + "님 인증되셨습니다.";
        } catch (ExpiredJwtException e) {
            return "만료된 인증 토큰입니다.";
        }
    }
}