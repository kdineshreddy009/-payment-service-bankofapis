package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentConsentRequest {
    @JsonProperty("Data")
    private PData Data;
    @JsonProperty("Risk")
    private Risk Risk;
}
