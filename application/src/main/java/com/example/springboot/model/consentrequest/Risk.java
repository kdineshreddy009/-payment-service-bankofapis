package com.example.springboot.model.consentrequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Risk {
    @JsonProperty("PaymentContextCode")
    private String PaymentContextCode;
    @JsonProperty("MerchantCategoryCode")
    private String MerchantCategoryCode;
    @JsonProperty("MerchantCustomerIdentification")
    private String MerchantCustomerIdentification;
    @JsonProperty("DeliveryAddress")
    private String DeliveryAddress;
}
