-- 商品管理系统 (Product Management System) 数据库初始化脚本
-- 创建时间: 2025-08-26

-- 删除表（如果存在）
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
