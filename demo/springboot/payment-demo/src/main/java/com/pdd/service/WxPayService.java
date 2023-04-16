package com.pdd.service;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

public interface WxPayService {
    Map<String, Object> nativePay(Long productId) throws Exception;

    void processOrder(HashMap<String, Object> bodyMap) throws GeneralSecurityException;
}
