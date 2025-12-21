package com.mainProject.airBnb.dto;

import com.mainProject.airBnb.entity.HotelContactInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String[] photos;
    private String[] amenities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private HotelContactInfo contactInfo;
    private Boolean isActive;

}
