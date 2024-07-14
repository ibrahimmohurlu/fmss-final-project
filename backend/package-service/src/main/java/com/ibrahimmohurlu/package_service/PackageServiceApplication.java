package com.ibrahimmohurlu.package_service;

import com.ibrahimmohurlu.package_service.model.Package;
import com.ibrahimmohurlu.package_service.repository.PackageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class PackageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PackageServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner packagesInitData(PackageRepository repo) {
        return args -> {
            Package p = new Package();
            p.setName("basic");
            p.setDescription("Basic package with 10 Listing allowance");
            p.setPrice(123.45);
            p.setListingAllowance(10);
            p.setDurationDays(30);

            if (repo.findAll().isEmpty()) {
                repo.save(p);
            }

        };
    }
}
