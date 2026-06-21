package com.kobita.banking.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.kobita.banking.account.dto.AccountDto;
import com.kobita.banking.account.dto.AddAccountDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    @Mapping(source = "user.id", target = "userId")
    AccountDto toAccountDto(Account account);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    Account toAccount(AddAccountDto account);
    
}   
