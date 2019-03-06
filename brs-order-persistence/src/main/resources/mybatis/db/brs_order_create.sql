CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(255) DEFAULT NULL COMMENT '订单号',
  `third_id` varchar(255) DEFAULT NULL COMMENT '第三方订单号',
  `order_type` varchar(45) DEFAULT NULL COMMENT '订单类型',
  `impact_factor` double DEFAULT NULL COMMENT '影响因子',
  `academy_of_science_cn` varchar(45) DEFAULT NULL COMMENT '中科院分区',
  `deadline` datetime DEFAULT NULL COMMENT '截止日期',
  `description` varchar(255) DEFAULT NULL COMMENT '订单描述',
  `editor` varchar(45) DEFAULT NULL COMMENT '编辑',
  `data_processor` varchar(45) DEFAULT NULL COMMENT '数据处理人员',
  `submitter` varchar(45) DEFAULT NULL COMMENT '投稿人',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '定金',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价格',
  `key_word` varchar(255) DEFAULT NULL COMMENT '关键字',
  `creator` varchar(45) DEFAULT NULL COMMENT '订单创建者',
  `jcr` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_remarks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单备注';
