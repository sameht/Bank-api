package com.kobita.banking.transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kobita.banking.transaction.dto.AddTransactionDto;
import com.kobita.banking.transaction.dto.TransactionDto;
import com.kobita.banking.transaction.dto.TransactionFilterCriteria;

@Service
public interface TransactionService {

    TransactionDto addTransaction(AddTransactionDto dto);

    Page<TransactionDto> findAll(TransactionFilterCriteria criteria, Pageable pageable);

}
