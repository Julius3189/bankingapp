package com.banking_app.banking_app.service;

import com.banking_app.banking_app.dto.CreateAccountRequest;
import com.banking_app.banking_app.dto.DepositRequest;
import com.banking_app.banking_app.dto.UpdateAccountRequest;
import com.banking_app.banking_app.dto.WithdrawRequest;
import com.banking_app.banking_app.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

   Account createAccount(CreateAccountRequest createAccountRequest);
   Account getAccount(Long accountId);
   Account updateAccount(Long accountId, UpdateAccountRequest updateAccountRequest);
   void deleteAccount(Long accountId);
   Account deposit(Long accountId, DepositRequest depositRequest);
   Account withdraw(Long accountId, WithdrawRequest withdrawRequest);
   BigDecimal getBalance(Long accountId);
}
