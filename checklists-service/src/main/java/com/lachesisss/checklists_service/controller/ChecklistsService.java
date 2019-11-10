package com.lachesisss.checklists_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lachesisss.checklists_service.model.CheckItems;
import com.lachesisss.checklists_service.model.Checklists;
import com.lachesisss.checklists_service.repository.CheckItemsRepository;
import com.lachesisss.checklists_service.repository.ChecklistsRepository;

@Service
public class ChecklistsService {

	private static ChecklistsService instance = null;

	@Autowired
	private ChecklistsRepository checklistsRepository;

	@Autowired
	private CheckItemsRepository checkItemsRepository;

	public static ChecklistsService getInstance() {
		if (instance == null) {
			instance = new ChecklistsService();
		}

		return instance;
	}

	public Checklists createChecklist(Checklists checklist) {
		if (checklist == null) {
			Checklists newChecklist = new Checklists();
			return newChecklist;
		}

		return checklist;
	}

	public CheckItems createCheckItem(CheckItems checkItem, String idChecklist) {
		if (checkItem == null) {
			CheckItems newCheckItem = new CheckItems();
			newCheckItem.setIdChecklist(idChecklist);
			return newCheckItem;
		}

		checkItem.setIdChecklist(idChecklist);
		return checkItem;
	}

	public List<Checklists> getAllChecklists() {
		return checklistsRepository.findAll();
	}

	public List<CheckItems> getAllCheckItems() {
		return checkItemsRepository.findAll();
	}

	public Optional<Checklists> getChecklistById(String id) {
		return checklistsRepository.findById(id);
	}

	public Optional<CheckItems> getCheckItemByIdAndIdChecklist(String id, String idChecklist) {
		return checkItemsRepository.findByIdAndIdChecklist(id, idChecklist);
	}

	public Optional<CheckItems> getCheckItemByIdChecklist(String idChecklist) {
		return checkItemsRepository.findByIdChecklist(idChecklist);
	}

	public Checklists updateChecklist(Checklists newChecklist, Checklists checklist) {
		if (checklist == null) {
			return null;
		}

		newChecklist.setName(checklist.getName());
		newChecklist.setDescription(checklist.getDescription());
		newChecklist.setIdCard(checklist.getIdCard());
		newChecklist.setIdBoard(checklist.getIdBoard());
		return newChecklist;
	}

	public CheckItems updateCheckItem(CheckItems newCheckItem, CheckItems checkItem) {
		if (checkItem == null) {
			return null;
		}

		newCheckItem.setName(checkItem.getName());
		newCheckItem.setState(checkItem.getState());
		newCheckItem.setIdChecklist(checkItem.getIdChecklist());
		return newCheckItem;
	}

	public void deleteAllChecklists() {
		for (Checklists checklist : checklistsRepository.findAll()) {
			checkItemsRepository.deleteByIdChecklist(checklist.getId());
		}
		checklistsRepository.deleteAll();
	}

	public void deleteAllCheckItems() {
		checkItemsRepository.deleteAll();
	}

	public void deleteChecklistById(String id) {
		checkItemsRepository.deleteByIdChecklist(id);
		checklistsRepository.deleteById(id);
	}

	public void deleteCheckItemById(String id) {
		checkItemsRepository.deleteById(id);
	}

	public void deleteCheckItemById(String id, String idChecklist) {
		checkItemsRepository.deleteByIdAndIdChecklist(id, idChecklist);
	}

	public void saveChecklist(Checklists checklist) {
		checklistsRepository.save(checklist);
	}

	public void saveCheckItem(CheckItems checkItem) {
		checkItemsRepository.save(checkItem);
	}

}
