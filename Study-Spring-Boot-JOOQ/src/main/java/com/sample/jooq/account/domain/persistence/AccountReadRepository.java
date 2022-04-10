package com.sample.jooq.account.domain.persistence;

import jooq.dsl.tables.Account;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountReadRepository {

    private final DSLContext dsl;
    private final Account account = Account.ACCOUNT;

    public boolean existsUserInfo(String email, String username) {
        return dsl.fetchExists(
                dsl.selectOne()
                        .from(account)
                        .where(account.EMAIL.eq(email), account.USERNAME.eq(username))
        );
    }

    public AccountEntity findByUsername(String username) {

        /*  명시적 매핑 alias
            dsl.select(
                        account.ID.as("id"),
                        account.USERNAME.as("username"),
                        account.PASSWORD.as("password"),
                        account.EMAIL.as("email"),
                        account.ROLES.as("roles")
                )
                .from(account)
                .where(account.USERNAME.eq(username))
                .fetchOneInto(AccountEntity.class);
         */
        
        // 묵시적 매핑 - ResultSet 과 Entity field 명 자동 매칭
        return dsl.select()
                .from(account)
                .where(account.USERNAME.eq(username))
                .fetchOneInto(AccountEntity.class);
    }
}
