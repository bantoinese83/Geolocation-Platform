package com.baselab.geolocationplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableConfigurationProperties(SwaggerProperties.class)
public class GeolocationPlatformApplication {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationPlatformApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GeolocationPlatformApplication.class, args);
        logger.info("Geolocation Platform Application has started.");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        logger.info("Geolocation Platform Application is ready to serve requests.");
    }
}