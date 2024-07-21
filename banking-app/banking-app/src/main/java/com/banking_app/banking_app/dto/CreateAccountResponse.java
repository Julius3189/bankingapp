package com.banking_app.banking_app.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountResponse {
    private String owner;
    private String email;
    private BigDecimal initialBalance;
}
