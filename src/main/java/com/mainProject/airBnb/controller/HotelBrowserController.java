package com.mainProject.airBnb.controller;


import com.mainProject.airBnb.dto.HotelDTO;
import com.mainProject.airBnb.dto.HotelInfoDto;
import com.mainProject.airBnb.dto.HotelSearchDto;
import com.mainProject.airBnb.entity.Hotel;
import com.mainProject.airBnb.service.HotelSVC;
import com.mainProject.airBnb.service.InventorySVC;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelBrowserController {

    private final InventorySVC is;
    private final HotelSVC hs;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDTO>> searchHotel(@RequestBody HotelSearchDto Hotelsearch){
        Page<HotelDTO> page = is.searchHotel(Hotelsearch);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){

        return ResponseEntity.ok(hs.getHotelInfoById(hotelId));
    }
}
