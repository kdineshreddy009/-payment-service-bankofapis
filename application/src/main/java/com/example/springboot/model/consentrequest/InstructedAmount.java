package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InstructedAmount {
    @JsonProperty("Amount")
    private String Amount;
    @JsonProperty("Currency")
    private String Currency;
}
