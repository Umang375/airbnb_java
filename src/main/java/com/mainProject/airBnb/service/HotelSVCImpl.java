package com.mainProject.airBnb.service;

import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.entity.Hotel;
import com.mainProject.airBnb.exception.ResourceNotFoundException;
import com.mainProject.airBnb.repo.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelSVCImpl implements HotelSVC{
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

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
    public void activateHotel(Long Id) {
        log.info("Getting the hotel with ID : {}", Id);
        Hotel hotel = hotelRepository
                .findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with the ID : " + Id));
        hotel.setIsActive(true);
        //TODO : create inventory for all the rooms for the Hotel
    }

    @Override
    public Void deleteHotelById(Long Id){
        boolean exists = hotelRepository.existsById(Id);
        if(!exists) throw new ResourceNotFoundException("Hotel not found with the ID : " + Id);

        //TODO: delete the future inventories for this hotel
        return null;
    }
}
