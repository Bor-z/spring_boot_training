package com.demotest.loans.controller;

import com.demotest.loans.constant.LoansConstant;
import com.demotest.loans.dto.LoansDto;
import com.demotest.loans.dto.ResponseDto;
import com.demotest.loans.service.LoansService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoansController {

    LoansService loansService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber){
        loansService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstant.STATUS_201,LoansConstant.MESSAGE_201));
    }
}
