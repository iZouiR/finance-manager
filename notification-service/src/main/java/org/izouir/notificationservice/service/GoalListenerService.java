package org.izouir.notificationservice.service;

import jakarta.mail.MessagingException;
import org.izouir.notificationservice.dto.GoalNotificationDto;

public interface GoalListenerService {
    void listen(final GoalNotificationDto dto) throws MessagingException;
}
