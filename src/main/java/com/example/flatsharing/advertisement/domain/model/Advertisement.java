package com.example.flatsharing.advertisement.domain.model;

import com.example.flatsharing.user.domain.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Document("advertisements")
@NoArgsConstructor
@AllArgsConstructor
public class Advertisement {
    @Id
    private String id;
    private String title;
    private String content;
    private List<Binary> photos;
    private City city;
    private String street;
    private BigDecimal price;
    private Integer numberOfRooms;
    private String conditions;
    private Boolean isPromoted;
//    private DateTime createdAt;
//    private DateTime updatedAt;
}
