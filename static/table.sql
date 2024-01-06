create database aquarium_system;

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
    nickname    VARCHAR(60)        NOT NULL DEFAULT '' COMMENT '昵称',                                                -- 昵称，默认为空字符串
    avatar_id   bigint                      DEFAULT 1 COMMENT '头像ID',
    phone       VARCHAR(20)        NOT NULL DEFAULT '' COMMENT '电话号码',                                            -- 电话号码，不允许为空
    email       VARCHAR(60)        NOT NULL DEFAULT '' COMMENT '邮箱',                                                -- 邮箱，默认为空字符串
    address     VARCHAR(100)       NOT NULL DEFAULT '' COMMENT '地址',                                                -- 地址，默认为空字符串
    sex         INT                NOT NULL DEFAULT 0 COMMENT '性别',                                                 -- 性别，默认为0表示未知 1表示男 2表示女
    role_id     BIGINT             NOT NULL DEFAULT 0 COMMENT '角色ID',                                               -- 角色ID，不允许为空
    create_time DATETIME           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',                             -- 创建时间，默认为当前时间
    update_time DATETIME           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间', -- 更新时间，默认为当前时间，自动更新
    is_delete   INT                NOT NULL DEFAULT 0 COMMENT '删除标志',                                             -- 删除标志，默认为0表示存在
    INDEX idx_username_password (username, password) USING BTREE COMMENT '用户名密码索引'                             -- 用户名密码索引
) COMMENT '用户信息表';

CREATE TABLE aquarium_image
(
    id     BIGINT PRIMARY KEY AUTO_INCREMENT,       -- 头像ID，主键，自增
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