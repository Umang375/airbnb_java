package com.mainProject.airBnb.dto;

import lombok.Data;

import java.util.List;

@Data
public class HotelInfoDto {

    private HotelDTO htd;
    private List<RoomDTO> rooms;
}
