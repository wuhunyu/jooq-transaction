package top.wuhunyu.jooq.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * jooq 事务验证程序
 *
 * @author wuhunyu
 * @date 2025-01-21 14:30
 */

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class JooqTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(JooqTransactionApplication.class, args);
    }

}
