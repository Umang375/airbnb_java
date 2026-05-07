package com.mainProject.airBnb.strategies;

import com.mainProject.airBnb.entity.Inventory;

import java.math.BigDecimal;

public class BasePricing implements PricingStrategies{
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
