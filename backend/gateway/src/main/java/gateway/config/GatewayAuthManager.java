package gateway.config;

import gateway.dto.UserInfoDto;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class GatewayAuthManager implements ReactiveAuthenticationManager {

    private final WebClient.Builder webClientBuilder;

    public GatewayAuthManager(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();


        return webClientBuilder.build()
                .get()
                .uri("http://USER-SERVICE/api/v1/users/{email}", name)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (clientResponse -> Mono.error(new InternalServerErrorException("Server is not available"))))
                .onStatus(HttpStatusCode::is4xxClientError,(clientResponse -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
                .bodyToMono(UserInfoDto.class)
                .flatMap(userInfo -> {
                    if (userInfo != null && userInfo.getPassword().equals(password)) {
                        return Mono.just(new UsernamePasswordAuthenticationToken(name, password, Collections.emptyList()));
                    }
                    return Mono.error(new BadCredentialsException("Invalid Credentials"));
                });
    }
}
