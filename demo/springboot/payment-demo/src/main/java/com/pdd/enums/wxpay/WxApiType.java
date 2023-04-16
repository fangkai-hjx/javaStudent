package com.pdd.enums.wxpay;

/**
 * 微信API接口
 * api.mch.weixin.qq.com
 */
public enum WxApiType {
    /**
     * Native下单
     */
    NATIVE_PAY("/v3/pay/transactions/native"),
    /**
     * 查询订单(通过商户订单号)
     */
    ORDER_QUERY_BY_NO("//v3/pay/transactions/out-trade-no/%s"),

    /**
     * 关闭订单
     */
    CLOSE_ORDER_BY_NO("v3/pay/transactions/out-trade-no/%s/close"),

    /**
     * 申请退款
     */
    DOMESTIC_REFUNDS("/v3/refund/domestic/refunds"),

    /**
     * 查询单笔退款
     */
    DOMESTIC_REFUNDS_QUERY("/v3/refund/domestic/refunds/%s"),

    /**
     * 申请交易账单
     */
    TRADE_BILLS("/v3/bill/tradebill"),

    /**
     * 申请资金账单
     */
    FUND_FLOW_BILLS("/v3/bill/fundflowbill");

    private final String apiUrl;
    WxApiType(String url) {
        this.apiUrl = url;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
