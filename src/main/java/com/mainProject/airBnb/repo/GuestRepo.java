package com.mainProject.airBnb.repo;
import com.mainProject.airBnb.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepo  extends JpaRepository<Guest, Long> {
}
