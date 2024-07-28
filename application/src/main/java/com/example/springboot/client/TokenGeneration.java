package com.example.springboot.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@AllArgsConstructor
public class TokenGeneration {
    private final String REQUEST_URL = "https://ob.sandbox.natwest.com/token";
    private final String GRANT_TYPE = "client_credentials";
    private final String CLIENT_ID = "C8sICKZnnYXRNvC40jIYPk27UuGMzEJ5-zZ-RKYoMFY=";
    private final String CLIENT_SECRET = "_2gAaWMDLcn6r-ToNsnAFEjKK9pw3D7AjIZC1k4oz34=";
    private final String SCOPE = "payments";

    @Autowired
    private RestTemplate restTemplate;

    public String getBearerToken() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", GRANT_TYPE);
        map.add("client_id", CLIENT_ID);
        map.add("client_secret", CLIENT_SECRET);
        map.add("scope", SCOPE);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map ,
                headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                REQUEST_URL, HttpMethod.POST, entity,
                String.class);
        System.out.println("responseEntity.getBody()-"+responseEntity.getBody());;
        return extractAccessToken(responseEntity.getBody());
    }

    private String extractAccessToken(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseBody);
            return rootNode.path("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token from response", e);
        }
    }

}
