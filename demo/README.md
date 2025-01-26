# 数据库SQL
DROP TABLE user_info;

CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) COMMENT '姓名',
  `type` tinyint COMMENT '用户类型',
  `status` tinyint COMMENT '状态 1-正常 2-禁用',
  `phone` varchar(20) COMMENT '手机号',
  `email` varchar(32) COMMENT '邮箱',
  `extend_field` varchar(1000) COMMENT '扩展字段：以JSON格式保存，key为字段名称；value为值',
  `create_time` datetime NOT NULL DEFAULT now() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT now() COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

INSERT INTO `demo`.`user_info` (`name`, `type`, `status`, `phone`, `email`, `extend_field`, `create_time`, `update_time`) 
VALUES ('小明', 1, 1, '18045679090', '1270762753@qq.com', '{key:value}', now(), NOW());

#批量更新的SQL
UPDATE yd_org_purchase_region
    SET
    village_name = CASE id
        WHEN 202 THEN "202村"
        WHEN 203 THEN "203村"
        WHEN 204 THEN "204村"
    END,
		group_name = CASE id
        WHEN 202 THEN "第一组"
        WHEN 203 THEN "第二组"
        WHEN 204 THEN "第三组"
		END
		WHERE id in(202,203,204);