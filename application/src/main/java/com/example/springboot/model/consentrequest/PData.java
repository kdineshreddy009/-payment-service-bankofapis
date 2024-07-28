package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PData {
    @JsonProperty("Initiation")
    private Initiation Initiation;
}
