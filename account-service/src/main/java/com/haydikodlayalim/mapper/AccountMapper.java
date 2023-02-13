package com.haydikodlayalim.mapper;

import com.haydikodlayalim.model.AccountDto;
import com.haydikodlayalim.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account mapToModel(AccountDto dto);

    AccountDto mapToModel(Account entity);

}