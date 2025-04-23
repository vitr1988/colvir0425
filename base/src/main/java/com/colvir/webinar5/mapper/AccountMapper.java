package com.colvir.webinar5.mapper;

import com.colvir.webinar5.dto.AccountDto;
import com.colvir.webinar5.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    AccountDto toDto(Account account);

    default List<AccountDto> toDtos(List<Account> accounts) {
        return accounts.stream().map(this::toDto).toList();
    }
}
