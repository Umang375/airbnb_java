package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.RoomDTO;

import java.util.List;

public interface RoomSVC {

    RoomDTO createNewRoom(Long hotelId, RoomDTO roomDto);

    List<RoomDTO> getAllRoomsInHotel(Long hotelId);

    RoomDTO getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

}
