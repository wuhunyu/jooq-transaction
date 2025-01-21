create database if not exists test;

drop table if exists t_user;
create table t_user
(
    id        bigint primary key comment '主键',
    user_name varchar(120) not null default '' comment '用户名称'
) comment '用户信息';