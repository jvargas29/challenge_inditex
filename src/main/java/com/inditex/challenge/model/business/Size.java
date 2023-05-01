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

    public boolean hasStock(){
        return this.quantity>0;
    }

    public boolean isSpecialAndHasStock(){
        return this.isSpecial() && (this.hasStock() || this.isBackSoon());
    }

    public boolean isCommonAndHasStock(){
        return !this.isSpecial() && (this.hasStock() || this.isBackSoon());
    }
}
