package gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        RequestMatcher listingMatcher = new RegexRequestMatcher("^/api/v1/listings/\\d+$", HttpMethod.GET.toString());
        return http.authorizeExchange(exchange -> exchange
                        .pathMatchers(HttpMethod.GET, "/api/v1/packages")
                        .permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/v1/listings")
                        .permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/v1/listings/*")
                        .permitAll()
                        .anyExchange()
                        .authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(Customizer.withDefaults()).build();

    }

}