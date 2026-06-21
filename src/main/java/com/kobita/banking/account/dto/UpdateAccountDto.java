package com.kobita.banking.account.dto;

import java.math.BigDecimal;

import com.kobita.banking.common.Currency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import com.kobita.banking.common.AccountType;

public record UpdateAccountDto(
    @NotEmpty(message ="Balance is required")
    @DecimalMin(value = "0", inclusive = true, message = "Currency cannot be negative")
    BigDecimal balance,

    @NotBlank(message = "Currency is required")
    Currency currency,

    @NotBlank(message = "Type is required")
    AccountType type
) {

}
