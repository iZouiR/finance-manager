package org.izouir.notificationservice.service;

import jakarta.mail.MessagingException;

public interface MailService {
    void sendNotification(final String email, final String subject, final String text) throws MessagingException;
}
