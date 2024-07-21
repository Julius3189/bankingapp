package com.banking_app.banking_app.service.serviceImpl;

import com.banking_app.banking_app.dto.CreateAccountRequest;
import com.banking_app.banking_app.dto.DepositRequest;
import com.banking_app.banking_app.dto.UpdateAccountRequest;
import com.banking_app.banking_app.dto.WithdrawRequest;
import com.banking_app.banking_app.entity.Account;
import com.banking_app.banking_app.exception.InsufficientFundsException;
import com.banking_app.banking_app.exception.UserAlreadyExistsException;
import com.banking_app.banking_app.exception.UserNotFoundException;
import com.banking_app.banking_app.repository.AccountRepository;
import com.banking_app.banking_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(CreateAccountRequest createAccountRequest) {

      if (accountRepository.existsByEmail(createAccountRequest.getEmail())){
          throw new UserAlreadyExistsException("THE USER ALREADY EXIST");
      }

       Account builtAccount = Account.builder()
              .owner(createAccountRequest.getOwner())
              .email(createAccountRequest.getEmail())
              .balance(createAccountRequest.getInitialBalance())
              .build();

       return accountRepository.save(builtAccount);
    }

    @Override
    public Account getAccount(Long accountId) {
      Account account =  accountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("USERNAME IS NOT FOUND"));

      return account;
    }

    @Override
    public Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest) {
        Account account =  accountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("USERNAME IS NOT FOUND"));
        account.setOwner(updateAccountRequest.getOwner());
        account.setEmail(updateAccountRequest.getEmail());

       return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account =  accountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("USERNAME IS NOT FOUND"));
        accountRepository.delete(account);
    }

    @Override
    @Transactional
    public Account deposit(Long accountId, DepositRequest depositRequest) {
        Account account =  accountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("USERNAME IS NOT FOUND"));
        account.setBalance(account.getBalance().add(depositRequest.getAmount()));
        return account;
    }

    @Override
    public Account withdraw(Long accountId, WithdrawRequest withdrawRequest) {
        Account account =  accountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("USERNAME IS NOT FOUND"));

        //check if the account has sufficient balance
        if (account.getBalance().compareTo(withdrawRequest.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds: requested amount exceeds available balance.");
        }

        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));
        return account;
    }

    @Override
    public BigDecimal getBalance(Long accountId) {
        Account account =  accountRepository.findById(accountId).orElseThrow(() -> new UserNotFoundException("USERNAME IS NOT FOUND"));
        return account.getBalance();
    }


}
