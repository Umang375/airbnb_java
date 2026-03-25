package com.mainProject.airBnb.controller;

import com.mainProject.airBnb.dto.BookingDto;
import com.mainProject.airBnb.dto.BookingReq;
import com.mainProject.airBnb.dto.GuestDto;
import com.mainProject.airBnb.service.BookingSVC;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {


    private final BookingSVC bs;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingReq br){

        return ResponseEntity.ok(bs.initialiseBooking(br));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId, @RequestBody List<GuestDto> guestsList){
        return ResponseEntity.ok(bs.addGuests(bookingId, guestsList));
    }
}
