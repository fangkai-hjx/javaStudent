package com.pdd.proxy;

/**
 * 对生产厂家的要求
 */
public interface IProducer {
    public void saleProduct(float money);

    public void afterService(float money) ;
}
