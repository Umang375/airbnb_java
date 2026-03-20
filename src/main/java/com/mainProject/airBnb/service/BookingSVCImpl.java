package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.BookingDto;
import com.mainProject.airBnb.dto.BookingReq;
import com.mainProject.airBnb.entity.Booking;
import com.mainProject.airBnb.entity.Hotel;
import com.mainProject.airBnb.entity.Inventory;
import com.mainProject.airBnb.entity.Room;
import com.mainProject.airBnb.entity.emuns.BookingStatus;
import com.mainProject.airBnb.exception.ResourceNotFoundException;
import com.mainProject.airBnb.repo.BookingRepo;
import com.mainProject.airBnb.repo.HotelRepository;
import com.mainProject.airBnb.repo.InventoryRepository;
import com.mainProject.airBnb.repo.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private final InventoryRepository ip;

    @Override
    @Transactional
    public BookingDto initialiseBooking(BookingReq br) {

       log.info("initializing booking for hotel : {}, room: {}, date: {}-{}", br.getHotelId(), br.getRoomId(), br.getCheckInDate(), br.getCheckOutDate());
        Hotel hotel = hrp.findById(br.getHotelId()).orElseThrow(() ->
                new ResourceNotFoundException("Hotel not found"));

        Room room = rrp.findById(br.getRoomId()).orElseThrow(()->
                new ResourceNotFoundException("Room not found")
                );

        List<Inventory> inventoryList = ip.findAndLockAvailableInventory(
                room.getId(),
                br.getCheckInDate(),
                br.getCheckOutDate(),
                br.getRoomsCount()

        );

        long daysCount = ChronoUnit.DAYS.between(br.getCheckInDate(), br.getCheckOutDate()) + 1;
        if(inventoryList.size() != daysCount){
            throw new IllegalStateException("Room is not available anymore");
        }

        for(Inventory inven : inventoryList){
            inven.setBookedCount(inven.getBookedCount() + br.getRoomsCount());
        }
        ip.saveAll(inventoryList);
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
}
