package com.sample.jooq.account.domain.service;

import com.sample.jooq.account.domain.model.Account;
import com.sample.jooq.account.domain.persistence.AccountEntity;
import com.sample.jooq.account.domain.persistence.AccountReadRepository;
import com.sample.jooq.account.domain.persistence.AccountWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountReadRepository accountReadRepository;
    private final AccountWriteRepository accountWriteRepository;

    public AccountEntity save(Account account) {
        if (accountReadRepository.existsUserInfo(account.getEmail(), account.getUsername())) {
            throw new RuntimeException("This account states is duplicated");
        }
        return accountWriteRepository.save(account.toEntity());
    }

    @Transactional(readOnly = true)
    public AccountEntity findOne(String username) {
        final AccountEntity targetAccount = accountReadRepository.findByUsername(username);
        Assert.notNull(targetAccount, format("account is not Found || username = %s || dateTime = %s", username, LocalDateTime.now()));
        return targetAccount;
    }
}
