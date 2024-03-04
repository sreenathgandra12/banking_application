package com.example.banking.application.Service.Implementation;
import com.example.banking.application.Dto.AccountDto;
import com.example.banking.application.Dto.TransferRequestDto;
import com.example.banking.application.Service.AccountService;
import com.example.banking.application.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private final AccountService accountService;
    @Autowired
    public TransferServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void transfer(TransferRequestDto transferRequestDto) {
        AccountDto sourceAccount=accountService.getAccountById(transferRequestDto.getSourceAccountId());
        AccountDto destinationAccount=accountService.getAccountById(transferRequestDto.getDestinationAccountId());
        if(sourceAccount.getBalance() < transferRequestDto.getAmount())
        {
            throw new RuntimeException("Insufficient balance in the source account");
        }
        sourceAccount.setBalance(sourceAccount.getBalance() - transferRequestDto.getAmount());
        destinationAccount.setBalance(destinationAccount.getBalance() + transferRequestDto.getAmount());
        // Update accounts
        accountService.updateAccount(sourceAccount);
        accountService.updateAccount(destinationAccount);
    }
}
