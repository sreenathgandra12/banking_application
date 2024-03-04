package com.example.banking.application.Dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private  long Id;
    private  String accountHolderName;
    private double balance;
}
