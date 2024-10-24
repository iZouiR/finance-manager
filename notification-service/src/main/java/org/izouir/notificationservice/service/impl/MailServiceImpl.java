package org.izouir.notificationservice.service.impl;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.izouir.notificationservice.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    @Value("${MAIL_SMTP_USERNAME}")
    private String smtpUsername;
    @Value("${MAIL_SMTP_PASSWORD}")
    private String smtpPassword;

    @Override
    public void sendNotification(final String email, final String subject, final String text) throws MessagingException {
        final var session = createSmtpSession();
        final var message = constructMessage(session, email, subject, text);
        Transport.send(message);
    }

    private Session createSmtpSession() {
        final var properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.host", "smtp.yandex.ru");
        properties.put("mail.smtp.port", "465");

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });
    }

    private MimeMessage constructMessage(final Session session, final String email, final String subject, final String text) throws MessagingException {
        final var message = new MimeMessage(session);
        message.setFrom(new InternetAddress(smtpUsername));
        message.setSubject(subject);
        message.setText(text);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSentDate(new Date());
        return message;
    }
}
