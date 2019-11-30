package com.lachesisss.tasks_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lachesisss.tasks_service.model.Lists;
import com.lachesisss.tasks_service.repository.ListsRepository;

@Service
public class ListsService {

	private static ListsService instance = null;

	@Autowired
	private ListsRepository listsRepository;

	public static ListsService getInstance() {
		if (instance == null) {
			instance = new ListsService();
		}

		return instance;
	}

	public Lists createList(Lists list) {
		if (list == null) {
			Lists newList = new Lists();
			return saveList(newList) ? newList : null;
		}

		return saveList(list) ? list : null;
	}

	public List<Lists> getAllLists() {
		return listsRepository.findAll();
	}

	public Optional<Lists> getListById(String id) {
		return listsRepository.findById(id);
	}

	public Optional<List<Lists>> getListsByIdBoard(String idBoard) {
		return listsRepository.findByIdBoard(idBoard);
	}

	public Lists updateList(Lists newList, Lists list) {
		if (list == null) {
			return null;
		}

		newList.setIdBoard(list.getIdBoard());
		return saveList(newList) ? newList : null;
	}

	public boolean deleteAllLists() {
		try {
			listsRepository.deleteAll();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean deleteListById(String id) {
		try {
			listsRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private boolean saveList(Lists list) {
		if (list == null) {
			return false;
		}

		listsRepository.save(list);
		return true;
	}

}
