package com.example.banking.application.Service;

import com.example.banking.application.Dto.AccountDto;

import java.util.List;


public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id ,double amount);

    AccountDto withdrawal(long id,double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(long id);

    void updateAccount(AccountDto accountDto);

}
