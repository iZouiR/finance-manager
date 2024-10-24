package org.izouir.notificationservice.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.izouir.notificationservice.dto.GoalNotificationDto;
import org.izouir.notificationservice.service.GoalListenerService;
import org.izouir.notificationservice.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalListenerServiceImpl implements GoalListenerService {
    private final MailService mailService;

    private static final String GOAL_NOTIFICATION_SUBJECT = "Goal completed!";
    private static final String GOAL_NOTIFICATION_MESSAGE = "You have completed goal - %s$!";

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.properties.topic-goal}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "goalListener")
    public void listen(final GoalNotificationDto dto) throws MessagingException {
        final var text = String.format(GOAL_NOTIFICATION_MESSAGE, dto.getGoal());

        // TODO: add SMS, Push notifications
        mailService.sendNotification(dto.getEmail(), GOAL_NOTIFICATION_SUBJECT, text);
    }
}
