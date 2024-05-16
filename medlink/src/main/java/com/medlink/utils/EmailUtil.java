package com.medlink.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
            + "<head>"
            + "<style>"
            + "@keyframes fadeIn {"
            + "  from { opacity: 0; }"
            + "  to { opacity: 1; }"
            + "}"
            + "@keyframes slideIn {"
            + "  from { transform: translateY(20px); opacity: 0; }"
            + "  to { transform: translateY(0); opacity: 1; }"
            + "}"
            + ".fade-in {"
            + "  animation: fadeIn 2s ease-in-out;"
            + "}"
            + ".slide-in {"
            + "  animation: slideIn 1s ease-in-out;"
            + "}"
            + "</style>"
            + "</head>"
            + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'>"
            + "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border-collapse: collapse; margin: 20px auto; background-color: #ffffff; padding: 0; border: 1px solid #dddddd;'>"
            + "<tr class='fade-in'>"
            + "<td align='center' style='padding: 20px 0; background-color: #007bff; color: #ffffff;'>"
            + "<img src='https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHZ5ZWQ5eGFqNXQ1dmVoMzM1anVpZ3ZndjExdXc1MXprbmo0OWpxcCZlcD12MV9pbnRlcm5naWZfYnlfaWQmY3Q9Zw/dv78V39sfMssrjpHWO/giphy.gif' alt='Medlink GIF' width='100' style='display: block;' />"
            + "<h1 style='margin: 0; font-size: 24px;'>Medlink</h1>"
            + "</td>"
            + "</tr>"
            + "<tr class='slide-in'>"
            + "<td style='padding: 20px 30px 40px 30px;'>"
            + "<h2 style='color: #333333; text-align: center;'>Verify Your Account</h2>"
            + "<p style='color: #333333;'>Dear User,</p>"
            + "<p style='color: #333333;'>Please use the following OTP to verify your account:</p>"
            + "<p style='color: #333333; text-align: center; font-size: 24px; font-weight: bold;'>" + otp + "</p>"
            + "<p style='color: #333333;'>If you did not request this verification, please ignore this email.</p>"
            + "<div style='text-align: center; margin-top: 20px;'>"
            + "</div>"
            + "<p style='color: #333333;'>Thank you,</p>"
            + "<p style='color: #333333;'>Team Medlink</p>"
            + "</td>"
            + "</tr>"
            + "<tr class='fade-in'>"
            + "<td style='padding: 20px; background-color: #f4f4f4; text-align: center; font-size: 12px; color: #777777;'>"
            + "<p>© 2024 Medlink. All rights reserved.</p>"
            + "</td>"
            + "</tr>"
            + "</table>"
            + "</body>"
            + "</html>";
            
            mimeMessageHelper.setText(htmlContent, true);
            
            javaMailSender.send(mimeMessage);
          }
        }

      