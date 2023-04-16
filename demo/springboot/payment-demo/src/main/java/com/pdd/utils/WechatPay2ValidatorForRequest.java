package com.pdd.utils;

import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.Validator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;

import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static com.wechat.pay.contrib.apache.httpclient.constant.WechatPayHttpHeaders.*;
import static com.wechat.pay.contrib.apache.httpclient.constant.WechatPayHttpHeaders.WECHAT_PAY_TIMESTAMP;

public class WechatPay2ValidatorForRequest {
    protected static final Logger log = LoggerFactory.getLogger(com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator.class);
    protected static final long RESPONSE_EXPIRED_MINUTES = 5L;
    protected final Verifier verifier;
    protected final String requestId;
    protected final String body;

    public WechatPay2ValidatorForRequest(Verifier verifier, String requestId, String body) {
        this.requestId = requestId;
        this.verifier = verifier;
        this.body = body;
    }

    protected static IllegalArgumentException parameterError(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("parameter error: " + message);
    }

    protected static IllegalArgumentException verifyFail(String message, Object... args) {
        message = String.format(message, args);
        return new IllegalArgumentException("signature verify fail: " + message);
    }

    public final boolean validate(HttpServletRequest request) throws IOException {
        try {
            // 处理请求参数
            this.validateParameters(request);
            // 构造验签名串
            String message = this.buildMessage(request); // 构造签名字符串
            String serial = request.getHeader("Wechatpay-Serial");
            String signature = request.getHeader("Wechatpay-Signature");//应答签名
            //验证签名
            // 微信支付的平台证书序列号位于HTTP头Wechatpay-Serial。
            // 验证签名前，请商户先检查序列号是否跟商户当前所持有的 微信支付平台证书的序列号一致。
            // 如果不一致，请重新获取证书。否则，签名的私钥和证书不匹配，将无法成功验证签名。
            if (!this.verifier.verify(serial, message.getBytes(StandardCharsets.UTF_8), signature)) {
                throw verifyFail("serial=[%s] message=[%s] sign=[%s], request-id=[%s]", serial, message, signature, requestId);
            } else {
                return true;
            }
        } catch (IllegalArgumentException var5) {
            log.warn(var5.getMessage());
            return false;
        }
    }

    protected final void validateParameters(HttpServletRequest request) {

        // NOTE: ensure HEADER_WECHAT_PAY_TIMESTAMP at last
        String[] headers = {WECHAT_PAY_SERIAL, WECHAT_PAY_SIGNATURE, WECHAT_PAY_NONCE, WECHAT_PAY_TIMESTAMP};

        // 判断请求头是否为空
        String header = null;
        for (String headerName : headers) {
            header = request.getHeader(headerName);
            if (header == null) {
                throw parameterError("empty [%s], request-id=[%s]", headerName, requestId);
            }
        }
        // 获取时间戳，判断是否过期
        String timestampStr = header;
        try {
            // 通过时间戳 创建一个对象
            Instant responseTime = Instant.ofEpochSecond(Long.parseLong(timestampStr));
            // 拒绝过期应答
            if (Duration.between(responseTime, Instant.now()).abs().toMinutes() >= RESPONSE_EXPIRED_MINUTES) {
                throw parameterError("timestamp=[%s] expires, request-id=[%s]", timestampStr, requestId);
            }
        } catch (DateTimeException | NumberFormatException e) {
            throw parameterError("invalid timestamp=[%s], request-id=[%s]", timestampStr, requestId);
        }
    }

    protected final String buildMessage(HttpServletRequest request) throws IOException {
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        return timestamp + "\n" + nonce + "\n" + body + "\n";
    }

    protected final String getResponseBody(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        return entity != null && entity.isRepeatable() ? EntityUtils.toString(entity) : "";
    }
}

