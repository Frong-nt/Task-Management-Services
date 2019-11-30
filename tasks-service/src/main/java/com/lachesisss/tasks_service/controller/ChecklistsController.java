package com.lachesisss.tasks_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lachesisss.tasks_service.service.ChecklistsService;
import com.lachesisss.tasks_service.model.CheckItems;
import com.lachesisss.tasks_service.model.Checklists;

@RestController
public class ChecklistsController {

	@Autowired
	private ChecklistsService service = ChecklistsService.getInstance();

	@Autowired
	ObjectMapper objectMapper;

	@GetMapping(value = "/checklists")
	public ResponseEntity<List<Object>> index() {
		try {
			List<Checklists> checklists;
			checklists = service.getAllChecklists();

			List<Object> response = new ArrayList<Object>();

			for (Checklists checklist : checklists) {
				Map<String, Object> checklistMap = objectMapper.convertValue(checklist, Map.class);
				checklistMap.put("checkItems", service.getCheckItemByIdChecklist(checklist.getId()).get());
				response.add(checklistMap);
			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/checkItems")
	public ResponseEntity<List<CheckItems>> index2() {
		try {
			List<CheckItems> checkItems;
			checkItems = service.getAllCheckItems();
			return new ResponseEntity<List<CheckItems>>(checkItems, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/checklists/{id}")
	public ResponseEntity<Checklists> getChecklistById(@RequestParam String id) {
		try {
			Checklists checklist;
			checklist = service.getChecklistById(id).get();
			return new ResponseEntity<Checklists>(checklist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/cards/{id}/checklists")
	public ResponseEntity<List<Checklists>> getChecklistsByIdCard(@RequestParam String idCard) {
		try {
			List<Checklists> checklists;
			checklists = service.getChecklistsByIdCard(idCard).get();
			return new ResponseEntity<List<Checklists>>(checklists, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/checklists/{idChecklist}/checkItems/{id}")
	public ResponseEntity<CheckItems> getCheckItemById(@PathVariable String idChecklist, @PathVariable String id) {
		try {
			CheckItems checkItem;
			checkItem = service.getCheckItemByIdAndIdChecklist(id, idChecklist).get();
			return new ResponseEntity<CheckItems>(checkItem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/checklists/{id}")
	public ResponseEntity<Checklists> updateChecklist(@PathVariable String id, @RequestBody Checklists checklist) {
		try {
			Checklists newChecklist;
			newChecklist = service.getChecklistById(id).get();
			newChecklist = service.updateChecklist(newChecklist, checklist);
			service.saveChecklist(newChecklist);
			return new ResponseEntity<Checklists>(checklist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/checklists/{idChecklist}/checkItem/{id}")
	public ResponseEntity<CheckItems> updateCheckItem(@PathVariable String idChecklist, @PathVariable String id,
			@RequestBody CheckItems checkItem) {
		try {
			CheckItems newCheckItem;
			newCheckItem = service.getCheckItemByIdAndIdChecklist(id, idChecklist).get();
			newCheckItem = service.updateCheckItem(newCheckItem, checkItem);
			service.saveCheckItem(newCheckItem);
			return new ResponseEntity<CheckItems>(checkItem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/checklists")
	public ResponseEntity<Checklists> createBoard(@Valid @RequestBody(required = false) Checklists checklist) {
		try {
			Checklists newChecklist;
			newChecklist = service.createChecklist(checklist);
			service.saveChecklist(newChecklist);
			return new ResponseEntity<Checklists>(newChecklist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/checklists/{id}/checkItems")
	public ResponseEntity<?> createCheckItems(@PathVariable String id,
			@Valid @RequestBody(required = false) CheckItems checkItem) {
		try {
			Checklists checklist;
			checklist = service.getChecklistById(id).get();

			CheckItems newCheckItem = service.createCheckItem(checkItem, id);
			service.saveCheckItem(newCheckItem);

			service.saveChecklist(checklist);
			return new ResponseEntity<Checklists>(checklist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/checklists/all")
	public ResponseEntity<?> deleteAllChecklists() {
		try {
			service.deleteAllChecklists();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/checkItems/all")
	public ResponseEntity<?> deleteAllCheckItems() {
		try {
			service.deleteAllCheckItems();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/checklists/{id}")
	public ResponseEntity<?> deleteChecklistsById(@PathVariable String id) {
		try {
			service.deleteChecklistById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/checklists/{idChecklist}/checkItems/{id}")
	public ResponseEntity<?> deleteCheckItemsById(@PathVariable String idChecklist, @PathVariable String id) {
		try {
			service.getChecklistById(idChecklist);
			service.deleteCheckItemById(id, idChecklist);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
