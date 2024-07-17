package com.ibrahimmohurlu.listing_service.service;

import com.ibrahimmohurlu.listing_service.dto.UserPackageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceWebClient {
    private final WebClient.Builder webClient;

    public List<UserPackageResponseDto> getUserActivePackages(String authHeader){
        return webClient
                .build()
                .get()
                .uri("http://USER-SERVICE/api/v1/users/packages/active")
                .header("Authorization", authHeader)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ClientResponse::createException)
                .bodyToMono(new ParameterizedTypeReference<List<UserPackageResponseDto>>() {
                }).block();
    }
}
