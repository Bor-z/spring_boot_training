package com.demotest.demo_1.controller;

import com.demotest.demo_1.constants.AccountsConstants;
import com.demotest.demo_1.dto.CustomerDto;
import com.demotest.demo_1.dto.ResponseDto;
import com.demotest.demo_1.service.AccountsService;
import jakarta.validation.Valid;
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
public class AccountsController {

    AccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        accountsService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                         @RequestParam String mobileNumber){
        CustomerDto cusDto = accountsService.fetchAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cusDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto ){
        boolean sus = accountsService.updateAccount(customerDto);
        if(sus){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                         @RequestParam String mobileNumber){
        boolean sus = accountsService.deleteAccount(mobileNumber);
        if(sus){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));

    }
}
