package com.korit.projectrrs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    // 이메일의 호스트 주소
    @Value("${spring.mail.host}")
    private String host;

    // 587
    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    // 해당 메서드의 반환값을 spring bean으로 등록하여 다른곳에서 받을 수 있도록 설정
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        // 이메일 전송 시 사용할 추가 속성 설정을 위한 객체 생성
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true"); // smtp 인증 사용
        props.put("mail.smtp.starttls.enable", "true"); // STARTTLS 활성화
        props.put("mail.debug", "true"); // 디버깅 활성화 - 메일 전송 시 로그 출력

        return mailSender;
    }
}