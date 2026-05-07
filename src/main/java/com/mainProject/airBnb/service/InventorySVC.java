package com.mainProject.airBnb.service;


import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.dto.HotelPriceDto;
import com.mainProject.airBnb.dto.HotelSearchDto;
import com.mainProject.airBnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventorySVC {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotel(HotelSearchDto HotelSearchDto);
}
