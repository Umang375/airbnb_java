package com.mainProject.airBnb.controller;


import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.dto.HotelSearchDto;
import com.mainProject.airBnb.entity.Hotel;
import com.mainProject.airBnb.service.InventorySVC;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowserController {

    private final InventorySVC is;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDTO>> searchHotel(@RequestBody HotelSearchDto Hotelsearch){
        Page<HotelDTO> page = is.searchHotels(hotelSearch);


        return page;
    }
}
