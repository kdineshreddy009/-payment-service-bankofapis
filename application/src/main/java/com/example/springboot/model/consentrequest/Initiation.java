package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Initiation {
    @JsonProperty("InstructionIdentification")
    private String InstructionIdentification;
    @JsonProperty("EndToEndIdentification")
    private String EndToEndIdentification;
    @JsonProperty("InstructedAmount")
    private InstructedAmount InstructedAmount;
    @JsonProperty("DebtorAccount")
    private Account DebtorAccount;
    @JsonProperty("CreditorAccount")
    private Account CreditorAccount;
    @JsonProperty("RemittanceInformation")
    private RemittanceInformation RemittanceInformation;
}
