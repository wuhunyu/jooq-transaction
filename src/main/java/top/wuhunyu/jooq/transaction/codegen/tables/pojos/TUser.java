/*
 * This file is generated by jOOQ.
 */
package top.wuhunyu.jooq.transaction.codegen.tables.pojos;


import top.wuhunyu.jooq.transaction.codegen.tables.interfaces.ITUser;


/**
 * 用户信息
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TUser implements ITUser {

    private static final long serialVersionUID = 1L;

    private Long   id;
    private String userName;

    public TUser() {}

    public TUser(ITUser value) {
        this.id = value.getId();
        this.userName = value.getUserName();
    }

    public TUser(
        Long   id,
        String userName
    ) {
        this.id = id;
        this.userName = userName;
    }

    /**
     * Getter for <code>test.t_user.id</code>. 主键
     */
    @Override
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>test.t_user.id</code>. 主键
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for <code>test.t_user.user_name</code>. 用户名称
     */
    @Override
    public String getUserName() {
        return this.userName;
    }

    /**
     * Setter for <code>test.t_user.user_name</code>. 用户名称
     */
    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TUser (");

        sb.append(id);
        sb.append(", ").append(userName);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ITUser from) {
        setId(from.getId());
        setUserName(from.getUserName());
    }

    @Override
    public <E extends ITUser> E into(E into) {
        into.from(this);
        return into;
    }
}
