package com.mainProject.airBnb.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingReq {
    private Long hotelId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer roomsCount;
}
