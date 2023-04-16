package com.pdd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum PayType {
    WXPAY("微信"),
    ALIPAY("支付宝");

    private final String type;
    PayType(String payType){
        this.type = payType;
    }

    public String getType() {
        return type;
    }
}

