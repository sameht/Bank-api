package com.kobita.banking.transaction;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kobita.banking.transaction.dto.AddTransactionDto;
import com.kobita.banking.transaction.dto.TransactionDto;
import com.kobita.banking.transaction.dto.TransactionFilterCriteria;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @GetMapping()
    public ResponseEntity<Page<TransactionDto>> findAll(
        TransactionFilterCriteria criteria,
        @PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable){
        return ResponseEntity.ok(transactionService.findAll(criteria, pageable));
    }

    @PostMapping()
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody @Valid AddTransactionDto dto){
        return ResponseEntity.ok(transactionService.addTransaction(dto));
    }
}
