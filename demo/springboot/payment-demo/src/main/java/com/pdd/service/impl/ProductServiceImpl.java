package com.pdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pdd.entity.OrderInfo;
import com.pdd.entity.Product;
import com.pdd.mapper.OrderInfoMapper;
import com.pdd.mapper.ProductMapper;
import com.pdd.service.OrderInfoService;
import com.pdd.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
