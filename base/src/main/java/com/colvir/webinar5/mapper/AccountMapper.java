package com.colvir.webinar5.mapper;

import com.colvir.webinar5.dto.AccountDto;
import com.colvir.webinar5.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Mapping(target = "cur", source = "account.currency")
    AccountDto toDto(Account account);

    @Mapping(target = "currency", source = "account.cur")
    Account toEntity(AccountDto account);

    default List<AccountDto> toDtos(List<Account> accounts) {
        return accounts.stream().map(this::toDto).toList();
    }
}
