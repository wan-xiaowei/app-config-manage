
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';


CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `code` varchar(50) DEFAULT NULL COMMENT '配置编码',
  `name` varchar(50) DEFAULT NULL COMMENT '配置项名称',
  `is_link` bit(1) DEFAULT b'0' COMMENT '是否联动',
  `system` varchar(100) DEFAULT NULL COMMENT '使用系统',
  `commont` varchar(100) DEFAULT NULL COMMENT '备注',
  `pre_coloum` varchar(255) DEFAULT NULL COMMENT '预留列',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  `modify_user_name` varchar(50) DEFAULT NULL COMMENT '修改人姓名',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='配置项';

CREATE TABLE `conf_detail` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `conf_id` int(11) NOT NULL COMMENT '配置项id',
  `code` varchar(50) DEFAULT NULL COMMENT '配置项编码',
  `key` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `link_id` int(11) DEFAULT NULL COMMENT '本表的联动id',
  `commont` varchar(100) DEFAULT NULL COMMENT '备注',
  `pre_coloum` varchar(100) DEFAULT NULL COMMENT '预留列',
  `create_user_id` varchar(50) DEFAULT NULL COMMENT '创建人id',
  `create_user_name` varchar(50) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user_id` varchar(50) DEFAULT NULL COMMENT '修改人id',
  `modify_user_name` varchar(50) DEFAULT NULL COMMENT '修改人姓名',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` bit(1) DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='配置项明细';
