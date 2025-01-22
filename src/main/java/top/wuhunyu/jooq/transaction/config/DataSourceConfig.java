package top.wuhunyu.jooq.transaction.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.ThreadLocalTransactionProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author wuhunyu
 * @date 2025-01-21 14:48
 */

@Configuration
@MapperScan(basePackages = "top.wuhunyu.jooq.transaction.mapper")
public class DataSourceConfig {

    @Bean("jooqConfiguration")
    public org.jooq.Configuration jooqConfiguration(DataSource dataSource) {
        final org.jooq.Configuration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(dataSource);
        // 新增事务配置
        final DataSourceConnectionProvider dataSourceConnectionProvider = new DataSourceConnectionProvider(
                new TransactionAwareDataSourceProxy(dataSource));
        // 使用 ThreadLocalTransactionProvider 事务管理器，避免 spring 声明式事务 和 jooq 的事务冲突问题
        final ThreadLocalTransactionProvider threadLocalTransactionProvider =
                new ThreadLocalTransactionProvider(dataSourceConnectionProvider);
        configuration.set(threadLocalTransactionProvider);
        return configuration;
    }

    @Bean("dslContext")
    public DSLContext dslContext(org.jooq.Configuration configuration) {
        return DSL.using(configuration);
    }

}
