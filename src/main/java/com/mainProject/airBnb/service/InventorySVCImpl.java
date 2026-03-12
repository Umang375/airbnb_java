package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.dto.HotelSearchDto;
import com.mainProject.airBnb.entity.Hotel;
import com.mainProject.airBnb.entity.Inventory;
import com.mainProject.airBnb.entity.Room;
import com.mainProject.airBnb.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventorySVCImpl implements InventorySVC {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for (; !today.isAfter(endDate); today=today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDTO> searchHotel(HotelSearchDto hotelSearchDto){
        Pageable pageable = PageRequest.of(hotelSearchDto.getPage(), hotelSearchDto.getSize());
        long dateCount = ChronoUnit.DAYS.between(hotelSearchDto.getStartDate(), hotelSearchDto.getStartDate()) + 1 ;
        Page<Hotel> hotels =  inventoryRepository.findHotelWithAvailableInventory(
                hotelSearchDto.getCity(),
                hotelSearchDto.getStartDate(),
                hotelSearchDto.getEndDate(),
                hotelSearchDto.getRoomCount(),
                dateCount,
                pageable
        );

        return hotels.map((ele)-> modelMapper.map(ele, HotelDTO.class));
    }
}
