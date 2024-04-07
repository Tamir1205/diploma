package com.example.flatsharing.advertisement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdvertisementDTO {
    //    private String title;
    private String description;
    //    private List<Binary> photos;
//    private String city;
    private String address;
    private BigDecimal price;
    private Integer numberOfRooms;
    private String conditions;
//    private DateTime createdAt;
//    private DateTime updatedAt;
}
