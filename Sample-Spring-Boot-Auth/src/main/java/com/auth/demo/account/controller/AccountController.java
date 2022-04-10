package com.auth.demo.account.controller;

import com.auth.demo.account.controller.form.AccountRequest;
import com.auth.demo.account.controller.form.AccountResponse;
import com.auth.demo.account.domain.persistence.AccountEntity;
import com.auth.demo.account.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@PostMapping("/accounts/sign-up")
	public ResponseEntity<AccountResponse.UserInfo> signUp(@Valid @RequestBody AccountRequest.SignUp signUp) {
		final AccountEntity savedAccount = accountService.save(signUp.toModel());
		return ResponseEntity.status(CREATED).body(AccountResponse.UserInfo.from(savedAccount));
	}
}