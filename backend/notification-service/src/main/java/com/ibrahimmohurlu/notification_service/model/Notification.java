package com.ibrahimmohurlu.notification_service.model;


import com.ibrahimmohurlu.notification_service.model.enums.NotificationStatus;
import com.ibrahimmohurlu.notification_service.model.enums.NotificationType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("notification")
@Builder
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime createdAt;
    private NotificationStatus status;
    private String message;
}
