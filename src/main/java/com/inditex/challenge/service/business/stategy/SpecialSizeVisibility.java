package com.inditex.challenge.service.business.stategy;

import com.inditex.challenge.model.business.Size;

import java.util.Set;

public class SpecialSizeVisibility implements VisibilityStrategy{
    @Override
    public boolean isVisible(Set<Size> sizes) {
        return isSpecialAndHasStock(sizes) && isCommonSizeAndHasStock(sizes);
    }

    private static boolean isCommonSizeAndHasStock(Set<Size> sizes) {
        return sizes.stream().anyMatch(Size::isSpecialAndHasStock);
    }

    private static boolean isSpecialAndHasStock(Set<Size> sizes) {
        return sizes.stream().anyMatch(Size::isCommonAndHasStock);
    }


}
