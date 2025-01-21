package top.wuhunyu.jooq.transaction.service;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.wuhunyu.jooq.transaction.JooqTransactionApplication;
import top.wuhunyu.jooq.transaction.codegen.tables.pojos.TUser;
import top.wuhunyu.jooq.transaction.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户信息 测试
 *
 * @author wuhunyu
 * @date 2025-01-21 14:53
 */

@SpringBootTest(classes = JooqTransactionApplication.class)
@Execution(ExecutionMode.SAME_THREAD)
@Slf4j
public class UserServiceTest {

    private final Long userId4Jooq = 1L;

    private final String userName4Jooq = "张三";

    private final Long userId4Mybatis = 2L;

    private final String userName4Mybatis = "李四";

    @Autowired
    private UserService4Jooq userService4Jooq;

    @Autowired
    private UserService4Mybatis userService4Mybatis;

    @Autowired
    private DSLContext dslContext;

    @Test
    @DisplayName("jooq 通常测试")
    @Order(1)
    public void testJooqNormal() {
        final TUser tUser = userService4Jooq.insertRetuning(userId4Jooq, userName4Jooq);

        Assertions.assertNotNull(tUser, "插入失败");
        Assertions.assertEquals(tUser.getId(), userId4Jooq, "userId 错误");
        Assertions.assertEquals(tUser.getUserName(), userName4Jooq, "userName 错误");
    }

    @Test
    @DisplayName("jooq 事务回滚测试")
    @Order(2)
    public void testJooqException() {
        Exception exception = null;
        try {
            userService4Jooq.insertRetuningThrow(userId4Jooq, userName4Jooq);
        } catch (Exception e) {
            exception = e;
        }
        Assertions.assertTrue(Objects.isNull(exception) || exception instanceof ArithmeticException,
                "未预知的异常");

        TUser tUserNewest = userService4Jooq.selectById(userId4Jooq);

        Assertions.assertNull(tUserNewest, "数据回滚失败");
    }

    @Test
    @DisplayName("jooq 编程式事务回滚测试")
    @Order(3)
    public void testJooqProgrammingException() {
        Exception exception = null;
        try {
            dslContext.transactionResult(() -> {
                return userService4Jooq.insertRetuningThrow(userId4Jooq, userName4Jooq);
            });
        } catch (Exception e) {
            exception = e;
        }

        Assertions.assertTrue(Objects.isNull(exception) || exception instanceof ArithmeticException,
                "未预知的异常");

        TUser tUserNewest = userService4Jooq.selectById(userId4Jooq);

        Assertions.assertNull(tUserNewest, "数据回滚失败");
    }

    @Test
    @DisplayName("mybatis 通常测试")
    @Order(4)
    public void testMybatisNormal() {
        final User user = userService4Mybatis.insertRetuning(userId4Mybatis, userName4Mybatis);

        Assertions.assertNotNull(user, "插入失败");
        Assertions.assertEquals(user.getId(), userId4Mybatis, "userId 错误");
        Assertions.assertEquals(user.getUserName(), userName4Mybatis, "userName 错误");
    }

    @Test
    @DisplayName("mybatis 事务回滚测试")
    @Order(5)
    public void testMybatisException() {
        Exception exception = null;
        try {
            userService4Mybatis.insertRetuningThrow(userId4Mybatis, userName4Mybatis);
        } catch (Exception e) {
            exception = e;
        }

        Assertions.assertTrue(Objects.isNull(exception) || exception instanceof ArithmeticException,
                "未预知的异常");

        User userNewest = userService4Mybatis.selectById(userId4Mybatis);

        Assertions.assertNull(userNewest, "数据回滚失败");
    }

    @BeforeEach
    @AfterEach
    public void clean() {
        final List<Long> ids = new ArrayList<>();
        ids.add(userId4Jooq);
        ids.add(userId4Mybatis);
        userService4Mybatis.removeByIds(ids);
        final Set<Long> idsSet = userService4Mybatis.listByIds(ids).stream().map(User::getId).collect(Collectors.toSet());

        Assertions.assertFalse(idsSet.contains(userId4Jooq), userName4Jooq + " 数据清理失败");
        Assertions.assertFalse(idsSet.contains(userId4Mybatis), userName4Mybatis + " 数据清理失败");
    }

}
