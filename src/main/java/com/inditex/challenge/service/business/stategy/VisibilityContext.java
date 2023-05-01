package com.inditex.challenge.service.business.stategy;

import com.inditex.challenge.model.business.Product;

public class VisibilityContext {

     public static VisibilityStrategy getStrategy(Product product) {
        if (!product.hasSpecialSize()) {
            return new CommonSizeVisibility();
        }
        return new SpecialSizeVisibility();
    }

}
