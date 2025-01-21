/*
 * This file is generated by jOOQ.
 */
package top.wuhunyu.jooq.transaction.codegen;


import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import top.wuhunyu.jooq.transaction.codegen.tables.TUser;
import top.wuhunyu.jooq.transaction.codegen.tables.records.TUserRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * test.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TUserRecord> KEY_T_USER_PRIMARY = Internal.createUniqueKey(TUser.T_USER, DSL.name("KEY_t_user_PRIMARY"), new TableField[] { TUser.T_USER.ID }, true);
}
