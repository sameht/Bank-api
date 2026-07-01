package com.kobita.banking.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kobita.banking.account.Account;
import com.kobita.banking.account.AccountRepository;
import com.kobita.banking.exception.ApiException;
import com.kobita.banking.transaction.dto.AddTransactionDto;
import com.kobita.banking.transaction.dto.TransactionDto;

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
    public List<TransactionDto> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map((transaction)-> transactionMapper.toTransactionDto(transaction))
                .toList();
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

        var account = transactionMapper.toTransaction(dto);
        account.setFromAccount(fromAccount);
        account.setToAccount(toAccount);

        return transactionMapper.toTransactionDto(account);
    }

}
