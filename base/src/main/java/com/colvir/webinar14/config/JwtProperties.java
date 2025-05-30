package com.colvir.webinar14.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.jwt")
public class JwtProperties {

    private String secret;
}
