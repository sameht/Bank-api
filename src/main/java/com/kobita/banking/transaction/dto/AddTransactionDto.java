package com.kobita.banking.transaction.dto;

import java.math.BigDecimal;
import com.kobita.banking.common.TransactionType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AddTransactionDto(

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount should not be negative")
    BigDecimal amount,

    @NotBlank(message = "From Account Number is required")
    String fromAccountNumber,

    @NotBlank(message = "To Account Number is required")
    String toAccountNumber,

    @NotNull(message = "Type is required")
    TransactionType type
) {
}
