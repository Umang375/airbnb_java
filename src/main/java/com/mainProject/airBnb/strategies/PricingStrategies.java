package com.mainProject.airBnb.strategies;

import com.mainProject.airBnb.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PricingStrategies {

    BigDecimal calculatePrice(Inventory inventory);
}
