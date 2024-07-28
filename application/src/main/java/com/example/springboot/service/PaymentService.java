package com.example.springboot.service;

import com.example.springboot.client.TokenGeneration;
import com.example.springboot.model.consentrequest.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Configuration
@AllArgsConstructor
public class PaymentService {

    TokenGeneration tokenClient;
    RestTemplate restTemplate;
    private final String CLIENT_ID = "C8sICKZnnYXRNvC40jIYPk27UuGMzEJ5-zZ-RKYoMFY=";
    private final String RESPONSE_TYPE = "code id_token";
    private final String SCOPE = "openid payments";
    private final String REDIRECT_URI = "https%3A%2F%2F881199f9-3039-4e08-90f2-04571a7ef731.example.org%2Fredirect";
    private final String STATE = "ABC";
    private final String AUTH_MODE = "AUTO_POSTMAN";
    private final String AUTH_USERNAME = "123456789012@881199f9-3039-4e08-90f2-04571a7ef731.example.org";
    private final String AUTH_ACCOUT = "50000012345601";

    @SneakyThrows
    public String generatePaymentConsent(String financialId) {
        String bearerToken = tokenClient.getBearerToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + bearerToken);
        headers.set("x-fapi-financial-id", financialId);
        headers.set("x-jws-signature", "DUMMY_SIG");
        headers.set("x-idempotency-key", UUID.randomUUID().toString());

        HttpEntity<PaymentConsentRequest> entity = new HttpEntity<>(getPaymentConsentRequestBody(), headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://ob.sandbox.natwest.com/open-banking/v3.1/pisp/domestic-payment-consents",
                HttpMethod.POST,
                entity,
                String.class
        );

//        Leaving this piece for now: Auto-approving consent as well
//        TODO: Customer Auth is somehow failing with error - 400 : "Client id is not present or invalid"
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        ConsentResponse consentResponse = objectMapper.readValue(response.getBody(), ConsentResponse.class);
//        approvePaymentConsentByCustomer(consentResponse.getData().getConsentId());
        return response.getBody();
    }

    public String approvePaymentConsentByCustomer(String consentId){
        System.out.println("Auto approving by User...consentId:"+consentId);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("client_id", CLIENT_ID);
        map.add("response_type", RESPONSE_TYPE);
        map.add("scope", SCOPE);
        map.add("redirect_uri", REDIRECT_URI);
        map.add("state", STATE);
        map.add("request", consentId);
        map.add("authorization_mode", AUTH_MODE);
        map.add("authorization_username", AUTH_ACCOUT);
        map.add("authorization_account", AUTH_ACCOUT);
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map,
                headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://api.sandbox.natwest.com/authorize", HttpMethod.GET, entity,
                String.class);
        System.out.println("redirectURI response="+responseEntity.getBody());;
        return "";
    }

    @SneakyThrows
    private PaymentConsentRequest getPaymentConsentRequestBody() {
        // Construct request body using POJOs
        PaymentConsentRequest requestBody = new PaymentConsentRequest();
        PData data = new PData();
        Initiation initiation = new Initiation();
        InstructedAmount instructedAmount = new InstructedAmount();
        instructedAmount.setAmount("50.00");
        instructedAmount.setCurrency("GBP");

        Account creditorAccount = new Account();
        creditorAccount.setSchemeName("IBAN");
        creditorAccount.setIdentification("BE56456394728288");
        creditorAccount.setName("ACME DIY");
        creditorAccount.setSecondaryIdentification("secondary-identif");

        RemittanceInformation remittanceInformation = new RemittanceInformation();
        remittanceInformation.setUnstructured("Tools");
        remittanceInformation.setReference("Tools");

        initiation.setInstructionIdentification("instr-identification");
        initiation.setEndToEndIdentification("e2e-identification");
        initiation.setInstructedAmount(instructedAmount);
        initiation.setDebtorAccount(null);
        initiation.setCreditorAccount(creditorAccount);
        initiation.setRemittanceInformation(remittanceInformation);

        data.setInitiation(initiation);

        Risk risk = new Risk();
        risk.setPaymentContextCode("EcommerceGoods");
        risk.setMerchantCategoryCode(null);
        risk.setMerchantCustomerIdentification(null);
        risk.setDeliveryAddress(null);

        requestBody.setData(data);
        requestBody.setRisk(risk);
        System.out.println("requestBody"+ new ObjectMapper().writeValueAsString(requestBody));
    return requestBody;
    }
}
