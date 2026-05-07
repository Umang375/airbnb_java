package com.mainProject.airBnb.strategies;

import com.mainProject.airBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SurgePricing implements PricingStrategies{

    private final PricingStrategies wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return wrapped.calculatePrice(inventory).multiply(inventory.getSurgeFactor());
    }
}
