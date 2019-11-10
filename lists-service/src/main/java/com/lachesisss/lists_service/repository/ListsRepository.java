package com.lachesisss.lists_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.lists_service.model.Lists;

@Repository
public interface ListsRepository extends MongoRepository<Lists, String> {

	Optional<List<Lists>> findByIdBoard(String idBoard);

}
