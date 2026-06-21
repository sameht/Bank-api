package com.kobita.banking.account;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kobita.banking.account.dto.AccountDto;
import com.kobita.banking.account.dto.AddAccountDto;
import com.kobita.banking.account.dto.UpdateAccountDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountDto>> findByUserId(@PathVariable Integer userId){
        return ResponseEntity.ok(accountService.findByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<AccountDto> save(@RequestBody @Valid AddAccountDto dto){
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid UpdateAccountDto dto, @PathVariable Integer id){
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
