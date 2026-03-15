package com.mainProject.airBnb.controller;

import com.mainProject.airBnb.dto.BookingDto;
import com.mainProject.airBnb.dto.BookingReq;
import com.mainProject.airBnb.service.BookingSVC;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {


    private final BookingSVC bs;

    @PostMapping
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingReq br){

        return ResponseEntity.ok(bs.initialiseBooking(br));
    }
}
