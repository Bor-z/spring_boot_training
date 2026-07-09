package com.demotest.loans.controller;

import com.demotest.loans.constant.LoansConstant;
import com.demotest.loans.dto.LoansDto;
import com.demotest.loans.dto.ResponseDto;
import com.demotest.loans.service.LoansService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class LoansController {

    LoansService loansService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
                                                      String mobileNumber){
        loansService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoansConstant.STATUS_201,LoansConstant.MESSAGE_201));
    }

    @GetMapping(path = "fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam
                                                  @Pattern(regexp="(^$|[0-9]{10})",message = "MobileNumber must be 10 digits")
                                                  String mobileNumber){
        LoansDto loansDto = loansService.fetchLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody LoansDto loansDto){
        boolean sus = loansService.updateLoan(loansDto);

        if(sus){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoansConstant.STATUS_200,LoansConstant.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(LoansConstant.STATUS_417,LoansConstant.MESSAGE_417_UPDATE));
    }
}
