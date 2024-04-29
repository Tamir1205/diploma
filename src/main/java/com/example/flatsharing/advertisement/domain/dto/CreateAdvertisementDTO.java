package com.example.flatsharing.advertisement.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdvertisementDTO {
    @NotEmpty
    private String title;
    private String content;
    private List<byte[]> photos;
    @NotEmpty
    private String city;
    @NotEmpty
    private String address;
    @NotNull
    private BigDecimal price;
    @NotEmpty
    private String type;
    @NotNull
    private Integer numberOfRooms;
    private List<String> conditions;
    private Boolean isPromoted;
}
