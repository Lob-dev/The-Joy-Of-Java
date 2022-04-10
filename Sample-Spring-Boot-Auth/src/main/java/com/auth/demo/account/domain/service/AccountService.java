package com.auth.demo.account.domain.service;

import com.auth.demo.account.domain.exception.AccountInfoDuplicateException;
import com.auth.demo.account.domain.exception.AccountNotFoundException;
import com.auth.demo.account.domain.model.Account;
import com.auth.demo.account.domain.persistence.AccountEntity;
import com.auth.demo.account.domain.persistence.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public AccountEntity save(Account account) {
		if (accountRepository.existsByEmailLikeOrUsernameLike(account.getEmail(), account.getUsername())) {
			throw new AccountInfoDuplicateException("This account states is duplicated");
		}
		account.encodedPassword(passwordEncoder);
		AccountEntity newAccount = account.toEntity();
		return accountRepository.save(newAccount);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return Account.from(findOne(username));
	}

	@Transactional(readOnly = true)
	public AccountEntity findOne(String username) {
		return accountRepository.findByUsernameLike(username).orElseThrow(
				() -> new AccountNotFoundException(format("account is not Found || username = %s || dateTime = %s", username, LocalDateTime.now())));
	}
}