# create database if not exists hm_planet default charset utf8mb4 collate utf8mb4_general_ci;
# use hm_planet;
create database if not exists hm_planet_db default charset utf8mb4 collate utf8mb4_general_ci;
use hm_planet_db;

-- ----------------------------
-- 1、用户表
-- ----------------------------
-- 如果表已存在则删除
-- 关闭外键
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists planet_users;
CREATE TABLE planet_users
(
    user_id      BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID',
    username     VARCHAR(255) NOT NULL COMMENT '用户名',
    email        VARCHAR(255) NOT NULL UNIQUE COMMENT '用户邮箱，必须唯一',
    password     VARCHAR(255) NOT NULL COMMENT '用户密码的哈希值',
    avatar_url   TEXT COMMENT '用户头像的URL地址',
    bio          TEXT COMMENT '用户个人简介',
    phone_number VARCHAR(20) COMMENT '用户手机号',
    del_flag     TINYINT(1)  DEFAULT 0 COMMENT '逻辑删除标记（0: 未删除, 1: 已删除）',
    create_by    varchar(64) default '' comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64) default '' comment '更新者',
    update_time  datetime comment '更新时间',
    PRIMARY KEY (user_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000 COMMENT ='用户信息表';


-- ----------------------------
-- 2、星球表
-- ----------------------------

drop table if exists planets;
create table planets
(
    planet_id   bigint(20)      not null auto_increment comment '星球唯一ID',
    owner_id    bigint(20)      comment '星球创建者，关联users表',
    name        varchar(255)    not null comment '星球名称',
    avatar_url  text comment '星球头像的URL地址',
    description text            comment '星球描述',
    is_paid     char(1)        default '0' comment '是否付费（0免费 1付费）',
    price       decimal(10, 2) default null comment '加入星球的费用',
    del_flag    TINYINT(1)     DEFAULT 0 COMMENT '逻辑删除标记（0: 未删除, 1: 已删除）',
    create_by   varchar(64)    default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)    default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (planet_id),
    foreign key (owner_id) references planet_users (user_id)
) engine = innodb
  auto_increment = 1 comment ='星球表';


-- ----------------------------
-- 3、用户星球关系表
-- ----------------------------
drop table if exists p_user_planet;
create table p_user_planet
(
    user_id    bigint(20) not null comment '用户ID，关联users表',
    planet_id  bigint(20) not null comment '星球ID，关联planets表',
    is_paid    char(1)  default '0' comment '是否会员（0游客 1会员）',
    joined_at  datetime default current_timestamp() comment '加入时间',
    -- 过期时间 一年
    expired_at datetime comment '过期时间',
    primary key (user_id, planet_id),
    foreign key (user_id) references planet_users (user_id),
    foreign key (planet_id) references planets (planet_id)
) engine = innodb comment ='用户星球关系表';


-- 开启外键
SET FOREIGN_KEY_CHECKS = 1;