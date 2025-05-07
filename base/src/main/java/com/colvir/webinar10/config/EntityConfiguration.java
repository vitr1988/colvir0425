package com.colvir.webinar10.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan({"com.colvir.webinar10"
//        , "com.colvir.webinar9"
})
public class EntityConfiguration {
}
