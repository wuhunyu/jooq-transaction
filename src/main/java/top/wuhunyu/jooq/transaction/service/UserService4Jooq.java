package top.wuhunyu.jooq.transaction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wuhunyu.jooq.transaction.codegen.tables.daos.TUserDao;
import top.wuhunyu.jooq.transaction.codegen.tables.pojos.TUser;
import top.wuhunyu.jooq.transaction.codegen.tables.records.TUserRecord;

/**
 * 用户信息 jooq
 *
 * @author wuhunyu
 * @date 2025-01-21 14:42
 */

@Service("userService4Jooq")
@RequiredArgsConstructor
@Slf4j
public class UserService4Jooq {

    private final TUserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public Integer insert(final Long userId, final String userName) {
        final TUserRecord tUserRecord = new TUserRecord();
        tUserRecord.setId(userId);
        tUserRecord.setUserName(userName);
        final TUser tUser = tUserRecord.into(TUser.class);
        userDao.insert(tUser);
        return 1;
    }

    @Transactional(readOnly = true)
    public TUser selectById(final Long userId) {
        return userDao.fetchOneById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public TUser insertRetuning(final Long userId, final String userName) {
        this.insert(userId, userName);
        return this.selectById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public TUser insertRetuningThrow(final Long userId, final String userName) {
        final TUser tUser = this.insertRetuning(userId, userName);
        // TODO: 手动异常，待删除
        int i = 10 / 0;
        return tUser;
    }

}
