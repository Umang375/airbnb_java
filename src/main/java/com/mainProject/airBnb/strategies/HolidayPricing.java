package com.mainProject.airBnb.strategies;

import com.mainProject.airBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class HolidayPricing implements PricingStrategies{

    private final PricingStrategies wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean isTodayHoliday = true;
        LocalDate today = LocalDate.now();
        if(isTodayHoliday){
            price = price.multiply((BigDecimal.valueOf(1.15)));
        }
        return price;
    }
}

