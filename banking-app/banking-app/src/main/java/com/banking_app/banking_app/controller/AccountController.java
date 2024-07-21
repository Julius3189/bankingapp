package com.banking_app.banking_app.controller;

import com.banking_app.banking_app.dto.*;
import com.banking_app.banking_app.entity.Account;
import com.banking_app.banking_app.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody @Valid CreateAccountRequest request) {
        Account account = accountService.createAccount(request);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccount(accountId));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody @Valid UpdateAccountRequest request) {
        Account account = accountService.updateAccount(accountId, request);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<Account> deposit(@PathVariable Long accountId, @RequestBody @Valid DepositRequest depositRequest) {
        Account account = accountService.deposit(accountId, depositRequest);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<Account> withdraw(@PathVariable Long accountId, @RequestBody @Valid WithdrawRequest withdrawRequest) {
        Account account = accountService.withdraw(accountId, withdrawRequest);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long accountId) {
        BigDecimal balance = accountService.getBalance(accountId);
        return ResponseEntity.ok(balance);
    }
}
