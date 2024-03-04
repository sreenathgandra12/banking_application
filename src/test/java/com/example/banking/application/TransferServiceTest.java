package com.example.banking.application;

import com.example.banking.application.Dto.AccountDto;
import com.example.banking.application.Dto.TransferRequestDto;
import com.example.banking.application.Service.AccountService;
import com.example.banking.application.Service.Implementation.TransferServiceImpl;
import com.example.banking.application.Service.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransferServiceTest {
    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransferService transferService = new TransferServiceImpl(accountService);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testTransferInsufficientBalance() {
        // Arrange
        AccountDto sourceAccount = new AccountDto(1L, "123456789", 100.0);
        AccountDto destinationAccount = new AccountDto(2L, "987654321", 500.0);
        TransferRequestDto transferRequest = new TransferRequestDto(1L, 2L, 300.0);
        when(accountService.getAccountById(1L)).thenReturn(sourceAccount);
        when(accountService.getAccountById(2L)).thenReturn(destinationAccount);
        // Act and Assert
        assertThrows(RuntimeException.class, () -> transferService.transfer(transferRequest));
        verify(accountService, never()).updateAccount(sourceAccount);
        verify(accountService, never()).updateAccount(destinationAccount);
    }
}
