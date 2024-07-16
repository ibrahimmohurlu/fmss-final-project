package com.ibrahimmohurlu.notification_service.consumer;


import com.ibrahimmohurlu.notification_service.config.RabbitConfig;
import com.ibrahimmohurlu.notification_service.consumer.dto.PackagePurchasedMessageDto;
import com.ibrahimmohurlu.notification_service.model.enums.NotificationType;
import com.ibrahimmohurlu.notification_service.notification.SendNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final SendNotificationService notificationService;

    @RabbitListener(queues = RabbitConfig.NOTIFICATION_QUEUE)
    public void sendNotification(PackagePurchasedMessageDto notificationDto) {
        notificationService.sendNotification(
                NotificationType.MAIL,
                "package purchased successfully. package=" + notificationDto.toString()
        );

    }

}
