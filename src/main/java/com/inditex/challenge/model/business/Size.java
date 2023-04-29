package com.inditex.challenge.model.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Size {

    private Long sizeId;
    private Long productId;
    private boolean backSoon;
    private boolean special;
    private Integer quantity;

}
