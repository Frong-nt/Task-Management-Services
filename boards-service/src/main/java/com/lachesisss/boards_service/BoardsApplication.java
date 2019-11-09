package com.lachesisss.boards_service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lachesisss.boards_service.controller.BoardsController;
import com.lachesisss.boards_service.models.Boards;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class BoardsApplication {

	@Autowired
	private BoardsController controller = BoardsController.getInstance();

	public static void main(String[] args) {
		SpringApplication.run(BoardsApplication.class, args);
	}

	@GetMapping("/boards")
	public ResponseEntity<List<Boards>> index() {
		List<Boards> board = controller.getAllBoards();
		if (board == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Boards>>(board, HttpStatus.OK);
	}

	@GetMapping("/boards/{id}")
	public ResponseEntity<Boards> get(@PathVariable String id) {
		Boards board;
		try {
			board = controller.getBoardById(id).get();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Boards>(board, HttpStatus.OK);
	}

	@PutMapping("/boards/{id}")
	public ResponseEntity<Boards> updateBoard(@PathVariable String id, @RequestParam Map<String, String> queryMap) {
		Boards board;
		try {
			board = controller.getBoardById(id).get();
			controller.updateBoard(board, queryMap);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Boards>(board, HttpStatus.OK);
	}

	@PostMapping("/boards")
	public ResponseEntity<Boards> createBoard(@Valid @RequestBody(required = false) Boards board) {
		Boards newBoard;
		try {
			newBoard = controller.createBoard(board);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (newBoard == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boards>(newBoard, HttpStatus.OK);
	}
	
	@DeleteMapping("/boards/{id}")
	public ResponseEntity<Boards> deleteBoard(@PathVariable String id) {
		Boards board;
		try {
			board = controller.getBoardById(id).get();
			controller.deleteBoardById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		if (board == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boards>(board, HttpStatus.OK);

	}
}
