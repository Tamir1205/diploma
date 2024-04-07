package com.example.flatsharing.advertisement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedAdvertisementDTO {
    private List<AdvertisementDTO> content;
    private Long totalItems;
    private int totalPages;
    private int pageNumber;
}
