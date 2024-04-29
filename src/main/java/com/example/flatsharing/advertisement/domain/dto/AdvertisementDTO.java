package com.example.flatsharing.advertisement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDTO {
    private String id;
    private String title;
    private String content;
    private List<byte[]> photos;
    private String city;
    private String address;
    private String type;
    private BigDecimal price;
    private Integer numberOfRooms;
    private List<String> conditions;
    private Boolean isPromoted;
    private String authorId;
}
