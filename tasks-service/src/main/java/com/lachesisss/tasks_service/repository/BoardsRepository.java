package com.lachesisss.tasks_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.tasks_service.model.Boards;

@Repository
public interface BoardsRepository extends MongoRepository<Boards, String> {

}
