package com.inditex.challenge.service.business.stategy;

import com.inditex.challenge.model.business.Size;

import java.util.Set;

public class CommonSizeVisibility implements VisibilityStrategy{

    @Override
    public boolean isVisible(Set<Size> sizes) {
         return sizes.stream().anyMatch(size-> size.hasStock() || size.isBackSoon());
    }

}
