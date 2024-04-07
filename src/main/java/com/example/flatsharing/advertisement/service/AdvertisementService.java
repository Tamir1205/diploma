package com.example.flatsharing.advertisement.service;

import com.example.flatsharing.advertisement.domain.dto.CreateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.UpdateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.exception.AdvertisementNotFoundException;
import com.example.flatsharing.advertisement.domain.mapper.AdvertisementMapper;
import com.example.flatsharing.advertisement.domain.model.Advertisement;
import com.example.flatsharing.advertisement.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final AdvertisementMapper advertisementMapper;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public Advertisement create(CreateAdvertisementDTO dto) {
        Advertisement advertisement = advertisementMapper.toAdvertisement(dto);
        advertisementRepository.save(advertisement);
        return advertisement;
    }

    @Transactional
    public Page<Advertisement> paginate(Pageable pageable,
                                        Integer numberOfRooms,
                                        BigDecimal priceGreaterThan,
                                        BigDecimal priceLessThan) {
        Query query = new Query();
        if (numberOfRooms != null) {
            query.addCriteria(Criteria.where("numberOfRooms").is(numberOfRooms));
        }
        if (priceGreaterThan != null) {
            query.addCriteria(Criteria.where("price").gt(priceGreaterThan));
        }
        if (priceLessThan != null) {
            query.addCriteria(Criteria.where("price").lt(priceLessThan));
        }
        List<Advertisement> advertisements = mongoTemplate.find(query, Advertisement.class);
        advertisements.sort(Comparator.comparing(Advertisement::getIsPromoted).reversed());
        return PageableExecutionUtils.getPage(
                advertisements,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Advertisement.class));

    }

    @Transactional
    public Advertisement update(UpdateAdvertisementDTO dto, String id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow();
        advertisementMapper.mapValues(dto, advertisement);
        advertisementRepository.save(advertisement);
        return advertisement;
    }

    public Advertisement findById(String id) {
        return advertisementRepository.findById(id).orElseThrow(() -> new AdvertisementNotFoundException("Advertisement with id" + id + "not found"));
    }

    public void promote(String id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new AdvertisementNotFoundException("Advertisement with id" + id + "not found"));
        advertisement.setIsPromoted(true);
        advertisementRepository.save(advertisement);
    }

    @Transactional
    public void delete(String id) {
        advertisementRepository.deleteById(id);
    }
}
