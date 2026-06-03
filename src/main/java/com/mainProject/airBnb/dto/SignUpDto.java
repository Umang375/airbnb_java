package com.mainProject.airBnb.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
}
