package com.kobita.banking.account.dto;

import java.math.BigDecimal;
import com.kobita.banking.common.AccountType;
import com.kobita.banking.common.Currency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddAccountDto(

    @NotNull(message = "UserId is required")
    Integer userId,

    @NotBlank(message = "Account Number is required")
    String accountNumber,
    
    @NotNull(message = "Balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    BigDecimal balance,

    @NotNull(message = "Currency is required")
    Currency currency,

    @NotNull(message = "Type is required")
    AccountType type
) {

}
