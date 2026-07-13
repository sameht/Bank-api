package com.kobita.banking.transaction.dto;

public record TransactionFilterCriteria(
    String type,
    String fromAccountNumber,
    String toAccountNumber,
    Double maxAmount,
    Double minAmount
) {

}
