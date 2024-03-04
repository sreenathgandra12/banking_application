package com.example.banking.application.Dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {
    private long sourceAccountId;
    private long destinationAccountId;
    private double amount;
}
