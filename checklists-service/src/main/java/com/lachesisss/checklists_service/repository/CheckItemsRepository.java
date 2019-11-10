package com.lachesisss.checklists_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lachesisss.checklists_service.model.CheckItems;

@Repository
public interface CheckItemsRepository extends MongoRepository<CheckItems, String> {

	Optional<CheckItems> findByIdAndIdChecklist(String id, String idChecklist);

	Optional<CheckItems> findByIdChecklist(String idChecklist);

	void deleteByIdAndIdChecklist(String id, String idChecklist);

	void deleteByIdChecklist(String idChecklist);

}
