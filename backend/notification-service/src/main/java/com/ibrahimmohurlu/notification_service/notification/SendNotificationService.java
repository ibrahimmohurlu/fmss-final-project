package com.ibrahimmohurlu.notification_service.notification;

import com.ibrahimmohurlu.notification_service.model.enums.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SendNotificationService {
    private final ApplicationContext context;

    public void sendNotification(NotificationType type, String message) {
        NotificationStrategy strategy = (NotificationStrategy) context.getBean(type.name() + "NotificationStrategy");
        strategy.sendNotification(message);
    }
}
