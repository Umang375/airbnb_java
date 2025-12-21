package com.mainProject.airBnb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
//{
//        "name": "Hotel Lotus",
//        "city": "Delhi",
//        "contactInfo": {
//        "address": "Central Delhi",
//        "email": "hello@lotushotels.com",
//        "phoneNumber": "9829391929",
//        "location": "74.2381, 28.43124"
//
//        "amenities": ["AC", "Lake View", "Pool Area"],
//        "photos": ["http://via.placeholder.com/50"]
//}

