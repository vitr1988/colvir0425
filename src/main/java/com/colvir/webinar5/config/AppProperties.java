package com.colvir.webinar5.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Data
@ConfigurationProperties("app")
public class AppProperties {

    private String version;
    private Long digit;
    private ContextResource context;

    @Data
    public static class ContextResource {
        private Resource file;
    }
}
