package com.demotest.cards.service;

import com.demotest.cards.dto.CardsDto;
import com.demotest.cards.repository.CardsRepository;

public interface CardsService {

    void createCards(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
