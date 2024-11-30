package com.baselab.geolocationplatform;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class GeocodingResponse {

    @NotBlank
    private String displayName;

    @NotNull
    private Double lat;

    @NotNull
    private Double lon;
}