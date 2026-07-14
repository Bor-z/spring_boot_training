package com.demotest.cards.controller;

import com.demotest.cards.constant.CardsConstant;
import com.demotest.cards.dto.CardsDto;
import com.demotest.cards.dto.ResponseDto;
import com.demotest.cards.repository.CardsRepository;
import com.demotest.cards.service.CardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CardsController {

    CardsService cardsService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
                                                      @NotEmpty(message = "MobileNumber can not be a null or empty")
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
                                                      String mobileNumber){
        cardsService.createCards(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardsConstant.STATUS_201,CardsConstant.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
                                                  String mobileNumber){
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto){
       boolean sus = cardsService.updateCard(cardsDto);

       if(sus){
           return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstant.STATUS_200,CardsConstant.MESSAGE_200));
       }
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(CardsConstant.STATUS_417,CardsConstant.MESSAGE_417_UPDATE));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
                                                      String mobileNumber){
        boolean sus = cardsService.deleteCard(mobileNumber);

        if(sus){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardsConstant.STATUS_200,CardsConstant.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(CardsConstant.STATUS_417,CardsConstant.MESSAGE_417_DELETE));
    }

}
