package com.ibrahimmohurlu.package_service.producer;

import com.ibrahimmohurlu.package_service.config.RabbitConfig;
import com.ibrahimmohurlu.package_service.producer.dto.PackagePurchasedMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendPackagePurchaseMessage(PackagePurchasedMessageDto message) {
        rabbitTemplate.convertAndSend(RabbitConfig.PACKAGE_PURCHASE_EXCHANGE, "", message);
    }
}

