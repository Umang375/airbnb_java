package com.mainProject.airBnb.dto;

import com.mainProject.airBnb.entity.User;
import com.mainProject.airBnb.entity.emuns.Gender;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}