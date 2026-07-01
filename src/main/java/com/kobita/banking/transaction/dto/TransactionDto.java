package com.kobita.banking.transaction.dto;

import java.math.BigDecimal;

import com.kobita.banking.account.Account;
import com.kobita.banking.common.TransactionType;

public record TransactionDto(
    Integer id,
    BigDecimal amount,
    Account fromAccount,
    Account toAccount,
    TransactionType type
) {

}
