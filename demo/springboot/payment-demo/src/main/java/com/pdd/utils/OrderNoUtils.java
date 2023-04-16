package com.pdd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单编号工具类
 */
public class OrderNoUtils {
    /**
     * 获取订单编号
     *
     * @return
     */
    public static String getOrderNo() {
        return "ORDER_" + getNo();
    }

    /**
     * 获取退款单编号
     * @return
     */
    public static String getRefundNo(){
        return "REFUND_" + getNo();
    }

    public static String getNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result.append(random.nextInt(10));
        }
        return newDate+result;
    }
}
