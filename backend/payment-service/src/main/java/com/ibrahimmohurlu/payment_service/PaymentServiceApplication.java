package com.ibrahimmohurlu.payment_service;

import com.ibrahimmohurlu.payment_service.model.Payment;
import com.ibrahimmohurlu.payment_service.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PaymentRepository repo) {
        return args -> {
//            repo.save(Payment.builder()
//                    .purchaseDate(LocalDateTime.now())
//                    .userEmail("asd@mail.com")
//                    .build());
            repo.findAll().forEach(System.out::println);
        };
    }
}
