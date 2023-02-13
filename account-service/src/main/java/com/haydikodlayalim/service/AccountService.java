package com.haydikodlayalim.service;


import com.haydikodlayalim.model.AccountDto;
import com.haydikodlayalim.entity.Account;
import com.haydikodlayalim.mapper.AccountMapper;
import com.haydikodlayalim.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    public AccountDto get(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return accountMapper.mapToModel(account);
    }

    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.mapToModel(accountDto);
        account = accountRepository.save(account);
        return accountMapper.mapToModel(account);
    }

    @Transactional
    public AccountDto update(String id, AccountDto accountDto) {
        Assert.isNull(id, "Id cannot be null");
        Optional<Account> account = accountRepository.findById(id);
        Account accountToUpdate = account.map(it -> {
            it.setBirthDate(accountDto.getBirthDate());
            it.setUsername(accountDto.getUsername());
            it.setSurname(accountDto.getSurname());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        return accountMapper.mapToModel(accountRepository.save(accountToUpdate));
    }

    @Transactional
    public void delete(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        accountRepository.delete(account);
    }

    public Slice<AccountDto> findAll(Pageable pageable) {
        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"));
        Slice<Account> accounts = accountRepository.findAll(pageable);
        return new PageImpl<>(accounts.getContent().stream()
                .map(accountMapper::mapToModel)
                .collect(Collectors.toList()), pageable, accounts.getSize());


    }
}