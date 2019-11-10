package com.lachesisss.cards_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.cards_service.model.Cards;

@Repository
public interface CardsRepository extends MongoRepository<Cards, String> {

	Optional<List<Cards>> findByIdBoard(String idBoard);

	Optional<List<Cards>> findByIdList(String idList);

}
