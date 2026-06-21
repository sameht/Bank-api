package com.kobita.banking.account.dto;

import java.math.BigDecimal;

import com.kobita.banking.common.AccountStatus;
import com.kobita.banking.common.AccountType;
import com.kobita.banking.common.Currency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AddAccountDto(

    @NotEmpty(message = "UserId is required")
    Integer userId,

    @NotBlank(message = "Account Number is required")
    String accountNumber,
    
    @NotEmpty(message = "Balance is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative")
    BigDecimal balance,

    @NotBlank(message = "Currency is required")
    Currency currency,

    @NotBlank(message = "Type is required")
    AccountType type
) {

}
