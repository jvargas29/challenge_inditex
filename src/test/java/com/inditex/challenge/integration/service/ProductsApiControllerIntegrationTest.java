package com.inditex.challenge.integration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inditex.challenge.model.ProductResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsApiControllerIntegrationTest {

    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate restTemplate;

    private  ProductResponse  expectedTestCase;

    @BeforeEach
    public void setup() throws JsonProcessingException {

        String responseTestCase= """
                {
                  "productsId": "5,1,3"
                }""";

        this.expectedTestCase = mapperToProductResponse(responseTestCase);
    }

    @Test
    void testCase() throws Exception {
        ResponseEntity<ProductResponse> response = getProducts("/products");
        ProductResponse actual = response.getBody() != null ? response.getBody() : null;
        Assertions.assertThat(actual).isEqualTo(this.expectedTestCase);
    }

    private ResponseEntity<ProductResponse> getProducts(String parameters) throws MalformedURLException {
        return restTemplate.getForEntity(
                new URL("http://localhost:" + port + parameters).toString(), ProductResponse.class);
    }

    private ProductResponse mapperToProductResponse(String responseTestCase1) throws JsonProcessingException {
        ObjectMapper objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.readValue(responseTestCase1,ProductResponse.class);
    }
}
