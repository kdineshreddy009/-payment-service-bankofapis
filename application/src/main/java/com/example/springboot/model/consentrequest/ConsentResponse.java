package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConsentResponse {

    @JsonProperty("Data")
    private ResponseData data;

    @Setter
    @Getter
    public static class ResponseData {
        @JsonProperty("ConsentId")
        private String consentId;
    }
}
