package com.lachesisss.cards_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lachesisss.cards_service.model.Cards;

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

	@PostMapping(value = "/cards")
	public ResponseEntity<Cards> createCard(@RequestBody(required = false) Cards card) {
		try {
			Cards newCard;
			newCard = service.createCard(card);
			if (newCard == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Cards>(newCard, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/cards")
	public ResponseEntity<?> deleteCardsByIdBoard(@RequestParam(value = "idBoard") String idBoard) {
		try {
			List<Cards> cards;
			cards = service.getCardsByIdBoard(idBoard).get();

			for (Cards item : cards) {
				service.deleteCardById(item.getId());
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
