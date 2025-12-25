package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.HotelDTO;

public interface HotelSVC {

    HotelDTO createNewHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long Id);

    Void deleteHotelById(Long Id);

    HotelDTO updateHotelDTO(Long Id, HotelDTO hotelDTO);

    void activateHotel(Long Id);

}
