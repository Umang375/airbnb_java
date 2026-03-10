package com.mainProject.airBnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {

    private HotelDTO htd;
    private List<RoomDTO> rooms;
}
