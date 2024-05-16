package com.medlink.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class EmailUtil {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendOtpEmail(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Verify OTP");

    String htmlContent = "<html>"
            + "<body>"
            + "<h2>Verify your account</h2>"
            + "<p>Dear User,</p>"
            + "<p>Please use the following OTP to verify your account:</p>"
            + "<p><strong>" + otp + "</strong></p>"
            + "<p>If you did not request this verification, please ignore this email.</p>"
            + "<p>Thank you,</p>"
            + "<p>Team Medlink</p>"
            + "</body>"
            + "</html>";

    mimeMessageHelper.setText(htmlContent, true);

    javaMailSender.send(mimeMessage);
}
}

