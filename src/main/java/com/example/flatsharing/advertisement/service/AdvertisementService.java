package com.example.flatsharing.advertisement.service;

import com.example.flatsharing.advertisement.domain.dto.CreateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.dto.UpdateAdvertisementDTO;
import com.example.flatsharing.advertisement.domain.mapper.AdvertisementMapper;
import com.example.flatsharing.advertisement.domain.model.Advertisement;
import com.example.flatsharing.advertisement.repository.AdvertisementRepository;
import com.example.flatsharing.user.domain.model.User;
import com.example.flatsharing.user.service.UserService;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final UserService userService;
    private final AdvertisementMapper advertisementMapper;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public Advertisement create(CreateAdvertisementDTO dto, String userId) {
        Advertisement advertisement = advertisementMapper.toAdvertisement(dto);
        advertisement.setAuthorId(userId);
        save(advertisement);
        User user = userService.getById(userId);
        if (user.getAdvertisementIds() != null && !user.getAdvertisementIds().isEmpty()) {
            user.getAdvertisementIds().add(advertisement.getId());
        } else {
            List<String> advertisementIds = new ArrayList<>();
            advertisementIds.add(advertisement.getId());
            user.setAdvertisementIds(advertisementIds);
        }
        userService.save(user);
        return advertisement;
    }

    @Transactional
    public Boolean like(String id, String userId) {
        Advertisement advertisement = findById(id);
        User user = userService.getById(userId);
        if (user.getLikedAdvertisementIds() != null && !user.getLikedAdvertisementIds().isEmpty()) {
            user.getLikedAdvertisementIds().add(advertisement.getId());
        } else {
            List<String> advertisementIds = new ArrayList<>();
            advertisementIds.add(advertisement.getId());
            user.setLikedAdvertisementIds(advertisementIds);
        }
        userService.save(user);
        return true;
    }

    @Transactional
    public Boolean dislike(String id, String userId) {
        Advertisement advertisement = findById(id);
        User user = userService.getById(userId);
        if (user.getLikedAdvertisementIds() != null && !user.getLikedAdvertisementIds().isEmpty()) {
            user.getLikedAdvertisementIds().remove(advertisement.getId());
        }
        userService.save(user);
        return true;
    }

    public List<Advertisement> getFavourite(String userId) {
        User user = userService.getById(userId);
        if (user.getLikedAdvertisementIds() != null && !user.getLikedAdvertisementIds().isEmpty()) {
            List<String> likedAdvertisements = user.getLikedAdvertisementIds();
            return advertisementRepository.findAllById(likedAdvertisements);
        } else {
            return new ArrayList<>();
        }
    }

    public List<Advertisement> getByUserId(String userId) {
        User user = userService.getById(userId);
        if (user.getAdvertisementIds() != null && !user.getAdvertisementIds().isEmpty()) {
            List<String> advertisementIds = user.getAdvertisementIds();
            return advertisementRepository.findAllById(advertisementIds);
        } else {
            return new ArrayList<>();
        }
    }

    public Page<Advertisement> paginate(Pageable pageable,
                                        List<Integer> numberOfRooms,
                                        BigDecimal priceGreaterThan,
                                        BigDecimal priceLessThan,
                                        List<String> interests,
                                        String gender,
                                        Integer ageGreaterThan,
                                        Integer ageLessThan) {
        Query query = new Query();
        Query userQuery = new Query();
        if (numberOfRooms != null) {
            query.addCriteria(Criteria.where("numberOfRooms").in(numberOfRooms));
        }
        if (priceGreaterThan != null) {
            query.addCriteria(Criteria.where("price").gt(priceGreaterThan));
        }
        if (priceLessThan != null) {
            query.addCriteria(Criteria.where("price").lt(priceLessThan));
        }
        if (interests != null) {
            userQuery.addCriteria(Criteria.where("interests").in(interests));
        }
        if (gender != null) {
            userQuery.addCriteria(Criteria.where("gender").is(gender));
        }
        if (ageGreaterThan != null) {
            userQuery.addCriteria(Criteria.where("age").gt(ageGreaterThan));
        }
        if (ageLessThan != null) {
            userQuery.addCriteria(Criteria.where("age").lt(ageLessThan));
        }
        List<User> users = mongoTemplate.find(userQuery, User.class);
        if (!users.isEmpty()) {
            List<String> advertisementIds = users.stream()
                    .filter(user -> user.getAdvertisementIds() != null)
                    .flatMap(user -> user.getAdvertisementIds().stream())
                    .distinct()
                    .toList();
            query.addCriteria(Criteria.where("_id").in(advertisementIds));
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
        return save(advertisement);
    }

    public Advertisement findById(String id) {
        return advertisementRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("Advertisement with id " + id + " not found"));
    }

    public void promote(String id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Advertisement with id " + id + " not found"));
        advertisement.setIsPromoted(true);
        advertisementRepository.save(advertisement);
    }

    @Transactional
    public void delete(String id) {
        advertisementRepository.deleteById(id);
    }

    @Transactional
    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }
}
