package gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class UserIdHeaderInterceptor implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/api/v1/packages") || path.startsWith("/api/v1/listings")) {
            return chain.filter(exchange); // permitAll endpointler için filtreden çık
        }
        return ReactiveSecurityContextHolder
                .getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
                    /*
                     * if we are authenticated get userId from authentication details
                     * and add to header and forward the request
                     * */
                    if (authentication != null && authentication.isAuthenticated()) {
                        String userId = authentication.getDetails().toString();
                        if (userId != null) {
                            ServerHttpRequest request = exchange.getRequest().mutate()
                                    .header("USER_ID", userId)
                                    .build();
                            return chain.filter(exchange.mutate().request(request).build());
                        }
                    }
                    return chain.filter(exchange);
                });
    }
}
