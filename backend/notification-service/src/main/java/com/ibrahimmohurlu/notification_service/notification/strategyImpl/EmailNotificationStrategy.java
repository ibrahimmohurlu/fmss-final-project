package com.ibrahimmohurlu.notification_service.notification.strategyImpl;


import com.ibrahimmohurlu.notification_service.model.Notification;
import com.ibrahimmohurlu.notification_service.model.enums.NotificationStatus;
import com.ibrahimmohurlu.notification_service.model.enums.NotificationType;
import com.ibrahimmohurlu.notification_service.notification.NotificationStrategy;
import com.ibrahimmohurlu.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("MAILNotificationStrategy")
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationStrategy implements NotificationStrategy {
    private final NotificationRepository repository;

    @Override
    public void sendNotification(String message) {
        try {
            /**
             * we are just logging the notification but let's assume
             * we are really sending notification and it can throw exception
             * */
            log.info("Sending email notification message: {}", message);
            Notification notification = Notification.builder()
                    .createdAt(LocalDateTime.now())
                    .status(NotificationStatus.SUCCESS)
                    .type(NotificationType.MAIL)
                    .message(message)
                    .build();
            repository.save(notification);
        } catch (Exception e) {
            /**
             * when exception thrown we save failed notification
             * */
            Notification notification = Notification.builder()
                    .createdAt(LocalDateTime.now())
                    .status(NotificationStatus.FAILED)
                    .type(NotificationType.MAIL)
                    .message(message)
                    .build();
            repository.save(notification);
        }

    }
}
