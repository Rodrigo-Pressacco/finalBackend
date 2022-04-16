package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface catalogRepository extends MongoRepository<Catalog, String> {
}
