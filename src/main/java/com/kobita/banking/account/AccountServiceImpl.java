package com.kobita.banking.account;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kobita.banking.account.dto.AccountDto;
import com.kobita.banking.account.dto.AddAccountDto;
import com.kobita.banking.account.dto.UpdateAccountDto;
import com.kobita.banking.common.AccountStatus;
import com.kobita.banking.exception.ApiException;
import com.kobita.banking.user.User;
import com.kobita.banking.user.UserRepository;

@Service
public class AccountServiceImpl implements AccountService{

    final UserRepository userRepository;
    final AccountRepository accountRepository;
    final AccountMapper accountMapper;
    
    public AccountServiceImpl(UserRepository userRepository,
        AccountRepository accountRepository, 
        AccountMapper accountMapper) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDto> findByUserId(Integer userId) {
        return accountRepository.findByUserId(userId)
            .stream()
            .map((Account x) -> accountMapper.toAccountDto(x))
            .toList();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
            .stream()
            .map(x -> accountMapper.toAccountDto(x))
            .toList();
    }

    @Override
    public AccountDto createAccount(AddAccountDto dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(()-> new ApiException("User not found", HttpStatus.NOT_FOUND));

        if(accountRepository.existsByAccountNumber(dto.accountNumber())){
            throw new ApiException("Account Number already exists", HttpStatus.CONFLICT);
        }

        Account account = accountMapper.toAccount(dto);
        account.setUser(user); 
        account.setStatus(AccountStatus.ACTIVE);

        accountRepository.save(account);
        return accountMapper.toAccountDto(account);
    }

    @Override
    public AccountDto deleteAccount(Integer id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ApiException("Account not found", HttpStatus.NOT_FOUND));
        accountRepository.deleteById(id);
        return accountMapper.toAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(Integer accountId, UpdateAccountDto dto) {
        Account account = accountRepository.findById(accountId).orElseThrow(()-> new ApiException("Id not found", HttpStatus.NOT_FOUND));
        
        account.setBalance(dto.balance());
        account.setCurrency(dto.currency());
        account.setType(dto.type());

        accountRepository.save(account);
        return accountMapper.toAccountDto(account);
    }

}
