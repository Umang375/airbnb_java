package com.mainProject.airBnb.service;

import com.mainProject.airBnb.entity.Inventory;
import com.mainProject.airBnb.strategies.*;

import java.math.BigDecimal;

public class PricingService {

    public BigDecimal calculateDynamicPricing(Inventory inventory){
        PricingStrategies priceStrategy = new BasePricing();

        //apply all the other strategies
        priceStrategy = new SurgePricing(priceStrategy);
        priceStrategy = new OccupancyPricing(priceStrategy);
        priceStrategy = new UrgencyPricing(priceStrategy);
        priceStrategy = new HolidayPricing(priceStrategy);


        return priceStrategy.calculatePrice(inventory);
    }
}
