package com.colvir.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * Alternative way to propagate information about Authorization Header
 */
@Slf4j
public class OAuth2AccessTokenManager {

    public static String getAccessToken() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return "Bearer " + ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
        } catch (Exception exp) {
            log.error("client credentials error {}", exp.getMessage());
        }
        return null;
    }
}
