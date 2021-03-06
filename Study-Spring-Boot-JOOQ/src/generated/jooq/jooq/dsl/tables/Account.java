/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables;


import java.util.Arrays;
import java.util.List;

import jooq.dsl.Keys;
import jooq.dsl.Public;
import jooq.dsl.tables.records.AccountRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Account extends TableImpl<AccountRecord> {

    private static final long serialVersionUID = -247177110;

    /**
     * The reference instance of <code>public.account</code>
     */
    public static final Account ACCOUNT = new Account();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * The column <code>public.account.id</code>.
     */
    public final TableField<AccountRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.account.email</code>.
     */
    public final TableField<AccountRecord, String> EMAIL = createField(DSL.name("email"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.account.password</code>.
     */
    public final TableField<AccountRecord, String> PASSWORD = createField(DSL.name("password"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.account.roles</code>.
     */
    public final TableField<AccountRecord, String> ROLES = createField(DSL.name("roles"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.account.username</code>.
     */
    public final TableField<AccountRecord, String> USERNAME = createField(DSL.name("username"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * Create a <code>public.account</code> table reference
     */
    public Account() {
        this(DSL.name("account"), null);
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(String alias) {
        this(DSL.name(alias), ACCOUNT);
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(Name alias) {
        this(alias, ACCOUNT);
    }

    private Account(Name alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(Name alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Account(Table<O> child, ForeignKey<O, AccountRecord> key) {
        super(child, key, ACCOUNT);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<AccountRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACCOUNT;
    }

    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.ACCOUNT_PKEY;
    }

    @Override
    public List<UniqueKey<AccountRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountRecord>>asList(Keys.ACCOUNT_PKEY, Keys.UK_Q0UJA26QGU1ATULENWUP9RXYR, Keys.UK_GEX1LMAQPG0IR5G1F5EFTYAA1);
    }

    @Override
    public Account as(String alias) {
        return new Account(DSL.name(alias), this);
    }

    @Override
    public Account as(Name alias) {
        return new Account(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(String name) {
        return new Account(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Name name) {
        return new Account(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
