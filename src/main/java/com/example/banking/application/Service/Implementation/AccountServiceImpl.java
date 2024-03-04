package com.example.banking.application.Service.Implementation;
import com.example.banking.application.Dto.AccountDto;
import com.example.banking.application.Entity.Account;
import com.example.banking.application.Repository.AccountRepository;
import com.example.banking.application.Service.AccountService;
import com.example.banking.application.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Override
    public AccountDto getAccountById(Long id) {
       Account account= accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
        return AccountMapper.mapToAccountDto(account);
    }
    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        double total = account.getBalance() + amount; // Add 'amount' to the current balance
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    @Override
    public AccountDto withdrawal(long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        if(account.getBalance()< amount)
        {
            throw  new RuntimeException("Insufficient amount to be withdrawal");
        }
        double total = account.getBalance() - amount; // Add 'amount' to the current balance
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }
    public void deleteAccount(long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account doesn't exist"));
        accountRepository.deleteById(id);
    }
    @Override
    public void updateAccount(AccountDto accountDto) {
        Account existingAccount = accountRepository.findById(accountDto.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        // Update the entity with new values
        existingAccount.setBalance(accountDto.getBalance());
        // You may update other fields as needed
        // Save the updated entity back to the database
        accountRepository.save(existingAccount);
    }
}
