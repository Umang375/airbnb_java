package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.BookingDto;
import com.mainProject.airBnb.dto.BookingReq;
import com.mainProject.airBnb.dto.GuestDto;
import com.mainProject.airBnb.entity.*;
import com.mainProject.airBnb.entity.emuns.BookingStatus;
import com.mainProject.airBnb.exception.ResourceNotFoundException;
import com.mainProject.airBnb.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingSVCImpl implements BookingSVC{

    private final ModelMapper modelMapper;
    private final BookingRepo brp;
    private final HotelRepository hrp;
    private final RoomRepository rrp;
    private final InventoryRepository irp;
    private final GuestRepo grp;

    @Override
    @Transactional
    public BookingDto initialiseBooking(BookingReq br) {

       log.info("initializing booking for hotel : {}, room: {}, date: {}-{}", br.getHotelId(), br.getRoomId(), br.getCheckInDate(), br.getCheckOutDate());
        Hotel hotel = hrp.findById(br.getHotelId()).orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found"));

        Room room = rrp.findById(br.getRoomId()).orElseThrow(()->
                new ResourceNotFoundException("Room not found")
                );

        List<Inventory> inventoryList = irp.findAndLockAvailableInventory(
                room.getId(),
                br.getCheckInDate(),
                br.getCheckOutDate(),
                br.getRoomsCount()

        );

        long daysCount = ChronoUnit.DAYS.between(br.getCheckInDate(), br.getCheckOutDate()) + 1;
        if(inventoryList.size() != daysCount){
            throw new IllegalStateException("Room is not available anymore");
        }

        for(Inventory inve : inventoryList){
            inve.setReservedCount(inve.getReservedCount() + br.getRoomsCount());
        }
        irp.saveAll(inventoryList);
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(br.getCheckInDate())
                .checkOutDate(br.getCheckOutDate())
                .user(getCurrentUser())
                .roomsCount(br.getRoomsCount())
                .amount(BigDecimal.TEN)
                .build();
        booking = (Booking) brp.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }


    @Override
    @Transactional
    public BookingDto addGuests(Long bookingId, List<GuestDto> gl)  {
        log.info("Adding guests for the following booking ID:", bookingId);

        Booking booking = brp.findById(bookingId).orElseThrow(() ->
                new ResourceNotFoundException("Booking not found with id: "+bookingId));

        if(hasBookingExpired(booking)){
            throw new IllegalStateException("Booking has already expired");
        }

        if(booking.getBookingStatus() != BookingStatus.RESERVED){
            throw new IllegalStateException("booking is not under reserved state");
        }

        for(GuestDto g : gl){
            Guest guest = modelMapper.map(g, Guest.class);
            guest.setUser(getCurrentUser());
            guest = grp.save(guest);
            booking.getGuests().add(guest);
        }

        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
        booking = brp.save(booking);

        return modelMapper.map(booking, BookingDto.class);
    }

    public boolean hasBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }

    private User getCurrentUser() {
        User user = new User();
        user.setId(1L);
        return user;
    }

}
