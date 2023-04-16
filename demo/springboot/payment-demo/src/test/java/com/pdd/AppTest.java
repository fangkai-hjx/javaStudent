package com.pdd;

import com.pdd.config.WxPayConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.PrivateKey;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest{
    @Autowired
    private WxPayConfig wxPayConfig;

    @Test
    public void TestGetPrivateKey(){
        String privateKeyPath = wxPayConfig.getPrivateKeyPath();
        PrivateKey key = wxPayConfig.getPrivateKey(privateKeyPath);
        System.out.println(key);
    }
}
