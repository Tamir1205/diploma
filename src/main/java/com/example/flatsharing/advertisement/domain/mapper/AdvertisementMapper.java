package com.example.flatsharing.advertisement.domain.mapper;

import com.example.flatsharing.advertisement.domain.dto.AdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.CreateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.UpdateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.model.Advertisement;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AdvertisementMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    Advertisement toAdvertisement(CreateAdvertisementDTO dto);

    @Mapping(target = "id", ignore = true)
    void mapValues(UpdateAdvertisementDTO dto, @MappingTarget Advertisement advertisement);

    AdvertisementDTO toDTO(Advertisement advertisement);

    List<AdvertisementDTO> toDTO(List<Advertisement> advertisements);

}
