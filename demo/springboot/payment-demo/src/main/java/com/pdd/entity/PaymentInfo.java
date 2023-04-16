package com.pdd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * CREATE TABLE `t_payment_info` (
 * `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
 * `transaction_id` varchar(50) DEFAULT NULL COMMENT '支付系统交易编号',
 * `payment_type` varchar(20) DEFAULT NULL COMMENT '支付类型',
 * `trade_type` varchar(20) DEFAULT NULL COMMENT '交易类型',
 * `trade_state` varchar(50) DEFAULT NULL COMMENT '交易状态',
 * `payer_total` int(11) DEFAULT NULL COMMENT '支付金额(分)',
 * `content` text COMMENT '通知参数',
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
@TableName("t_payment_info")
@Data
public class PaymentInfo extends BaseEntity {
    private String orderNo; // 商户订单编号
    private String transactionId;//支付系统交易编号
    private String paymentType;//支付类型
    private String tradeType;//交易类型
    private String tradeState;//交易状态
    private String payerTotal;//支付金额(分)
    private String content;//通知参数
}
