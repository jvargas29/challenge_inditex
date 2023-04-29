package com.inditex.challenge.model.business;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Product {
    private Long id;
    private Long sequence;

    private Set<Size> sizes;

}
