package top.wuhunyu.jooq.transaction.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wuhunyu.jooq.transaction.domain.User;
import top.wuhunyu.jooq.transaction.mapper.UserMapper;

/**
 * 用户信息 mybatis
 *
 * @author wuhunyu
 * @date 2025-01-21 14:38
 */

@Service("userService4Mybatis")
@RequiredArgsConstructor
@Slf4j
public class UserService4Mybatis extends ServiceImpl<UserMapper, User> {

    @Transactional(rollbackFor = Exception.class)
    public Integer insert(final Long userId, final String userName) {
        final User user = User.builder()
                .id(userId)
                .userName(userName)
                .build();
        return this.save(user) ? 1 : 0;
    }

    @Transactional(readOnly = true)
    public User selectById(final Long userId) {
        return this.getById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public User insertRetuning(final Long userId, final String userName) {
        this.insert(userId, userName);
        return this.selectById(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    public User insertRetuningThrow(final Long userId, final String userName) {
        final User user = this.insertRetuning(userId, userName);
        // TODO: 手动异常，待删除
        int i = 10 / 0;
        return user;
    }

}
