package com.example.flatsharing.advertisement.domain.mapper;

import com.example.flatsharing.advertisement.domain.dto.AdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.CreateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.UpdateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.model.Advertisement;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface AdvertisementMapper {
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
//    @Mapping(target = "createdAt", expression = "java(org.joda.time.DateTime.now())")
//    @Mapping(target = "updatedAt", expression = "java(org.joda.time.DateTime.now())")
    Advertisement toAdvertisement(CreateAdvertisementDTO dto);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdAt", ignore = true)
//    @Mapping(target = "updatedAt", expression = "java(org.joda.time.DateTime.now())")
    void mapValues(UpdateAdvertisementDTO dto, @MappingTarget Advertisement advertisement);

    AdvertisementDTO toDTO(Advertisement advertisement);
}
