package com.lachesisss.tasks_service.controller;

import java.util.List;
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

import com.lachesisss.tasks_service.service.CardsService;
import com.lachesisss.tasks_service.model.Cards;

@RestController
public class CardsController {

	@Autowired
	private CardsService service = CardsService.getInstance();

	@GetMapping(value = "/cards")
	public ResponseEntity<List<Cards>> index() {
		try {
			List<Cards> card;
			card = service.getAllCards();
			return new ResponseEntity<List<Cards>>(card, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/cards/{id}")
	public ResponseEntity<Cards> getCardById(@PathVariable String id) {
		try {
			Cards card;
			card = service.getCardById(id).get();
			return new ResponseEntity<Cards>(card, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/lists/{idList}/cards")
	public ResponseEntity<List<Cards>> getCardsByIdList(@PathVariable String idList) {
		try {
			List<Cards> cards;
			cards = service.getCardsByIdList(idList).get();
			return new ResponseEntity<List<Cards>>(cards, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/cards/{id}")
	public ResponseEntity<Cards> updateChecklist(@PathVariable String id, @RequestBody Cards card) {
		try {
			Cards newCard;
			newCard = service.getCardById(id).get();
			newCard = service.updateCard(newCard, card);
			service.saveCard(newCard);
			return new ResponseEntity<Cards>(card, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/cards")
	public ResponseEntity<Cards> createCard(@RequestBody(required = false) Cards card) {
		try {
			Cards newCard;
			newCard = service.createCard(card);
			service.saveCard(newCard);
			return new ResponseEntity<Cards>(newCard, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/cards")
	public ResponseEntity<?> deleteCardsByIdList(@RequestParam(value = "idList") String idList) {
		try {
			List<Cards> cards;
			cards = service.getCardsByIdList(idList).get();

			for (Cards item : cards) {
				service.deleteCardById(item.getId());
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/cards/{id}")
	public ResponseEntity<?> deleteCardById(@PathVariable String id) {
		try {
			service.deleteCardById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/cards/all")
	public ResponseEntity<?> deleteAllCards() {
		try {
			service.deleteAllCards();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
