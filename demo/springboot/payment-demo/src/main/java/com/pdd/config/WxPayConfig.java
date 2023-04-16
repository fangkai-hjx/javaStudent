package com.pdd.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

@Data
@Configuration
@PropertySource("classpath:wxpay.properties")
@ConfigurationProperties(prefix = "wxpay")
public class WxPayConfig {
    private String mchId;// 商户号
    private String mchSerialNo;// 商户API证书序列号

    private String privateKeyPath;//商户密钥文件

    private String apiV3Key;//APuV3密钥

    private String appId;//APPID
    private String Domain;//微信服务器地址

    private String notifyDomain;//接收结果通知地址

    /**
     * 获取商户的私钥
     *
     * @return
     */
    private PrivateKey getPrivateKey(String path) {
        try {
            return PemUtil.loadPrivateKey(
                    new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("私钥文件不存在");
        }
    }

    /**
     * 获取签名验证器
     *
     * @return
     */
    @Bean
    public Verifier getVerifier() {
        // 向证书管理器增加需要自动更新平台证书的商户信息
        try {
            CertificatesManager certificatesManager = CertificatesManager.getInstance();
            // 获取用户私钥
            PrivateKey privateKey = getPrivateKey(privateKeyPath);
            // 私钥签名对象
            PrivateKeySigner privateKeySigner = new PrivateKeySigner(mchSerialNo, privateKey);
            // 身份认证对象
            WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(mchId, privateKeySigner);
            //apiV3Key 为对称加密密钥，后续传输过程需要通过该密钥加密数据
            certificatesManager.putMerchant(mchId, wechatPay2Credentials, apiV3Key.getBytes(StandardCharsets.UTF_8));
            return certificatesManager.getVerifier(mchId);
        } catch (IOException | GeneralSecurityException | HttpCodeException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取请求对象
     *
     * @param verifier
     * @return
     */
    @Bean
    public CloseableHttpClient getWxPayClient(Verifier verifier) {
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, getPrivateKey(privateKeyPath))
                .withValidator(new WechatPay2Validator(verifier));
        return builder.build();

    }
}
