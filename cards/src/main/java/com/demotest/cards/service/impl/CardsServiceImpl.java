package com.demotest.cards.service.impl;

import com.demotest.cards.constant.CardsConstant;
import com.demotest.cards.dto.CardsDto;
import com.demotest.cards.entity.Cards;
import com.demotest.cards.exception.CardAlreadyExistsException;
import com.demotest.cards.exception.ResourceNotFoundException;
import com.demotest.cards.mapper.CardsMapper;
import com.demotest.cards.repository.CardsRepository;
import com.demotest.cards.service.CardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements CardsService {

    CardsRepository cardsRepository;

    @Override
    public void createCards(String mobileNumber){
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);

        if(card.isPresent()){
            throw new CardAlreadyExistsException("Card already exists with mobile number : "+mobileNumber);
        }

        cardsRepository.save(createCardDetails(mobileNumber));
    }

    private Cards createCardDetails(String mobileNumber){
        Cards nCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        nCard.setCardNumber(Long.toString(randomCardNumber));
        nCard.setMobileNumber(mobileNumber);
        nCard.setCardType(CardsConstant.CREDIT_CARD);
        nCard.setTotalLimit(CardsConstant.NEW_CARD_LIMIT);
        nCard.setAmountUsed(0);
        nCard.setAvailableAmount(CardsConstant.NEW_CARD_LIMIT);
        return nCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber){
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);

        if(card.isEmpty()){
            throw new ResourceNotFoundException("Card","Mobile Number", mobileNumber);
        }

        return CardsMapper.mapToCardsDto(card.get(), new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto){
        Optional<Cards> op_loan = cardsRepository.findByCardNumber(cardsDto.getCardNumber());
        if(op_loan.isEmpty()){
            throw new ResourceNotFoundException("Card","Card Number",cardsDto.getCardNumber());
        }
        cardsRepository.save(CardsMapper.maptToCards(cardsDto, op_loan.get()));
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber){
        Optional<Cards> op_loan = cardsRepository.findByMobileNumber(mobileNumber);
        if(op_loan.isEmpty()){
            throw new ResourceNotFoundException("Card","Mobile Number",mobileNumber);
        }
        cardsRepository.deleteById(op_loan.get().getCardId());
        return true;
    }
}
