create database aquarium_system;

use aquarium_system;
drop table if exists aquarium_user;
drop table if exists aquarium_role;
drop table if exists aquarium_permission;
drop table if exists aquarium_role_permission;
drop table if exists aquarium_image;

CREATE TABLE aquarium_user
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,                                                                    -- 用户ID，主键，自增
    username    VARCHAR(60) UNIQUE NOT NULL COMMENT '用户名',                                                         -- 用户名，不允许为空
    password    VARCHAR(75)        NOT NULL COMMENT '密码',                                                           -- 密码，不允许为空
    avatar_id   bigint                      DEFAULT 1 COMMENT '头像ID',
    role_id     BIGINT             NOT NULL DEFAULT 0 COMMENT '角色ID',                                               -- 角色ID，不允许为空
    create_time DATETIME           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',                             -- 创建时间，默认为当前时间
    update_time DATETIME           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间', -- 更新时间，默认为当前时间，自动更新
    is_delete   INT                NOT NULL DEFAULT 0 COMMENT '删除标志',                                             -- 删除标志，默认为0表示存在
    INDEX idx_username_password (username, password) USING BTREE COMMENT '用户名密码索引'                             -- 用户名密码索引
) COMMENT '用户信息表';

CREATE TABLE aquarium_image
(
    id     BIGINT PRIMARY KEY AUTO_INCREMENT,         -- 头像ID，主键，自增
    `data` MEDIUMTEXT DEFAULT NULL COMMENT '图片内容' -- 图片内容
) COMMENT '图片信息表';

create TABLE aquarium_role
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,                     -- 角色ID，主键，自增
    role_name VARCHAR(20) NOT NULL COMMENT '角色名',                 -- 角色名，不允许为空
    INDEX idx_role_name (role_name) USING BTREE COMMENT '角色名索引' -- 角色名索引
) COMMENT '角色信息表';

create TABLE aquarium_permission
(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,                           -- 权限ID，主键，自增
    permission_name VARCHAR(20) NOT NULL COMMENT '权限名',                       -- 权限名，不允许为空
    INDEX idx_permission_name (permission_name) USING BTREE COMMENT '权限名索引' -- 权限名索引
) COMMENT '权限信息表';

create TABLE aquarium_role_permission
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,                                                -- 角色权限ID，主键，自增
    role_id       BIGINT NOT NULL COMMENT '角色ID',                                                 -- 角色ID，不允许为空
    permission_id BIGINT NOT NULL COMMENT '权限ID',                                                 -- 权限ID，不允许为空
    INDEX idx_role_id_permission_id (role_id, permission_id) USING BTREE COMMENT '角色ID权限ID索引' -- 角色ID权限ID索引
) COMMENT '角色权限关联表';

# 初始化角色信息
INSERT INTO aquarium_role (role_name)
VALUES ('user');
INSERT INTO aquarium_role (role_name)
VALUES ('admin');
INSERT INTO aquarium_role (role_name)
VALUES ('super_admin');

# 初始化权限信息
INSERT INTO aquarium_permission (permission_name)
VALUES ('user:normal');
INSERT INTO aquarium_permission (permission_name)
VALUES ('admin:normal');
INSERT INTO aquarium_permission (permission_name)
VALUES ('super_admin:normal');

# 初始化角色权限关联信息
INSERT INTO aquarium_role_permission (role_id, permission_id)
VALUES (1, 1);
INSERT INTO aquarium_role_permission (role_id, permission_id)
VALUES (2, 2);
INSERT INTO aquarium_role_permission (role_id, permission_id)
VALUES (2, 1);
INSERT INTO aquarium_role_permission (role_id, permission_id)
VALUES (3, 3);
INSERT INTO aquarium_role_permission (role_id, permission_id)
VALUES (3, 2);
INSERT INTO aquarium_role_permission (role_id, permission_id)
VALUES (3, 1);

drop table if exists aquarium_activity;
drop table if exists aquarium_activity_image;
drop table if exists aquarium_activity_user;
drop table if exists aquarium_notice;

create table aquarium_activity
(
    id          bigint primary key auto_increment,
    title       varchar(100)   not null comment '活动标题',
    content     text                    default null comment '活动内容',
    start_time  datetime       default null comment '活动开始时间',
    end_time    datetime       default null comment '活动结束时间',
    address     varchar(100)   not null comment '活动地址',
    fee         decimal(10, 2) not null comment '活动费用(小数点后两位)',
    status      int            not null default 0 comment '活动状态,0表示未开始，1表示进行中，2表示已结束',
    create_time datetime       not null default current_timestamp comment '创建时间',
    update_time datetime       not null default current_timestamp on update current_timestamp comment '更新时间',
    is_delete   int            not null default 0 comment '删除标志',
    index idx_title (title) using btree comment '活动标题索引'
) comment '活动信息表';

create table aquarium_activity_image
(
    id          bigint primary key auto_increment,
    activity_id bigint not null comment '活动ID',
    image_id    bigint not null comment '图片ID',
    index idx_activity_id_image_id (activity_id, image_id) using btree comment '活动ID图片ID索引'
) comment '活动图片关联表';

create table aquarium_activity_user
(
    id          bigint primary key auto_increment,
    activity_id bigint not null comment '活动ID',
    user_id     bigint not null comment '用户ID',
    is_pay      int    not null default 0 comment '是否支付,0表示未支付，1表示已支付',
    is_check    int    not null default 0 comment '是否已检票,0表示未检票，1表示已检票',
    comment     text            default null comment '用户评价',
    index idx_activity_id_user_id (activity_id, user_id) using btree comment '活动ID用户ID索引'
) comment '活动用户关联表';

#通知信息表
create table aquarium_notice
(
    id          bigint primary key auto_increment,
    user_id     bigint       not null comment '用户ID',
    title       varchar(256) not null comment '通知标题',
    content     text                  default null comment '通知内容',
    create_time datetime     not null default current_timestamp comment '创建时间',
    index idx_user_id (user_id) using btree comment '用户ID索引'
) comment '通知信息表';