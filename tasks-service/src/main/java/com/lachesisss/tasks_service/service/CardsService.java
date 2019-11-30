package com.lachesisss.tasks_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lachesisss.tasks_service.model.Cards;
import com.lachesisss.tasks_service.repository.CardsRepository;

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
			return newCard;
		}

		return card;
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

	public Cards updateCard(Cards newCard, Cards card) {
		if (card == null) {
			return null;
		}

		newCard.setName(card.getName());
		newCard.setDescription(card.getDescription());
		newCard.setClosed(card.isClosed());
		newCard.setDue(card.getDue());

		newCard.setIdChecklists(card.getIdChecklists());
		newCard.setIdAttachmentCover(card.getIdAttachmentCover());

		newCard.setIdList(card.getIdList());
		newCard.setIdBoard(card.getIdBoard());
		return newCard;
	}

	public void deleteAllCards() {
		cardsRepository.deleteAll();
	}

	public void deleteCardById(String id) {
		cardsRepository.deleteById(id);
	}

	public void saveCard(Cards card) {
		cardsRepository.save(card);
	}

}
