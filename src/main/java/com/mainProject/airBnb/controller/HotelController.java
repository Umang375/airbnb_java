package com.mainProject.airBnb.controller;


import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.service.HotelSVC;
import com.mainProject.airBnb.service.HotelSVCImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelSVC hs;

    @PostMapping
    public ResponseEntity<HotelDTO> createNewHotel(@RequestBody HotelDTO hoteldto){
        log.info("attempting t create a new hotel with name: " + hoteldto.getName());
        HotelDTO hotel = hs.createNewHotel(hoteldto);
        return new ResponseEntity<>(hotel,HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId){
        HotelDTO hotelDTO = hs.getHotelById(hotelId);
        return ResponseEntity.ok(hotelDTO);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDTO> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDTO hoteldto){
        HotelDTO hotelDTO = hs.updateHotelDTO(hotelId, hoteldto);
        return ResponseEntity.ok(hotelDTO);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId){
         hs.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }
}
