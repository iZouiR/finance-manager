package org.izouir.notificationservice.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.izouir.notificationservice.dto.LimitationNotificationDto;
import org.izouir.notificationservice.service.LimitationListenerService;
import org.izouir.notificationservice.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitationListenerServiceImpl implements LimitationListenerService {
    private final MailService mailService;

    private static final String LIMITATION_NOTIFICATION_SUBJECT = "Limitation reached!";
    private static final String LIMITATION_NOTIFICATION_MESSAGE = "You have reached limitation - %s$! Overrun - %s$!";

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.properties.topic-limitation}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "limitationListener")
    public void listen(final LimitationNotificationDto dto) throws MessagingException {
        final var overrun = dto.getLimitation().getAmount().subtract(dto.getLimitation().getSpent());
        final var text = String.format(LIMITATION_NOTIFICATION_MESSAGE, dto.getLimitation().getAmount(), overrun);

        // TODO: add SMS, Push notifications
        mailService.sendNotification(dto.getEmail(), LIMITATION_NOTIFICATION_SUBJECT, text);
    }
}
