package com.ibrahimmohurlu.payment_service.service;

import com.ibrahimmohurlu.payment_service.config.RabbitConfig;
import com.ibrahimmohurlu.payment_service.model.Payment;
import com.ibrahimmohurlu.payment_service.producer.RabbitMessageProducer;
import com.ibrahimmohurlu.payment_service.producer.dto.PackagePurchasedMessageDto;
import com.ibrahimmohurlu.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RabbitMessageProducer producer;
    /**
     * We just save the purchase data into Mongo DB
     * and send purchase confirmation message to package service
     * using Rabbit MQ. This code simulates a real payment
     * happened here.
     * */
    @RabbitListener(queues = RabbitConfig.PAYMENT_QUEUE)
    public void handlePayment(PackagePurchasedMessageDto message) {

        Payment payment = Payment
                .builder()
                .userEmail(message.getUserEmail())
                .purchasedPackage(message.getUserPackage().getUserPackage())
                .purchaseDate(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);
        producer.sendPurchaseConfirmationMessage(message);
        log.info("Payment processed for package: {}", message);
    }
}
