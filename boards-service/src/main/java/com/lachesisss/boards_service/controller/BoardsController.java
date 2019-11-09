package com.lachesisss.boards_service.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lachesisss.boards_service.models.Boards;

@Service
public class BoardsController {

	private static BoardsController instance = null;

	@Autowired
	private BoardsRepository boardsRepository;

	public static BoardsController getInstance() {
		if (instance == null) {
			instance = new BoardsController();
		}

		return instance;
	}

	public Boards createBoard(@Valid Boards board) {
		if (board == null) {
			Boards newBoard = new Boards();
			return saveBoard(newBoard) ? newBoard : null;
		}

		return saveBoard(board) ? board : null;
	}

	public List<Boards> getAllBoards() {
		return boardsRepository.findAll();
	}

	public Optional<Boards> getBoardById(String id) {
		return boardsRepository.findById(id);
	}

	public Boards updateBoard(Boards boards, Map<String, String> queryMap) {
		if (boards == null || queryMap.isEmpty()) {
			return null;
		}

		String name = queryMap.get("name");
		if (name != null) {
			boards.setName(name);
		}

		String description = (queryMap.get("description"));
		if (description != null) {
			boards.setDescription(description);
		}

		try {
			boolean closed = Boolean.parseBoolean(queryMap.get("closed"));
			boards.setClosed(closed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String idOrganization = (queryMap.get("idOrganization"));
		if (idOrganization != null) {
			boards.setIdOrganization(idOrganization);
		}

		try {
			boolean starred = Boolean.parseBoolean(queryMap.get("closed"));
			boards.setStarred(starred);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return saveBoard(boards) ? boards : null;
	}

	public boolean deleteBoardById(String id) {
		try {
			boardsRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private boolean saveBoard(Boards board) {
		if (board == null) {
			return false;
		}

		boardsRepository.save(board);
		return true;
	}

}
