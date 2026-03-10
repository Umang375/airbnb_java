package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.dto.HotelInfoDto;
import org.jspecify.annotations.Nullable;

public interface HotelSVC {

    HotelDTO createNewHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long Id);

    HotelInfoDto getHotelInfoById(Long hotelId);

    void deleteHotelById(Long Id);

    HotelDTO updateHotelDTO(Long Id, HotelDTO hotelDTO);

    void activateHotel(Long Id);

    HotelInfoDto getHotelInfoById();
}
