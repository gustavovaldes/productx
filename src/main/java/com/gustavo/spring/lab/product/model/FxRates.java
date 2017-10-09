package com.gustavo.spring.lab.product.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FxRates {

    @JsonProperty
    private String base;
    @JsonProperty
    private String date;
    @JsonProperty
    private Map<String, Double> rates;
}


