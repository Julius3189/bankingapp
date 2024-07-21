package com.banking_app.banking_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawRequest {

    @NotEmpty(message = "Amount is mandatory")
    @NotNull(message = "Amount is mandatory")
    private BigDecimal amount;
}
