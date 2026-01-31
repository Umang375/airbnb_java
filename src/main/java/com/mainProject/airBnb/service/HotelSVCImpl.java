package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.entity.Hotel;
import com.mainProject.airBnb.entity.Room;
import com.mainProject.airBnb.exception.ResourceNotFoundException;
import com.mainProject.airBnb.repo.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelSVCImpl implements HotelSVC{
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventorySVC inventorySVC;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDTO){
        log.info("Created a new hotel with name : {} ", hotelDTO.getName());
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);
        hotel.setIsActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with name : {} ", hotelDTO.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    };

    @Override
    public HotelDTO getHotelById(Long Id){
        log.info("Getting the hotel with ID : {}", Id);
        Hotel hotel = hotelRepository
                .findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with the ID : " + Id));

        return modelMapper.map(hotel, HotelDTO.class);
    };

    @Override
    public HotelDTO updateHotelDTO(Long Id, HotelDTO hotelDTO){
        log.info("Getting the hotel with ID : {}", Id);
        Hotel hotel = hotelRepository
                .findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with the ID : " + Id));

        modelMapper.map(hotelDTO, hotel);
        hotel.setId(Id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void activateHotel(Long Id) {
        log.info("Getting the hotel with ID : {}", Id);
        Hotel hotel = hotelRepository
                .findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with the ID : " + Id));
        hotel.setIsActive(true);
        //assuming I did it only once

        for (Room room : hotel.getRooms()){
            inventorySVC.initializeRoomForAYear(room);
        }
    }

    @Override
    @Transactional
    public void deleteHotelById(Long Id){
        Hotel hotel = hotelRepository
                .findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with the ID : " + Id));

        for (Room room : hotel.getRooms()) {
            inventorySVC.deleteFutureInventories(room);
        }
    }
}
