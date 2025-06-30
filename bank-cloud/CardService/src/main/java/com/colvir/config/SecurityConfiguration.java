package com.colvir.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, KeycloakAuthenticationConverter authenticationConverter) throws Exception {
        return http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/actuator/**").permitAll()
                                .anyRequest().authenticated())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(authenticationConverter)))
                .build();
    }

    @Component
    static class KeycloakAuthoritiesConverter implements Converter<Jwt, List<SimpleGrantedAuthority>> {
        @Override
        @SuppressWarnings("unchecked")
        public List<SimpleGrantedAuthority> convert(Jwt jwt) {
            final var realmAccess = (Map<String, Object>) jwt.getClaims().getOrDefault("realm_access", Map.of());
            final var roles = (List<String>) realmAccess.getOrDefault("roles", List.of());
            // add some processing here like a "ROLE_" prefix if you prefer hasRole over hasAuthority and your Keycloak roles do not start with ROLE_ already
            return roles.stream().map(SimpleGrantedAuthority::new).toList();
        }
    }

    @Component
    @RequiredArgsConstructor
    static class KeycloakAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {
        private final KeycloakAuthoritiesConverter authoritiesConverter;

        @Override
        public JwtAuthenticationToken convert(Jwt jwt) {
            return new JwtAuthenticationToken(jwt, authoritiesConverter.convert(jwt), jwt.getClaimAsString(StandardClaimNames.PREFERRED_USERNAME));
        }

    }
}
