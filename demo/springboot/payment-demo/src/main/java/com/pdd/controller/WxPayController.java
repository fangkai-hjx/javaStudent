package com.pdd.controller;

import com.google.common.base.Verify;
import com.google.gson.Gson;
import com.pdd.config.WxPayConfig;
import com.pdd.service.WxPayService;
import com.pdd.utils.HttpUtils;
import com.pdd.utils.WechatPay2ValidatorForRequest;
import com.pdd.vo.R;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/api/wx-pay")
@Api(tags = "网站微信支付API")
@Slf4j
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    WxPayConfig wxPayConfig;

    @Autowired
    private Verifier verifier;

    @ApiOperation("调用统一下单API，生成支付二维码")
    @PostMapping("native/{productId}")
    public R nativePay(@PathVariable(name = "productId") Long productId) throws Exception {
        log.info("发起支付请求");
        //放回支付二维码链接和订单号
        Map<String, Object> map = wxPayService.nativePay(productId);
        return R.ok().setData(map);
    }

    @PostMapping("/native/notify")
    public String nativeNotify(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,String> map = new HashMap<>();
        Gson gson = new Gson();
        // 处理通知参数
        try {
            String body = HttpUtils.readData(req);
            HashMap<String,Object> bodyMap = gson.fromJson(body, HashMap.class);
            String requestId = (String) bodyMap.get("id");
            log.info("支付通知的id====> {}",bodyMap.get("id"));
            log.info("支付通知的完整数据====> {}",body);
            //TODO：验证签名
            WechatPay2ValidatorForRequest wechatPay2ValidatorForRequest =
                    new WechatPay2ValidatorForRequest(verifier,requestId,body);
            if(!wechatPay2ValidatorForRequest.validate(req)){
                log.error("通知验签失败- {}",requestId);
                rsp.setStatus(500);
                map.put("code","ERROR");
                map.put("message","验签失败");
                return gson.toJson(map);
            }
            log.error("通知验签成功- {}",requestId);
            //  处理订单
            wxPayService.processOrder(bodyMap);
            // 成功应答
            rsp.setStatus(200);
            map.put("code","SUCCESS");
            map.put("message","成功");
            return gson.toJson(map);
        }catch (Exception e){
            e.printStackTrace();
            rsp.setStatus(500);
            map.put("code","ERROR");
            map.put("message","失败");
            return gson.toJson(map);
        }
    }
}
