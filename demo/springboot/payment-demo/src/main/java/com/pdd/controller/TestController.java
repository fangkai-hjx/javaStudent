package com.pdd.controller;

import com.pdd.config.WxPayConfig;
import com.pdd.vo.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api("测试控制器")
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Resource
    private WxPayConfig wxPayConfig;

    @GetMapping("/t")
    public R getWxPayConfig(){
        String mchId = wxPayConfig.getMchId();
        System.out.println(wxPayConfig);
        return R.ok().data("mchId",mchId);
    }
}
