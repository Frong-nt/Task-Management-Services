package com.lachesisss.cards_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lachesisss.cards_service.model.Cards;
import com.lachesisss.cards_service.repository.CardsRepository;

@Service
public class CardsService {

	private static CardsService instance = null;

	@Autowired
	private CardsRepository cardsRepository;

	public static CardsService getInstance() {
		if (instance == null) {
			instance = new CardsService();
		}

		return instance;
	}

	public Cards createCard(Cards card) {
		if (card == null) {
			Cards newCard = new Cards();
			return saveCard(newCard) ? newCard : null;
		}

		return saveCard(card) ? card : null;
	}

	public List<Cards> getAllCards() {
		return cardsRepository.findAll();
	}

	public Optional<Cards> getCardById(String id) {
		return cardsRepository.findById(id);
	}

	public Optional<List<Cards>> getCardsByIdBoard(String idBoard) {
		return cardsRepository.findByIdBoard(idBoard);
	}

	public Optional<List<Cards>> getCardsByIdList(String idList) {
		return cardsRepository.findByIdList(idList);
	}

	public boolean deleteAllCards() {
		try {
			cardsRepository.deleteAll();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public boolean deleteCardById(String id) {
		try {
			cardsRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private boolean saveCard(Cards card) {
		if (card == null) {
			return false;
		}

		cardsRepository.save(card);
		return true;
	}

}
