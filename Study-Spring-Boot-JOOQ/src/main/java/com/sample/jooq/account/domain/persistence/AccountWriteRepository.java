package com.sample.jooq.account.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountWriteRepository extends JpaRepository<AccountEntity, Long> {
}
