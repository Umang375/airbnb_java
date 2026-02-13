package com.mainProject.airBnb.repo;

import com.mainProject.airBnb.entity.Inventory;
import com.mainProject.airBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByRoom(Room room);
}
