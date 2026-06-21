package com.kobita.banking.account.dto;

import java.math.BigDecimal;

import com.kobita.banking.common.AccountStatus;
import com.kobita.banking.common.AccountType;
import com.kobita.banking.common.Currency;

public record AccountDto(
    Integer userId,
    Integer id,
    String accountNumber,
    BigDecimal balance,
    Currency currency,
    AccountType type,
    AccountStatus status
) {

}
