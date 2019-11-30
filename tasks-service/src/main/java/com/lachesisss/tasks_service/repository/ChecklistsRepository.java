package com.lachesisss.tasks_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.tasks_service.model.Checklists;

@Repository
public interface ChecklistsRepository extends MongoRepository<Checklists, String> {

	Optional<List<Checklists>> findByIdCard(String idCard);

}
