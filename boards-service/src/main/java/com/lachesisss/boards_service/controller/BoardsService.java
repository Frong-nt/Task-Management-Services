package com.lachesisss.boards_service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lachesisss.boards_service.model.Boards;
import com.lachesisss.boards_service.repository.BoardsRepository;

@Service
public class BoardsService {

	private static BoardsService instance = null;

	@Autowired
	private BoardsRepository boardsRepository;

	public static BoardsService getInstance() {
		if (instance == null) {
			instance = new BoardsService();
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

	public Boards updateBoard(Boards newBoard, Boards board) {
		if (newBoard == null || board == null) {
			return null;
		}

		newBoard.setName(board.getName());
		newBoard.setDescription(board.getDescription());
		newBoard.setClosed(board.isClosed());

		return saveBoard(board) ? board : null;
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
