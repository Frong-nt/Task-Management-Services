package com.lachesisss.tasks_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lachesisss.tasks_service.service.ListsService;
import com.lachesisss.tasks_service.model.Lists;

@RestController
public class ListsController {

	@Autowired
	private ListsService service = ListsService.getInstance();

	@GetMapping(value = "/lists")
	public ResponseEntity<?> getLists(@RequestParam MultiValueMap<String, Object> queryMap) {
		try {
			List<Lists> lists;
			if (queryMap.isEmpty()) {
				lists = service.getAllLists();
			} else {
				String idBoard = queryMap.get("idBoard").get(0).toString();
				lists = service.getListsByIdBoard(idBoard).get();
			}
			return new ResponseEntity<List<Lists>>(lists, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/lists/{id}")
	public ResponseEntity<Lists> getListById(@PathVariable String id) {
		try {
			Lists list;
			list = service.getListById(id).get();
			return new ResponseEntity<Lists>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/boards/{idBoard}/lists")
	public ResponseEntity<List<Lists>> getListsByIdBoard(@PathVariable String idBoard) {
		try {
			List<Lists> list;
			list = service.getListsByIdBoard(idBoard).get();
			return new ResponseEntity<List<Lists>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/lists/{id}/{field}")
	public ResponseEntity<?> getListFieldsById(@PathVariable String id, @PathVariable String field) {
		try {
			Lists list;
			list = service.getListById(id).get();
			switch (field) {
			case "name":
				return new ResponseEntity<String>(list.getName(), HttpStatus.OK);
			case "closed":
				return new ResponseEntity<Boolean>(list.isClosed(), HttpStatus.OK);
			case "idBoard":
				return new ResponseEntity<String>(list.getIdBoard(), HttpStatus.OK);
			default:
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/lists/{id}")
	public ResponseEntity<Lists> updateList(@PathVariable String id, @RequestBody Lists list) {
		try {
			Lists newList;
			newList = service.getListById(id).get();
			newList = service.updateList(newList, list);
			if (newList == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Lists>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/lists")
	public ResponseEntity<Lists> createList(@RequestBody(required = false) Lists lists) {
		try {
			Lists newList;
			newList = service.createList(lists);
			if (newList == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Lists>(newList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/lists")
	public ResponseEntity<?> deleteListsByIdBoard(@RequestParam(value = "idBoard") String idBoard) {
		try {
			List<Lists> list;
			list = service.getListsByIdBoard(idBoard).get();

			for (Lists item : list) {
				service.deleteListById(item.getId());
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/lists/{id}")
	public ResponseEntity<?> deleteListById(@PathVariable String id) {
		try {
			service.deleteListById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/lists/all")
	public ResponseEntity<?> deleteAllLists() {
		try {
			service.deleteAllLists();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
