package com.lachesisss.boards_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.boards_service.model.Boards;

@Repository
public interface BoardsRepository extends MongoRepository<Boards, String> {

}
