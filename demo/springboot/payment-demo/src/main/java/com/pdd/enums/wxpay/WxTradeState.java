package com.pdd.enums.wxpay;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付交易状态:注意这是商户和微信支付 之间 发生的支付交易。
 */
public enum WxTradeState {

    /**
     * 支付成功
     */
    SUCCESS("SUCCESS"),

    /**
     * 未支付
     */
    NOTPAY("NOTPAY"),

    /**
     * 已关闭
     */
    CLOSED("CLOSED"),

    /**
     * 转入退款
     */
    REFUND("REFUND");

    /**
     * 类型
     */
    private final String type;

    public String getType() {
        return type;
    }
    WxTradeState(String type){
        this.type = type;
    }
}
