package com.example.flatsharing.advertisement.domain.dto;

import com.example.flatsharing.user.domain.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertisementDTO {
//    private String title;
    private String description;
//    private List<Binary> photos;
//    private String city;
    private String address;
    private BigDecimal price;
    private Integer numberOfRooms;
    private String conditions;
    private Boolean isPromoted;
//    private DateTime createdAt;
//    private DateTime updatedAt;
}
