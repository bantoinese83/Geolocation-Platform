package com.baselab.geolocationplatform;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "swagger.api")
public class SwaggerProperties {
    // Getters and Setters
    private String title;
    private String description;
    private String version;

}