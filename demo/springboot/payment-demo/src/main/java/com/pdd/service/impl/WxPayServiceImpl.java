package com.pdd.service.impl;

import com.google.gson.Gson;
import com.pdd.config.WxPayConfig;
import com.pdd.entity.OrderInfo;
import com.pdd.enums.OrderStatus;
import com.pdd.enums.wxpay.WxApiType;
import com.pdd.enums.wxpay.WxNotifyType;
import com.pdd.service.OrderInfoService;
import com.pdd.service.PaymentInfoService;
import com.pdd.service.WxPayService;
import com.pdd.utils.OrderNoUtils;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private WxPayConfig wxPayConfig;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private PaymentInfoService paymentInfoService;

    @Override
    public Map<String, Object> nativePay(Long productId) throws Exception {
        // 本地生成订单
        log.info("生成订单:" + System.currentTimeMillis());
        OrderInfo orderInfo = orderInfoService.createByProductId(productId);
        String codeUrl = orderInfo.getCodeUrl();
        if (!Strings.isEmpty(codeUrl)) {
            log.info("订单已存在，二维码已保存");
            HashMap<String, String> resultMap = new HashMap<>();
            codeUrl = resultMap.get("code_url");
            resultMap.put("codeUrl", codeUrl);
            resultMap.put("orderNo", orderInfo.getOrderNo());
        }
        // 调用统一下单的API
        HttpPost httpPost = new HttpPost(wxPayConfig.getDomain().concat(WxApiType.NATIVE_PAY.getApiUrl()));
        // 请求body参数
        Gson gson = new Gson();
        HashMap<Object, Object> paramsMap = new HashMap<>();
        paramsMap.put("appid", wxPayConfig.getAppId());
        paramsMap.put("mchid", wxPayConfig.getMchId());
        paramsMap.put("description", orderInfo.getTitle());
        paramsMap.put("out_trade_no", orderInfo.getOrderNo());
        paramsMap.put("notify_url", wxPayConfig.getNotifyDomain().concat(WxNotifyType.NATIVE_NOTIFY.getType()));

        HashMap amountMap = new HashMap();
        amountMap.put("total", orderInfo.getTotalFee());
        amountMap.put("currency", "CNY");

        paramsMap.put("amount", amountMap);

        String reqdata = gson.toJson(paramsMap);
        log.info("请求参数：" + reqdata);

        StringEntity entity = new StringEntity(reqdata, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        //完成签名并执行请求
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
        HashMap<String, Object> result = new HashMap<>();
        try {
            String body = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) { //处理成功
                System.out.println("success,return body = " + body);
            } else if (statusCode == 204) { //处理成功，无返回Body
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode + ",return body = " + body);
                throw new IOException("request failed");
            }
            // 响应结果
            HashMap<String, String> resultMap = gson.fromJson(body, HashMap.class);
            codeUrl = resultMap.get("code_url");

            //保存二维码
            this.orderInfoService.saveCodeUrl(orderInfo.getOrderNo(), codeUrl);

            result.put("codeUrl", codeUrl);
            result.put("orderNo", orderInfo.getOrderNo());
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * 订单处理
     * @param bodyMap
     */
    @Override
    public void processOrder(HashMap<String, Object> bodyMap) throws GeneralSecurityException {
        log.info("处理订单");
        // 解密秘文
        String plainText = decryptFromResource(bodyMap);
        // 待明文转换成map
        Gson gson = new Gson();
        HashMap plainTextMap = gson.fromJson(plainText, HashMap.class);
        // 更新订单状态
        String orderNo = (String) plainTextMap.get("out_trade_no");
        orderInfoService.updateStatusOrderNo(orderNo,OrderStatus.SUCCESS);
        // 记录支付日志
        paymentInfoService.createPaymentInfo(plainText);
    }

    /**
     * 对微信回调的请求体数据进行解密
     * @param bodyMap
     * @return
     */
    private String decryptFromResource(HashMap<String, Object> bodyMap) throws GeneralSecurityException {
        log.info("回调body密文解密");
        Map<String,String> map = (Map)bodyMap.get("resource");
        // 数据秘文
        String ciphertext = map.get("ciphertext");
        // 随机串
        String nonce = map.get("nonce");
        // 附加数据
        String associatedData = map.get("associated_data");
        AesUtil aesUtil1 = new AesUtil(wxPayConfig.getApiV3Key().getBytes(StandardCharsets.UTF_8));
        String plainText = aesUtil1.decryptToString(associatedData.getBytes(StandardCharsets.UTF_8),nonce.getBytes(StandardCharsets.UTF_8),
                ciphertext);
        log.info("秘文 ===> {}",ciphertext);
        log.info("明文 ===> {}",plainText);
        return plainText;
    }
}
