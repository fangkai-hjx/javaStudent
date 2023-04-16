package com.pdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pdd.entity.OrderInfo;
import com.pdd.enums.OrderStatus;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createByProductId(Long productId);

    OrderInfo getNoPayOrderByProductId(Long productId);

    void saveCodeUrl(String orderNo,String codeUrl);

    List<OrderInfo> listOrderByCreateTimeDesc();

    void updateStatusOrderNo(String orderNo, OrderStatus success);
}
