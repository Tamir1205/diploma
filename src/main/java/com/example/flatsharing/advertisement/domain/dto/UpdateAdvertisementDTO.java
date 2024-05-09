package com.example.flatsharing.advertisement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdvertisementDTO {
    private String title;
    private String content;
    private List<String> photos;
    private String city;
    private String address;
    private String type;
    private BigDecimal price;
    private Integer numberOfRooms;
    private List<String> conditions;
}
