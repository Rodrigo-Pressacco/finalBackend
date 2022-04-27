package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface catalogRepository extends MongoRepository<Catalog, String> {
    Optional<Catalog> findByGenre(String genre);
    void deleteByGenre(String genre);
}
