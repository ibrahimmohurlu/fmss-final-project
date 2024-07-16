package com.ibrahimmohurlu.package_service.service;

import com.ibrahimmohurlu.package_service.config.RabbitConfig;
import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.model.User;
import com.ibrahimmohurlu.package_service.model.UserPackage;
import com.ibrahimmohurlu.package_service.producer.dto.PackagePurchasedMessageDto;
import com.ibrahimmohurlu.package_service.repository.PackageRepository;
import com.ibrahimmohurlu.package_service.repository.UserPackageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PackageService {
    private final PackageRepository packageRepository;
    private final UserPackageRepository userPackageRepository;

    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<Package> getPackageById(Long id) {
        return packageRepository.findById(id);
    }

    public UserPackage createUserPackage(Package purchasePackage, Long userId) {

        User user = new User();
        user.setId(userId);
        UserPackage userPackage = new UserPackage();
        userPackage.setUserPackage(purchasePackage);
        userPackage.setUser(user);
        return userPackageRepository.save(userPackage);
    }

    @RabbitListener(queues = RabbitConfig.PAYMENT_CONFIRMATION_QUEUE)
    private void confirmPurchasedPackage(PackagePurchasedMessageDto message) {
        Long userPackageId = message.getUserPackage().getId();
        Optional<UserPackage> optionalUserPackage = userPackageRepository.findById(userPackageId);
        if (optionalUserPackage.isEmpty()) {
            log.error("User Package confirmation failed because entity with id:{} not found", userPackageId);
        }
        UserPackage userPackage = optionalUserPackage.get();
        userPackage.setConfirmed(true);
        userPackage.setConfirmationDate(LocalDateTime.now());
        UserPackage confirmedUserPackage = userPackageRepository.save(userPackage);
        log.info("User package with id:{} confirmed", confirmedUserPackage.getId());
    }
}
