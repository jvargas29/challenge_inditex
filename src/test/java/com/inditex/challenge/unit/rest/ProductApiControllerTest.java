package com.inditex.challenge.unit.rest;


import com.inditex.challenge.service.business.ProductVisibilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ProductApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductVisibilityService productVisibilityService;

    String productResponse;

    @BeforeEach
    public void setup(){
        productResponse = "1,2,3";
    }

    @Test
    void whenValidParam_thenReturns200() throws Exception {
        when(productVisibilityService.getProductsVisibles()).thenReturn(productResponse);

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.productsId").value("1,2,3"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void whenFailService_thenReturns500() throws Exception {
        doThrow(RuntimeException.class).when(productVisibilityService).getProductsVisibles();

        mockMvc.perform(get("/products"))
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }
}
