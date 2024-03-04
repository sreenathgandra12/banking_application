package com.example.banking.application;

import com.example.banking.application.Controller.AccountController;
import com.example.banking.application.Dto.AccountDto;
import com.example.banking.application.Service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountControllerTest {
    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllAccounts() {
        // Mocking the behavior of accountService
        when(accountService.getAllAccounts()).thenReturn(Collections.singletonList(new AccountDto()));

        // Calling the controller method
        ResponseEntity<List<AccountDto>> responseEntity = accountController.getAllAccounts();

        // Asserting the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
    }

}
