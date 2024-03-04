package com.example.banking.application.Controller;

import com.example.banking.application.Dto.TransferRequestDto;
import com.example.banking.application.Service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDto transferRequestDto) {
        transferService.transfer(transferRequestDto);
        return ResponseEntity.ok("Transfer successful");
    }
}
