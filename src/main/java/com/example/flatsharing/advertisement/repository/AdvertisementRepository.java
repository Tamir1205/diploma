package com.example.flatsharing.advertisement.repository;

import com.example.flatsharing.advertisement.domain.model.Advertisement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends MongoRepository<Advertisement, String> {
}
