package com.finapp.account.controller;


import com.finapp.account.constants.AccountsConstants;
import com.finapp.account.dto.CustomerDto;
import com.finapp.account.dto.ResponseDto;
import com.finapp.account.service.impl.AccountsServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.finapp.account.service.IAccountsService;


@RestController
@Validated
public class AccountsController {

    @Autowired
    private AccountsServiceImpl accountsService;

    @PostMapping("/api/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/api/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Valid @RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits") String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerDto);
    }

    @PutMapping("/api/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto( AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/api/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@Valid @RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits") String mobileNumber) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.MESSAGE_417_DELETE));
        }
    }




}
