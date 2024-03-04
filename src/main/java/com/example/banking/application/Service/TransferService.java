package com.example.banking.application.Service;

import com.example.banking.application.Dto.TransferRequestDto;

public interface TransferService {
    void transfer(TransferRequestDto transferRequestDto);
}
