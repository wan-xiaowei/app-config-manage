
DROP TABLE IF EXISTS `legal_operate_log`;
CREATE TABLE `legal_operate_log` (
  `id` varchar(32) NOT NULL,
  `data_id` varchar(32) NOT NULL COMMENT '业务数据ID',
  `log_type` tinyint(4) DEFAULT NULL COMMENT '日志类型（帐号管理日志、协议管理日志、店铺管理日志、公司管理日志、VAT税收管理）',
  `operate_type` varchar(16) DEFAULT NULL COMMENT '操作类型（驳回,新增,编辑,审核）',
  `operate_details` varchar(5000) DEFAULT NULL COMMENT '操作详细日志',
  `operate_id` varchar(50) NOT NULL COMMENT '操作用户',
  `operate_user_name` varchar(50) NOT NULL COMMENT '操作用户姓名',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  `code` varchar(200) DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='合规-操作日志';

