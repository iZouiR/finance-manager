package org.izouir.notificationservice.service;

import jakarta.mail.MessagingException;
import org.izouir.notificationservice.dto.LimitationNotificationDto;

public interface LimitationListenerService {
    void listen(final LimitationNotificationDto dto) throws MessagingException;
}
