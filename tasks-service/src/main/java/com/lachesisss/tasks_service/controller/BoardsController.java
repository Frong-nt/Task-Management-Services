package com.lachesisss.tasks_service.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lachesisss.tasks_service.model.Boards;
import com.lachesisss.tasks_service.service.BoardsService;

@RestController
public class BoardsController {

	@Autowired
	private BoardsService service = BoardsService.getInstance();

	@Autowired
	ObjectMapper objectMapper;

	@GetMapping(value = "/boards")
	public ResponseEntity<List<Boards>> index() {
		try {
			List<Boards> boards;
			boards = service.getAllBoards();
			return new ResponseEntity<List<Boards>>(boards, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/boards/{id}")
	public ResponseEntity<?> getBoardById(@PathVariable String id) {
		try {
			Boards board;
			board = service.getBoardById(id).get();
			return new ResponseEntity<Boards>(board, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/boards/{id}")
	public ResponseEntity<Boards> updateBoardById(@PathVariable String id, @Valid @RequestBody Boards board) {
		try {
			Boards newBoard;
			newBoard = service.getBoardById(id).get();
			newBoard = service.updateBoard(newBoard, board);
			if (newBoard == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Boards>(board, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/boards")
	public ResponseEntity<Boards> createBoard(@Valid @RequestBody(required = false) Boards board) {
		try {
			Boards newBoard;
			newBoard = service.createBoard(board);
			if (newBoard == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Boards>(newBoard, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/boards/{id}/lists")
	public ResponseEntity<?> createListByIdBoard(@PathVariable String id) {
		try {
			Boards board;
			board = service.getBoardById(id).get();

			final String uri = "https://azure-task-manager-sop-azure-gateway.azuremicroservices.io/TASKS-SERVICE/lists";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			MultiValueMap<String, Object> queryMap = new LinkedMultiValueMap<>();
			queryMap.add("idBoard", id);

			JSONObject test = new JSONObject();
			test.put("name", "TEST-LISTS");
			test.put("idBoard", id);

			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> request = new HttpEntity<>(test.toString(), headers);
			Object lists = restTemplate.postForObject(uri, request, Object.class);

			Map<String, Object> boardMap = objectMapper.convertValue(board, Map.class);
			Map<String, Object> listsMap = objectMapper.convertValue(lists, Map.class);

			Map<String, Object> response = new HashMap<String, Object>();

			response.putAll(boardMap);
			response.put("list", listsMap);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/boards/{id}")
	public ResponseEntity<?> deleteBoardById(@PathVariable String id) {
		try {
			service.deleteBoardById(id);

			final URI uri = UriComponentsBuilder.fromUriString("http://localhost:8081").path("/lists")
					.queryParam("idBoard", id).build().encode().toUri();

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(uri);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
