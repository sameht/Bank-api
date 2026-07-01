package com.kobita.banking.transaction;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kobita.banking.transaction.dto.AddTransactionDto;
import com.kobita.banking.transaction.dto.TransactionDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @GetMapping()
    public ResponseEntity<List<TransactionDto>> findAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @PostMapping()
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody @Valid AddTransactionDto dto){
        return ResponseEntity.ok(transactionService.addTransaction(dto));
    }
}
