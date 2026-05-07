package com.mainProject.airBnb.repo;

import com.mainProject.airBnb.dto.HotelPriceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HotelMinPriceRepo extends JpaRepository <HotelMinPriceRepo, Long>{

    @Query("""
            SELECT com.mainProject.airBnb.dto.HotelPriceDto(i.hotel, AVG(i.price))
            FROM HotelMinPrice i 
            WHERE i.hotel.city = :city 
                AND i.date BETWEEN :startDate AND :endDate
                AND i.hotel.active = true
           GROUP BY i.hotel
           """)
    Page<HotelPriceDto> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );
}
