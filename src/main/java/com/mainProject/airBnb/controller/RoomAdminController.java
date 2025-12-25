package com.mainProject.airBnb.controller;

import com.mainProject.airBnb.dto.RoomDTO;
import com.mainProject.airBnb.service.RoomSVC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {
    private final RoomSVC rs;

    @PostMapping
    public ResponseEntity<RoomDTO> createNewRoom(@PathVariable Long hoteId, @RequestBody RoomDTO roomDto){
        RoomDTO room = rs.createNewRoom(hoteId, roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRoomInHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok(rs.getAllRoomsInHotel(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById (@PathVariable Long hotelId, @PathVariable Long roomId){
        return ResponseEntity.ok(rs.getRoomById(roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<RoomDTO> deleteRoomById(@PathVariable Long hotelId, @PathVariable Long roomId) {
        rs.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
