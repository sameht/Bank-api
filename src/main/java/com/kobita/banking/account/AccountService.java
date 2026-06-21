package com.kobita.banking.account;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.kobita.banking.account.dto.AccountDto;
import com.kobita.banking.account.dto.AddAccountDto;
import com.kobita.banking.account.dto.UpdateAccountDto;
import com.kobita.banking.common.AccountStatus;

import jakarta.validation.Valid;

@Service
public interface AccountService {
    public List<AccountDto> findByUserId(Integer userId);

    public List<AccountDto> findAll();
    
    public AccountDto createAccount(AddAccountDto dto);

    public AccountDto deleteAccount(Integer id);

    public AccountDto updateAccount(Integer accountId, UpdateAccountDto dto);

    public void updateAccountStatus(Integer accountId, AccountStatus status);
}
