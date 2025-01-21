package top.wuhunyu.jooq.transaction.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        org.jooq.Configuration configuration = new DefaultConfiguration();
        configuration.set(SQLDialect.MYSQL);
        configuration.set(dataSource);
        return configuration;
    }

    @Bean("dslContext")
    public DSLContext dslContext(org.jooq.Configuration configuration) {
        return DSL.using(configuration);
    }

}
