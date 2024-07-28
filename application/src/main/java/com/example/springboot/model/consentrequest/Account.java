package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Account {
    @JsonProperty("SchemeName")
    private String SchemeName;
    @JsonProperty("Identification")
    private String Identification;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("SecondaryIdentification")
    private String SecondaryIdentification;
}
