package com.baselab.geolocationplatform;

import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geocoding")
public class GeocodingController {

    private static final Logger logger = LoggerFactory.getLogger(GeocodingController.class);
    private final GeocodingService geocodingService;

    public GeocodingController(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<GeocodingResponse>> search(@RequestParam @NotBlank String query) {
        try {
            logger.info("Received geocoding search request with query: {}", query);
            List<GeocodingResponse> responses = geocodingService.search(query);
            if (responses.isEmpty()) {
                logger.warn("No results found for query: {}", query);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            logger.error("Error occurred while processing geocoding search request", e);
            return ResponseEntity.status(500).build();
        }
    }
}