package com.inditex.challenge.service.business.stategy;

import com.inditex.challenge.model.business.Size;

import java.util.Set;

public interface VisibilityStrategy {

    boolean isVisible(Set<Size> size);
}
