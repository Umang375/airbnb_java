package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.BookingDto;
import com.mainProject.airBnb.dto.BookingReq;
import com.mainProject.airBnb.dto.GuestDto;

import java.util.List;

public interface BookingSVC {

    BookingDto initialiseBooking(BookingReq br);

    BookingDto addGuests(Long bookingId, List<GuestDto> gl) ;
}
