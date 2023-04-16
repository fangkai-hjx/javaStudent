package com.pdd.controller;

import com.pdd.entity.Product;
import com.pdd.service.ProductService;
import com.pdd.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@CrossOrigin //开放前段跨域访问
@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource(name = "productServiceImpl")
    private ProductService productService;

    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/list")
    public R list(){
        List<Product> products = productService.list();
        return R.ok().data("productList",products);
    }

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public R test(){
        return R.ok().data("message","hello").data("now",new Date());
    }
}
