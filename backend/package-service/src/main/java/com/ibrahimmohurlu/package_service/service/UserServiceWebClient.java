package com.ibrahimmohurlu.package_service.service;

import com.ibrahimmohurlu.package_service.dto.UserByEmailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class UserServiceWebClient {
    private final WebClient.Builder webClient;

    public UserByEmailResponseDto getUserByEmail(String email, String authHeader) {
        return webClient
                .build()
                .get()
                .uri("http://USER-SERVICE/api/v1/users/" + email)
                .header("Authorization", authHeader)
                .retrieve()
                .onStatus(HttpStatusCode::isError, ClientResponse::createException)
                .bodyToMono(UserByEmailResponseDto.class)
                .block();
    }
}
