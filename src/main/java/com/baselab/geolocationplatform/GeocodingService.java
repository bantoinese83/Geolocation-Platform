package com.baselab.geolocationplatform;

import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GeocodingService {

    private static final Logger logger = LoggerFactory.getLogger(GeocodingService.class);
    private static final String NOMINATIM_API_URL = "https://nominatim.openstreetmap.org/search?format=json&q=";

    public List<GeocodingResponse> search(@NotBlank String query) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            logger.info("Sending geocoding request to Nominatim API with query: {}", query);
            GeocodingResponse[] responses = restTemplate.getForObject(NOMINATIM_API_URL + query, GeocodingResponse[].class);
            if (responses.length == 0) {
                logger.warn("No geocoding results found for query: {}", query);
                return Collections.emptyList();
            }
            return Arrays.asList(responses);
        } catch (RestClientException e) {
            logger.error("Error occurred while calling Nominatim API", e);
            return Collections.emptyList();
        }
    }
}