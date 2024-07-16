package com.ibrahimmohurlu.payment_service.service;

import com.ibrahimmohurlu.payment_service.config.RabbitConfig;
import com.ibrahimmohurlu.payment_service.dto.PackagePurchasedMessageDto;
import com.ibrahimmohurlu.payment_service.model.Payment;
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

    @RabbitListener(queues = RabbitConfig.PAYMENT_QUEUE)
    public void handlePayment(PackagePurchasedMessageDto message) {
        Payment payment = Payment
                .builder()
                .userEmail(message.getUserEmail())
                .purchasedPackage(message.getUserPackage().getUserPackage())
                .purchaseDate(LocalDateTime.now())
                .build();
        paymentRepository.save(payment);
        log.info("Payment processed for package: {}", message);
    }
}
