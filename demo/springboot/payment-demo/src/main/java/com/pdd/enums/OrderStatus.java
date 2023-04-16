package com.pdd.enums;

/**
 * 枚举 订单的状态：注意这是用户和商户平台 发生的 订单关系。
 */
public enum OrderStatus {
    NOTPAY("未支付"),
    SUCCESS("支付成功"),
    CLOSED("超时已关闭"),
    CANCEL("用户已取消"),
    REFUND_PROCESSING("退款中"),
    REFUND_SUCCESS("退款成功"),
    REFUND_ABNORMAL("退款异常");
    private final String status;
    OrderStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
