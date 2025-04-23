package com.colvir.webinar4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.colvir.webinar4")
@EnableAspectJAutoProxy
public class AppConfig {
}
