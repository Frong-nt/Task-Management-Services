package com.lachesisss.checklists_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.checklists_service.model.Checklists;

@Repository
public interface ChecklistsRepository extends MongoRepository<Checklists, String> {

}
