package com.pdd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pdd.entity.OrderInfo;
import com.pdd.entity.Product;
import com.pdd.enums.OrderStatus;
import com.pdd.mapper.OrderInfoMapper;
import com.pdd.mapper.ProductMapper;
import com.pdd.service.OrderInfoService;
import com.pdd.utils.OrderNoUtils;
import org.mockito.internal.matchers.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private ProductMapper productMapper;
    @Override
    public OrderInfo createByProductId(Long productId) {
        // 查找已存在但是未支付的订单
        OrderInfo orderInfo = this.getNoPayOrderByProductId(productId);
        if(!Objects.isNull(orderInfo)){
            return orderInfo;
        }
        orderInfo = new OrderInfo();
        //获取商品信息
        Product product = productMapper.selectById(productId);
        orderInfo.setTitle(product.getTitle());
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo());
        orderInfo.setTotalFee(product.getPrice());//分
        orderInfo.setProductId(productId);
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getStatus());
        baseMapper.insert(orderInfo);
        return orderInfo;
    }

    @Override
    public OrderInfo getNoPayOrderByProductId(Long productId) {
        //select * from t_order_info where order_status = "未支付"
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status",OrderStatus.NOTPAY.getStatus())
                    .eq("product_id",productId);
//                .eq("user_id",userId);
        OrderInfo orderInfo = baseMapper.selectOne(queryWrapper);
        return orderInfo;
    }

    /**
     * 存储订单二维码
     * @param orderNo
     * @param codeUrl
     */
    @Override
    public void saveCodeUrl(String orderNo, String codeUrl) {
        // test
        UpdateWrapper<OrderInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("order_no",orderNo);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCodeUrl(codeUrl);
        baseMapper.update(orderInfo,updateWrapper);
    }

    /**
     * 查询订单列表，并按照创建时间倒序
     * @return
     */
    @Override
    public List<OrderInfo> listOrderByCreateTimeDesc() {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 根据订单号更新订单状态
     * @param orderNo
     * @param success
     */
    @Override
    public void updateStatusOrderNo(String orderNo, OrderStatus success) {

    }
}
