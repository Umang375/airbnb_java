package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.BookingDto;
import com.mainProject.airBnb.dto.BookingReq;

public interface BookingSVC {

    BookingDto initialiseBooking(BookingReq br);
}
