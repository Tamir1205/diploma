package com.example.flatsharing.advertisement.controller;

import com.example.flatsharing.advertisement.domain.dto.AdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.CreateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.PaginatedAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.UpdateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.mapper.AdvertisementMapper;
import com.example.flatsharing.advertisement.domain.model.Advertisement;
import com.example.flatsharing.advertisement.service.AdvertisementService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/advertisements")
@RequiredArgsConstructor
@Validated
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private final AdvertisementMapper mapper;

    @PostMapping
    public AdvertisementDTO create(@Valid @RequestBody CreateAdvertisementDTO dto, @RequestHeader String userId) {
        return mapper.toDTO(advertisementService.create(dto, userId));
    }

    @PostMapping("/{id}/like")
    public Boolean like(@PathVariable String id, @RequestHeader String userId) {
        return advertisementService.like(id, userId);
    }

    @PostMapping("/{id}/dislike")
    public Boolean dislike(@PathVariable String id, @RequestHeader String userId) {
        return advertisementService.dislike(id, userId);
    }

    @GetMapping("/user/favourite")
    public List<AdvertisementDTO> getFavourite(@RequestHeader String userId) {
        return mapper.toDTO(advertisementService.getFavourite(userId));
    }

    @GetMapping("/user")
    public List<AdvertisementDTO> getByUserId(@RequestHeader String userId) {
        return mapper.toDTO(advertisementService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public AdvertisementDTO updateById(@RequestBody UpdateAdvertisementDTO dto, @PathVariable String id) {
        return mapper.toDTO(advertisementService.update(dto, id));
    }

    @GetMapping("/{id}")
    public AdvertisementDTO getById(@PathVariable String id) {
        return mapper.toDTO(advertisementService.findById(id));
    }

    @GetMapping
    @PageableAsQueryParam
    public PaginatedAdvertisementDTO paginate(@Parameter(hidden = true) Pageable pageable,
                                              @RequestParam(required = false) List<Integer> numberOfRooms,
                                              @RequestParam(required = false) List<String> interests,
                                              @RequestParam(required = false) String gender,
                                              @RequestParam(required = false) Integer ageGreaterThan,
                                              @RequestParam(required = false) Integer ageLessThan,
                                              @RequestParam(required = false) BigDecimal priceGreaterThan,
                                              @RequestParam(required = false) BigDecimal priceLessThan) {
        Page<Advertisement> page = advertisementService.paginate(pageable, numberOfRooms, priceLessThan, priceGreaterThan,
                interests, gender, ageGreaterThan, ageLessThan);
        return new PaginatedAdvertisementDTO(page.getContent().stream().map(mapper::toDTO).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages(), page.getNumber());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        advertisementService.delete(id);
    }
}
