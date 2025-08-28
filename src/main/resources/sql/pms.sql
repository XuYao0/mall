-- EMall商城系统数据库初始化脚本
-- 包含：商品管理系统(PMS) + 用户管理系统(UMS)
-- 修改时间: 2025-08-28

-- 删除表（如果存在）
DROP TABLE IF EXISTS `ums_member`;
DROP TABLE IF EXISTS `pms_product`;

-- 创建商品信息表
CREATE TABLE `pms_product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID，主键',
    `product_name` VARCHAR(255) NOT NULL COMMENT '商品名称',
    `price` DECIMAL(10,2) NOT NULL COMMENT '商品价格',
    `stock_quantity` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
    `product_sn` VARCHAR(64) NULL COMMENT '商品货号',
    `brand_name` VARCHAR(255) NULL COMMENT '品牌名称',
    `publish_status` INT(1) DEFAULT 0 COMMENT '上架状态：0->下架；1->上架',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_product_name` (`product_name`),
    INDEX `idx_product_sn` (`product_sn`),
    INDEX `idx_publish_status` (`publish_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

-- 插入测试数据
INSERT INTO `pms_product` (`product_name`, `price`, `stock_quantity`, `product_sn`, `brand_name`, `publish_status`) VALUES
('iPhone 15 Pro', 7999.00, 100, 'IP15P001', 'Apple', 1),
('华为Mate60 Pro', 6999.00, 50, 'HWM60P001', '华为', 1),
('小米14 Ultra', 5999.00, 80, 'MI14U001', '小米', 1),
('MacBook Pro 14', 15999.00, 30, 'MBP14001', 'Apple', 1),
('戴尔XPS13', 8999.00, 25, 'DELLXPS001', '戴尔', 1),
('联想ThinkPad X1', 12999.00, 40, 'LNVX1001', '联想', 1),
('华硕ROG游戏本', 9999.00, 20, 'ASROG001', '华硕', 0),
('三星Galaxy S24', 5499.00, 60, 'SSG24001', '三星', 1),
('索尼WH-1000XM5耳机', 2299.00, 150, 'SNYWH001', '索尼', 1),
('AirPods Pro 2代', 1899.00, 200, 'APAP2001', 'Apple', 1);

-- 查询验证
SELECT * FROM `pms_product` ORDER BY `id`;

-- ========================================
-- 用户管理系统 (User Management System)
-- ========================================

-- 创建会员信息表
CREATE TABLE `ums_member` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '会员ID，主键',
    `username` VARCHAR(64) NOT NULL COMMENT '会员用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '会员密码（加密存储）',
    `nickname` VARCHAR(100) NULL COMMENT '会员昵称',
    `phone` VARCHAR(20) NULL COMMENT '会员手机号',
    `email` VARCHAR(100) NULL COMMENT '会员邮箱',
    `status` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '会员状态：0->禁用；1->启用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_phone` (`phone`),
    UNIQUE KEY `uk_email` (`email`),
    INDEX `idx_status` (`status`),
    INDEX `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员信息表';

-- 插入测试数据
INSERT INTO `ums_member` (`username`, `password`, `nickname`, `phone`, `email`, `status`) VALUES
('testuser', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '测试用户', '13800138001', 'test@emall.com', 1),
('john_doe', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', 'John Doe', '13800138002', 'john@emall.com', 1),
('jane_smith', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', 'Jane Smith', '13800138003', 'jane@emall.com', 1),
('mike_wang', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '王小明', '13800138004', 'mike@emall.com', 1),
('lisa_chen', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '陈丽莎', '13800138005', 'lisa@emall.com', 1),
('david_liu', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '刘大伟', '13800138006', 'david@emall.com', 0),
('sarah_zhou', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '周莎拉', '13800138007', 'sarah@emall.com', 1),
('tom_zhang', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '张汤姆', '13800138008', 'tom@emall.com', 1),
('emma_li', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HnQrX22', '李艾玛', '13800138009', 'emma@emall.com', 1);

-- 查询验证
SELECT * FROM `ums_member` ORDER BY `id`;
