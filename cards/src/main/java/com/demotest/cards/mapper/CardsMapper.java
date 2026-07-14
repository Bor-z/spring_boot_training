package com.demotest.cards.mapper;

import com.demotest.cards.dto.CardsDto;
import com.demotest.cards.entity.Cards;
import com.demotest.cards.exception.NoLimitException;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto){
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        if((cardsDto.getTotalLimit() - cardsDto.getAmountUsed())<0){
            throw new NoLimitException("Amount used can't surpass total limit.");
        }
        cardsDto.setAvailableAmount(cards.getTotalLimit() - cards.getAmountUsed());

        return cardsDto;
    }

    public static Cards maptToCards(CardsDto cardsDto, Cards cards){
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        if((cardsDto.getTotalLimit() - cardsDto.getAmountUsed())<0){
            throw new NoLimitException("Amount used can't surpass total limit.");
        }
        cards.setAvailableAmount(cardsDto.getTotalLimit() - cardsDto.getAmountUsed());

        return cards;
    }
}
