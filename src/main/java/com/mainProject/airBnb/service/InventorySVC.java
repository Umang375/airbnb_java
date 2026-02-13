package com.mainProject.airBnb.service;


import com.mainProject.airBnb.entity.Room;

public interface InventorySVC {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);
}
