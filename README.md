### 测试 jOOQ 与 数据库事务

#### 环境依赖

1. MySQL（Innodb 存储引擎）
2. jdk1.8
3. maven

测试步骤

1. 执行 `db/db.sql` sql 脚本
2. 修改 `resource` 下的 `application-dev.yml`，替换数据库连接信息
3. 执行 `top.wuhunyu.jooq.transaction.service.UserServiceTest` 测试