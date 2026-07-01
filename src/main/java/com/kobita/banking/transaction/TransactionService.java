package com.kobita.banking.transaction;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.kobita.banking.transaction.dto.AddTransactionDto;
import com.kobita.banking.transaction.dto.TransactionDto;

@Service
public interface TransactionService {

    TransactionDto addTransaction(AddTransactionDto dto);

    List<TransactionDto> findAll();

}
