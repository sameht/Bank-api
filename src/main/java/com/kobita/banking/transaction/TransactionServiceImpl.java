package com.kobita.banking.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kobita.banking.account.Account;
import com.kobita.banking.account.AccountRepository;
import com.kobita.banking.exception.ApiException;
import com.kobita.banking.transaction.dto.AddTransactionDto;
import com.kobita.banking.transaction.dto.TransactionDto;
import com.kobita.banking.transaction.dto.TransactionFilterCriteria;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    final TransactionRepository transactionRepository;
    final AccountRepository accountRepository;
    final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
        TransactionMapper transactionMapper, 
        AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.transactionMapper = transactionMapper;
    }
    
    @Override
    public Page<TransactionDto> findAll(TransactionFilterCriteria criteria, Pageable pageable) {
        Specification<Transaction> spec = Specification
            .where(TransactionSpecifications.hasFromAccountNumber(criteria.fromAccountNumber()))
            .and(TransactionSpecifications.hasToAccountNumber(criteria.toAccountNumber()))
            .and(TransactionSpecifications.hasType(criteria.type()))
            .and(TransactionSpecifications.maxAmount(criteria.maxAmount()))
            .and(TransactionSpecifications.minAmount(criteria.minAmount()));
            
        return transactionRepository.findAll(spec, pageable)
                .map((transaction)-> transactionMapper.toTransactionDto(transaction));
    }

    @Transactional
    @Override
    public TransactionDto addTransaction(AddTransactionDto dto) {
        Account fromAccount = accountRepository.findByAccountNumber(dto.fromAccountNumber())
            .orElseThrow(()-> new ApiException("From Account not found", HttpStatus.NOT_FOUND));
        
        Account toAccount = accountRepository.findByAccountNumber(dto.toAccountNumber())
            .orElseThrow(()-> new ApiException("To Account not found", HttpStatus.NOT_FOUND));
                
        if(fromAccount.getBalance().compareTo(dto.amount()) < 0){
            throw new ApiException("Unsufficient balance", HttpStatus.BAD_REQUEST);
        }
        
        fromAccount.setBalance(
            fromAccount.getBalance().subtract(dto.amount())
        );

        toAccount.setBalance(
            toAccount.getBalance().add(dto.amount())
        );

        var transaction = transactionMapper.toTransaction(dto);
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transactionRepository.save(transaction);

        return transactionMapper.toTransactionDto(transaction);
    }

}
