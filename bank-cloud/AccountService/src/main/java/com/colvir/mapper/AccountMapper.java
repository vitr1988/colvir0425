package com.colvir.mapper;

import com.colvir.domain.Account;
import com.colvir.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    AccountDto toDto(Account account);
}
