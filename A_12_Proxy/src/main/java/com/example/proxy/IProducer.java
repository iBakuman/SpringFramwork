package com.example.proxy;

/**
 * 对生产厂家要求的接口
 * @author Avarice
 * @date 2021/9/5 21:39
 */
public interface IProducer {
    public void saleProduct(float money);

    public void afterService(float money);
}
