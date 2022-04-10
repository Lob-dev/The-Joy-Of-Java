package com.auth.demo.account.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	Optional<AccountEntity> findByUsernameLike(String username);
	boolean existsByEmailLikeOrUsernameLike(String email, String name);
}